package site.euan.bester.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import site.euan.bester.constant.UserConstant;
import site.euan.bester.domain.dto.ProfileDTO;
import site.euan.bester.domain.dto.UserEmailLoginDTO;
import site.euan.bester.domain.dto.UserLoginDTO;
import site.euan.bester.domain.dto.UserRegisterDTO;
import site.euan.bester.domain.model.User;
import site.euan.bester.domain.model.UserInfo;
import site.euan.bester.domain.vo.ProfileVO;
import site.euan.bester.domain.vo.TabsListInfoVO;
import site.euan.bester.domain.vo.UserInfoVO;
import site.euan.bester.domain.vo.VerifyVO;
import site.euan.bester.exception.LoginException;
import site.euan.bester.exception.RegisterException;
import site.euan.bester.exception.UserException;
import site.euan.bester.mapper.UserMapper;
import site.euan.bester.service.UserInfoService;
import site.euan.bester.service.UserService;
import site.euan.bester.utils.JwtUtil;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author EUAN
 * 用户接口实现类
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserInfoService userInfoService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public UserInfoVO login(UserLoginDTO userLoginDTO) {
        User user = userMapper.geyByAccount(userLoginDTO.getUsername());
        if (Objects.isNull(user)) {
            throw new LoginException(UserConstant.THE_USER_ACCOUNT_DOES_NOT_EXIST);
        }
        String inputPassword = DigestUtils.md5DigestAsHex(userLoginDTO.getPassword().getBytes());
        if (!Objects.equals(user.getPassword(), inputPassword)) {
            throw new LoginException(UserConstant.USER_PASSWORD_ERROR);
        }
        UserInfo userInfo = userInfoService.getByUserId(user.getId());
        return EncapsulationUserInfo(userInfo);
    }

    @Override
    public UserInfoVO register(UserRegisterDTO userRegisterDTO) {
        if (userMapper.geyByAccount(userRegisterDTO.getUsername()) != null) {
            throw new RegisterException(UserConstant.ACCOUNT_ALREADY_EXISTS);
        }
        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        int userId = userMapper.insert(user);
        if (userId <= 0) {
            throw new RegisterException(UserConstant.ACCOUNT_REGISTRATION_FAILED);
        }
        UserInfo userInfo = userInfoService.getByUserId((long) userId);
        return EncapsulationUserInfo(userInfo);
    }

    @Override
    @Cacheable(value = "current", key = "#id", sync = true)
    public UserInfoVO getCurrent(Long id) {
        log.info("执行信息查询");
        UserInfo userInfo = userInfoService.getById(id);
        return EncapsulationUserInfo(userInfo);
    }

    @Override
    @CachePut(value = "current", key = "#currentId")
    public User changePassword(String password, Long currentId) {
        UserInfo userInfo = userInfoService.getById(currentId);
        //TODO 后续添加修改密码验证，例如：验证手机号或者邮箱等功能，确保用户密码修改的安全性
        if (userInfo == null) {
            throw new UserException("账号信息验证错误,修改密码失败");
        }
        User user = userMapper.selectById(userInfo.getUserId());
        if (user == null) {
            throw new UserException("账号不存在,修改密码失败");
        }
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userMapper.updateById(user);
        return user;
    }

    @Override
    @CacheEvict(value = "current", key = "#id")
    public void logout(Long id) {
        if (id == null) {
            throw new UserException("账号不存在");
        }
        UserInfo userInfo = userInfoService.getById(id);
        userMapper.logOut(userInfo.getUserId());
    }

    @Override
    public UserInfoVO loginEmail(UserEmailLoginDTO userEmailLoginDTO) {
        if (Objects.isNull(userEmailLoginDTO.getEmail())) {
            throw new LoginException("邮箱不能为空");
        }
        if (Objects.isNull(userEmailLoginDTO.getCode())) {
            throw new LoginException("验证码不能为空");
        }
        Object result = redisTemplate.opsForValue().get(userEmailLoginDTO.getEmail() + "_code");
        String code = (String) result;
        if (!Objects.equals(code, userEmailLoginDTO.getCode())) {
            throw new LoginException("验证码错误");
        }
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("email", userEmailLoginDTO.getEmail());
        User user = userMapper.selectOne(qw);
        if (user == null) {
            //没有账号，注册一个
            User user1 = new User();
            user1.setEmail(userEmailLoginDTO.getEmail());
            user1.setIsAdmin(false);
            user1.setIsOnline(true);
            userMapper.insert(user1);
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(user1.getId());
            userInfo.setName("用户很懒，还未取名字");
            userInfo.setPosts(0);
            userInfo.setFollowers(0);
            userInfo.setFollowing(0);
            userInfo.setGoldCoin(BigInteger.valueOf(0));
            return EncapsulationUserInfo(userInfoService.addInfo(userInfo));
        }
        UserInfo byUserId = userInfoService.getByUserId(user.getId());
        return EncapsulationUserInfo(byUserId);
    }

    @Override
    public ProfileVO getProfile(Long currentId) {
        UserInfo userInfo = userInfoService.getById(currentId);
        VerifyVO verify = userInfoService.getVerify(currentId);
        User user = userMapper.selectById(userInfo.getUserId());
        return ProfileVO.builder()
                .username(user.getUsername())
                .realName(verify.getRealName())
                .nickName(userInfo.getName())
                .gender(userInfo.getGender())
                .birthday(userInfo.getBirthday())
                .birthplace(JSON.parseArray(userInfo.getBirthplace(), String.class))
                .residence(JSON.parseArray(userInfo.getResidence(), String.class))
                .phone(user.getPhone())
                .email(user.getEmail())
                .qq(user.getQq())
                .signature(userInfo.getSignature())
                .build();
    }

    @Override
    public void updateProfile(Long currentId, ProfileDTO profileDTO) {
        User user = userMapper.selectById(currentId);
        BeanUtils.copyProperties(profileDTO, user);
        userMapper.updateById(user);
    }

    /**
     * 封装用户信息
     *
     * @param userInfo
     * @return
     */
    public UserInfoVO EncapsulationUserInfo(UserInfo userInfo) {
        UserInfoVO userInfoVO = UserInfoVO.builder()
                .id(userInfo.getId())
                .name(userInfo.getName())
                .avatar(userInfo.getAvatar())
                .followers(userInfo.getFollowers())
                .following(userInfo.getFollowing())
                .posts(userInfo.getPosts())
                .build();
        return userInfoVO;
    }
}

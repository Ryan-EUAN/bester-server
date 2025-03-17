package site.euan.bester.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import site.euan.bester.domain.dto.ProfileDTO;
import site.euan.bester.domain.dto.VerifyIdentityDTO;
import site.euan.bester.domain.model.User;
import site.euan.bester.domain.model.UserInfo;
import site.euan.bester.domain.model.VerifyIdentity;
import site.euan.bester.domain.vo.TabsListInfoVO;
import site.euan.bester.domain.vo.VerifyVO;
import site.euan.bester.mapper.UserInfoMapper;
import site.euan.bester.mapper.UserMapper;
import site.euan.bester.mapper.VerifyIdentityMapper;
import site.euan.bester.service.UserInfoService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private VerifyIdentityMapper verifyIdentityMapper;

    @Override
    public UserInfo addInfo(UserInfo userInfo) {
        int insert = userInfoMapper.insert(userInfo);
        return userInfo;
    }

    @Override
    public UserInfo getByUserId(Long id) {
        log.info("拿到的ID{}", id);
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        return userInfoMapper.selectOne(queryWrapper);
    }

    @Override
    public UserInfo getById(Long id) {
        return userInfoMapper.selectById(id);
    }

    @Override
    public void publish(Long currentId) {
        UserInfo userInfo = getById(currentId);
        log.info("拿一下个人信息{}", userInfo);
        userInfo.setPosts(userInfo.getPosts() + 1);
        userInfoMapper.updateById(userInfo);
    }

    @Override
    public List<TabsListInfoVO> getUserGoldCoinRankingList() {
        QueryWrapper<UserInfo> qw = new QueryWrapper<>();
        qw.lambda().orderByAsc(UserInfo::getGoldCoin);
        List<UserInfo> userInfos = userInfoMapper.selectList(qw);
        List<TabsListInfoVO> tabsListInfoVOList = new ArrayList<>();
        userInfos.forEach(a -> tabsListInfoVOList.add(TabsListInfoVO.builder()
                .id(a.getId())
                .label(a.getName())
                .end(a.getGoldCoin().toString())
                .build()));
        return tabsListInfoVOList;
    }

    @Override
    public Long updateProfile(Long currentId, ProfileDTO profileDTO) {
        UserInfo userInfo = getById(currentId);
        BeanUtils.copyProperties(profileDTO, userInfo);
        userInfo.setBirthplace(JSON.toJSONString(profileDTO.getBirthplace()));
        userInfo.setResidence(JSON.toJSONString(profileDTO.getResidence()));
        userInfoMapper.updateById(userInfo);
        return userInfo.getUserId();
    }

    @Override
    public void verifyIdentity(Long currentId, VerifyIdentityDTO verifyIdentityDTO) {
        VerifyIdentity verifyIdentity = new VerifyIdentity();
        verifyIdentity.setUserId(currentId);
        verifyIdentity.setRealName(verifyIdentityDTO.getRealName());
        verifyIdentity.setIdCard(verifyIdentityDTO.getIdCard());
        verifyIdentityMapper.insert(verifyIdentity);
    }

    @Override
    public VerifyVO getVerify(Long currentId) {
        UserInfo userInfo = userInfoMapper.selectById(currentId);
        User user = userMapper.selectById(userInfo.getUserId());
        QueryWrapper<VerifyIdentity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", currentId);
        VerifyIdentity verifyIdentity = verifyIdentityMapper.selectOne(queryWrapper);
        return VerifyVO.builder()
                .phone(user.getPhone())
                .phoneVerified(user.getPhone() != null && !user.getPhone().isEmpty())
                .realName(verifyIdentity != null ? verifyIdentity.getRealName() : null)
                .idCard(verifyIdentity != null ? verifyIdentity.getIdCard() : null)
                .identityVerified(verifyIdentity != null)
                .build();
    }
}

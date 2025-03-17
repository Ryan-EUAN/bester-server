package site.euan.bester.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import site.euan.bester.context.BaseContext;
import site.euan.bester.domain.dto.ProfileDTO;
import site.euan.bester.domain.dto.UserLoginDTO;
import site.euan.bester.domain.dto.UserRegisterDTO;
import site.euan.bester.domain.dto.VerifyIdentityDTO;
import site.euan.bester.domain.entity.Result;
import site.euan.bester.domain.model.User;
import site.euan.bester.domain.model.UserInfo;
import site.euan.bester.domain.vo.LoginVO;
import site.euan.bester.domain.vo.ProfileVO;
import site.euan.bester.domain.vo.UserInfoVO;
import site.euan.bester.domain.vo.VerifyVO;
import site.euan.bester.mapper.UserInfoMapper;
import site.euan.bester.service.UserInfoService;
import site.euan.bester.service.UserService;
import site.euan.bester.utils.JwtUtil;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author EUAN
 * 用户控制层
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户控制层")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/current")
    @ApiOperation(value = "获取当前用户信息", notes = "根据请求头携带的token，解析请求ID，并返回用户的个人信息")
    public Result<UserInfoVO> Current() {
        Long currentId = BaseContext.getCurrentId();
        UserInfoVO userInfoVO = userService.getCurrent(currentId);
        return Result.success(userInfoVO);
    }

    @GetMapping("/profile")
    @ApiOperation(value = "获取用户个人信息", notes = "根据用户ID，返回用户的个人信息")
    public Result<ProfileVO> GetProfile() {
        Long currentId = BaseContext.getCurrentId();
        ProfileVO profile = userService.getProfile(currentId);
        return Result.success(profile);
    }

    @PutMapping("/profile")
    @ApiOperation(value = "修改用户个人信息", notes = "根据用户ID，修改用户的个人信息")
    public Result<?> UpdateProfile(@RequestBody ProfileDTO profileDTO) {
        Long currentId = BaseContext.getCurrentId();
        Long userId = userInfoService.updateProfile(currentId, profileDTO);
        userService.updateProfile(userId, profileDTO);
        return Result.success();
    }

    @GetMapping("/verify")
    @ApiOperation(value = "获取认证信息", notes = "获取认证信息")
    public Result<VerifyVO> GetVerify() {
        Long currentId = BaseContext.getCurrentId();
        VerifyVO verify = userInfoService.getVerify(currentId);
        return Result.success(verify);
    }

    @PostMapping("/verify/identity")
    @ApiOperation(value = "实名认证", notes = "绑定身份证")
    public Result<?> VerifyIdentity(@RequestBody VerifyIdentityDTO verifyIdentityDTO) {
        Long currentId = BaseContext.getCurrentId();
        userInfoService.verifyIdentity(currentId, verifyIdentityDTO);
        return Result.success();
    }
}
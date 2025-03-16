package site.euan.bester.controller;

import cn.hutool.core.util.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import site.euan.bester.context.BaseContext;
import site.euan.bester.domain.dto.UserEmailLoginDTO;
import site.euan.bester.domain.dto.UserLoginDTO;
import site.euan.bester.domain.dto.UserRegisterDTO;
import site.euan.bester.domain.entity.Result;
import site.euan.bester.domain.vo.LoginVO;
import site.euan.bester.domain.vo.UserInfoVO;
import site.euan.bester.service.MailService;
import site.euan.bester.service.UserService;
import site.euan.bester.utils.JwtUtil;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author EUAN
 * 账号鉴权
 * 1、用户登录
 * 2、用户注册
 * 3、根据ID获取当前用户信息
 * 4、修改用户密码
 */
@RestController
@RequestMapping("/auth")
@Slf4j
@Api(tags = "账号鉴权")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @Autowired
    private JwtUtil jwtUtil;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/user/login")
    @ApiOperation(value = "用户登录接口", notes = "提供给用户的登录接口")
    public Result<LoginVO> UserLogin(@RequestBody UserLoginDTO userLoginDTO) {
        UserInfoVO userInfoVO = userService.login(userLoginDTO);
        LoginVO loginVO = LoginVO.builder()
                .info(userInfoVO)
                .token(jwtUtil.createJwt(userInfoVO.getId(), 3600))
                .build();
        return Result.success(loginVO);
    }

    @PostMapping("/user/loginEmail")
    @ApiOperation(value = "用户邮箱登录接口", notes = "提供给用户的登录邮箱接口")
    public Result<LoginVO> UserLoginEmail(@RequestBody UserEmailLoginDTO userEmailLoginDTO) {
        UserInfoVO userInfoVO = userService.loginEmail(userEmailLoginDTO);
        LoginVO loginVO = LoginVO.builder()
                .info(userInfoVO)
                .token(jwtUtil.createJwt(userInfoVO.getId(), 3600))
                .build();
        return Result.success(loginVO);
    }

    @PostMapping("/user/register")
    @ApiOperation(value = "用户注册接口", notes = "提供给用户的注册接口")
    public Result<LoginVO> UserRegister(@RequestBody UserRegisterDTO userRegisterDTO) {
        UserInfoVO register = userService.register(userRegisterDTO);
        LoginVO loginVO = LoginVO.builder()
                .info(register)
                .token(jwtUtil.createJwt(register.getId(), 3600))
                .build();
        return Result.success(loginVO);
    }

    @GetMapping("/user/sendCode")
    @ApiOperation(value = "发送验证码", notes = "给邮箱发送随机验证码，并返回验证码")
    public Result<String> sendCode(@RequestParam("email") String email) throws MessagingException, UnsupportedEncodingException {
        String code = RandomUtil.randomNumbers(6);
        mailService.sendVerificationCode(email, code);
        redisTemplate.opsForValue().set(email + "_code", code, 300, TimeUnit.SECONDS);
        return Result.success(code);
    }

    @GetMapping("/user/logout")
    @ApiOperation(value = "用户登出接口", notes = "就是给用户退出登录用的")
    public Result Logout() {
//        Long currentId = BaseContext.getCurrentId();
//        log.info("当前ID：{}", currentId);
//        userService.logout(currentId);
        return Result.success();
    }

    @PutMapping("/user/change-password")
    @ApiOperation(value = "修改用户密码", notes = "修改用户密码")
    public Result ChangePassword(@RequestBody Map<String, String> request) {
        String password = request.get("password");
        userService.changePassword(password, BaseContext.getCurrentId());
        return Result.success();
    }
}

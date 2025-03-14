package site.euan.bester.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import site.euan.bester.context.BaseContext;
import site.euan.bester.domain.dto.UserLoginDTO;
import site.euan.bester.domain.dto.UserRegisterDTO;
import site.euan.bester.domain.entity.Result;
import site.euan.bester.domain.model.User;
import site.euan.bester.domain.vo.LoginVO;
import site.euan.bester.domain.vo.UserInfoVO;
import site.euan.bester.service.UserService;
import site.euan.bester.utils.JwtUtil;

import java.util.Map;

/**
 * @author EUAN
 * 用户控制层
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

}
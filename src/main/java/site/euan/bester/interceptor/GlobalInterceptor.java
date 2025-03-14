package site.euan.bester.interceptor;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import site.euan.bester.context.BaseContext;
import site.euan.bester.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 令牌校验器
 *
 * @author EUAN
 */
@Component
@Slf4j
public class GlobalInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("请求路径：{}", request.getRequestURI());
        String token = request.getHeader("Authorization");
        log.info("令牌：{}", token);
        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            log.error("令牌为空");
            return false; // 未授权
        }

        try {
            Claims claims = jwtUtil.parseJwt(token);
            Long userId = Long.valueOf(claims.get("id").toString());
            BaseContext.setCurrentId(userId); // 将用户ID存入本地上下文
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            log.error("令牌无效");
            return false; // 令牌无效
        }

        return true; // 继续处理请求
    }
}

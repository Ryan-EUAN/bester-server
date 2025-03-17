package site.euan.bester.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.*;
import site.euan.bester.interceptor.GlobalInterceptor;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private GlobalInterceptor globalInterceptor;

    // 不需要拦截的WEB路径
    private static final String[] excludeWebPaths = {
            "/api/web/get_head_info"
    };
    private static final String[] excludeDevPaths = {
            "/carousel/top", "/module/getModule", "/module/getOnline"
    };

    // 不需要拦截的登录路径
    private static final String[] excludeAuthPaths = {
            "/api/auth/user/logout",
            "/api/auth/user/login",
            "/api/auth/user/register",
            "/api/auth/user/change-password",
            "/api/auth/user/sendCode",
            "/api/auth/user/loginEmail",
            "/api/auth/user/check-token"
    };

    // 不需要拦截的Swagger路径
    private static final String[] excludeSwaggerPaths = {
            "/doc.html", "/webjars/bycdao-ui/**", "/swagger-resources/**"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns()
                .excludePathPatterns(excludeAuthPaths)
                .excludePathPatterns(excludeWebPaths)
                .excludePathPatterns(excludeSwaggerPaths)
                .excludePathPatterns(Arrays.stream(excludeDevPaths).map(s -> "/api" + s).collect(Collectors.toList()))
                .excludePathPatterns("/error")
                .excludePathPatterns("/api/ranking/getUserGoldCoin");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/api",
                HandlerTypePredicate.forAnnotation(RestController.class));
    }
}

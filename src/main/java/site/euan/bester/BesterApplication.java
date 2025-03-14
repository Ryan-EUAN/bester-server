package site.euan.bester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableRedisRepositories
@EnableCaching//注解驱动缓存
@EnableSwagger2 // Swagger2
public class BesterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BesterApplication.class, args);
    }

}

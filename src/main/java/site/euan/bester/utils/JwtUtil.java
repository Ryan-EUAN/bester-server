package site.euan.bester.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import site.euan.bester.exception.TokenException;

import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "your_secret_key"; // 秘钥
    private final Integer EXPIRATION_TIME = 3600;//过期时间

    /**
     * 生成JWT令牌
     *
     * @param userId 用户ID
     * @return 生成的JWT令牌
     */
    public String createJwt(Long userId) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME * 1000); // 设置过期时间
        return Jwts.builder()
                .setSubject("user")
                .claim("id", userId)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * 解析JWT令牌
     *
     * @param token JWT令牌
     * @return 解析后的Claims
     */
    public Claims parseJwt(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new TokenException("令牌校验已过期，请重新登录");
        }
    }
}

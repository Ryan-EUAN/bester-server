package site.euan.bester.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "your_secret_key"; // 秘钥

    /**
     * 生成JWT令牌
     *
     * @param userId 用户ID
     * @param expirationTimeInSeconds 过期时间（秒）
     * @return 生成的JWT令牌
     */
    public String createJwt(Long userId, long expirationTimeInSeconds) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTimeInSeconds * 1000); // 设置过期时间
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
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}

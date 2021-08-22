package uz.pdp.appduonotarypraktikaserver.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import uz.pdp.appduonotarypraktikaserver.entity.User;
import java.util.Date;
import java.util.StringJoiner;

@Component
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication){
        User userPrincipal = (User) authentication.getPrincipal();
        Date expiryDate = new Date(new Date().getTime() + jwtExpirationInMs);
        return Jwts.builder()
                .setSubject(userPrincipal.getId().toString())
                .claim("roles", userPrincipal.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }
}

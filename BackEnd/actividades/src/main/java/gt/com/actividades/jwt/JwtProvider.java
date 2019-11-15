package gt.com.actividades.jwt;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import gt.com.actividades.utils.AppProperty;
import gt.com.actividades.utils.UserPrinciple;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    private String jwtPassword;
    private int jwtExpiration;

    @Autowired
    public JwtProvider(AppProperty properties) {
        this.jwtPassword = properties.getJwtPassword();
        this.jwtExpiration = properties.getJwtExpiration();
    }

    public String generateJwtToken(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        Date expiration = getDateExpiration(this.jwtExpiration);
        return Jwts.builder()
        .setSubject((userPrinciple.getUsername()))
        .setIssuedAt(new Date())
        .setExpiration(expiration)
        .signWith(SignatureAlgorithm.HS512, this.jwtPassword).compact();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.jwtPassword).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            logger.error("Error validate JWT", e);
        }
        return false;
    }

    public String getUsernameFromJWtToken(String token) {
        return Jwts.parser().setSigningKey(jwtPassword).parseClaimsJws(token).getBody().getSubject();
    }

    private static Date getDateExpiration(int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, hours);
        return calendar.getTime();
    }

}
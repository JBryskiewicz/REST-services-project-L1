package pl.aeh.rest_services_project_l1.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    // TODO change later and move it to some non-public file
    private final String SECRET_KEY = "9b5ccfe9e110ee4c0911f5c11a1050fd9aa39d552f953ea2a9c1b77b451e54a09aa32cd44dd33051bd8952fe54d6e83900a7348f4a4a5bcac0cf88c9039aed44cd05592092ba5bdec311f78e31f7ac8fc8bf950902d0c10e8baca353a30df12e71975cd5fafa46504d27452c0f20cbb6520158478deaffb46be87c39716ac9d73f5bde8004b47bd76ad621b475c1c1b2d5d97943cc87398407fdb3f3f557567e23d25391de75bd2cbeae1923bdd482af8895fafe881d195e81218155980cf5f1568e2e912551d2feaacd7d079f4978528f59d0c186ea6f843951de05f34bf58dafeb6952dff53fe72a0d377ce725e368bdb4c0c06300423c19d47342316c6953";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
}

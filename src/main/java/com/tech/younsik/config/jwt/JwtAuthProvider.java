package com.tech.younsik.config.jwt;

import com.tech.younsik.constant.AuthConst;
import com.tech.younsik.constant.AuthConst.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthProvider implements InitializingBean {
    
    private final String secret;
    private final long tokenValidityInMilliseconds;
    
    private Key key;
    
    public JwtAuthProvider(
        @Value("${auth.jwt.secret}") String secret,
        @Value("${auth.jwt.expire}") long tokenValidityInMilliseconds) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds;
    }
    
    @Override
    public void afterPropertiesSet() {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.secret));
    }
    
    public String getSubjectFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }
    
    public List<AuthConst.Role> getRoleFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        
        Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(claims.get(AuthConst.AUTHORIZATION_ROLES).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        
        return authorities.stream()
            .map(grantedAuthority -> AuthConst.Role.fromJsonCode(grantedAuthority.getAuthority()))
            .collect(Collectors.toList());
    }
    
    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }
    
    private Claims getAllClaimsFromToken(String token) {
        return Jwts
            .parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
    
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    
    public String generateToken(UserDetails userDetails) {
        String authorities = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));
        
        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put(AuthConst.AUTHORIZATION_ROLES, authorities);
        return doGenerateToken(claims, userDetails.getUsername());
    }
    
    private String doGenerateToken(Claims claims, String email) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + tokenValidityInMilliseconds);
        
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(email)
            .setIssuedAt(now)
            .setExpiration(expiry)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }
    
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String subject = getSubjectFromToken(token);
        return (subject.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}

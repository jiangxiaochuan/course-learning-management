package com.github.jiangxch.courselearningmanagement.common.utils;

import com.github.jiangxch.courselearningmanagement.common.data.AuthInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

import java.util.Date;

@Data
public class JwtUtil {

    public static String HEADER = "token";

    private static String SECRET="eyJleHAiOjE1NDMyMDUyODUsInN1YiI6ImFkbWluIiwiY3JlYXRlZCI6MTU0MDYxMzI4N";

    public static Integer EXPIRATION=3600000;

    public static String generateToken(AuthInfo authInfo) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION);
        String jwt = Jwts.builder()
                .setClaims(BeanUtils.entityToMap(authInfo))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        return jwt;
    }

    public static AuthInfo getAuthInfoFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            return BeanUtils.mapToEntity(claims, AuthInfo.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static Boolean hasTokenValid(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwt).getBody();
            Date expiration = claims.getExpiration();
            return expiration.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
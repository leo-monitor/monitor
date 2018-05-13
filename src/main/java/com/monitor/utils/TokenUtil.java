package com.monitor.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @Copyist: John
 * @Decsription: 生成Token的工具类
 * @Create: 2018-5-7 22:05:08
 */
public class TokenUtil {

    @Value("${app.key}")
    private static String APP_KEY;

    private TokenUtil(){}

    public static String createJWT(String id,String issuer,String subject,long ttlMillis,String audience){

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(APP_KEY);

        Key signingKey = new SecretKeySpec(apiKeySecretBytes,signatureAlgorithm.getJcaName());

        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm,signingKey);
        //设置Token过期时间
        if (ttlMillis >= 0){
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            jwtBuilder.setExpiration(exp);
        }
        return jwtBuilder.compact();
    }

    //私钥解密token信息
    public static Claims getClaims(String jwt) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(APP_KEY))
                .parseClaimsJws(jwt).getBody();
    }

}

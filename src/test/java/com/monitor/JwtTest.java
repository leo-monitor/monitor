package com.monitor;

import com.monitor.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JwtTest {
    @Test
    public void test(){
        String userId = "732097792@monitor.com";
        String issuser = "www.monitor.com";
        String subject = "monitor";
        long ttlMillis = 1000*60;
        String auddience = "John";
        String token = TokenUtil.createJWT(userId,issuser,subject,ttlMillis,auddience);
        System.out.println(token);

        Claims claims = TokenUtil.getClaims(token);
        System.out.println("ID为"+claims.getId()+"-------"+userId.equals(claims.getId().toString()));
        System.out.println("Subject为"+claims.getSubject()+"-------"+subject.equals(claims.getSubject().toString()));
        System.out.println("Issuer（jwt发放者）为"+claims.getIssuer()+"-------"+issuser.equals(claims.getIssuer().toString()));
        System.out.println("Expiration（Token过期时间）为"+claims.getExpiration()+"分钟");
    }

}

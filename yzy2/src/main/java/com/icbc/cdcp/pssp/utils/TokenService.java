package com.icbc.cdcp.pssp.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.icbc.cdcp.pssp.dto.UserInfo;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * token 下发
 */
@Service("TokenService")
public class TokenService {
    public String getToken(UserInfo userInfo){
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60 * 60 * 1000;//一个小时有效时间
        Date end = new Date(currentTime);

        String token = "";
        token = JWT.create().withAudience(userInfo.getUserId().toString()).withIssuedAt(start).withExpiresAt(end).sign(Algorithm.HMAC256(userInfo.getPassword()));
        return token;
    }
}

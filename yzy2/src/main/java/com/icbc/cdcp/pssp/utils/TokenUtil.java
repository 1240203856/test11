package com.icbc.cdcp.pssp.utils;

import com.auth0.jwt.JWT;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class TokenUtil {
    public static String getTokenUserId(){
        String token = getRequest().getHeader("x-token"); //从http头去除token
        String userId = JWT.decode(token).getAudience().get(0);
        return userId;
    }

    public static HttpServletRequest getRequest(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }
}

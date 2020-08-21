package com.icbc.cdcp.pssp.interceptor;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.icbc.cdcp.pssp.dto.UserInfo;
import com.icbc.cdcp.pssp.interceptor.annotation.PassToken;
import com.icbc.cdcp.pssp.interceptor.annotation.UserLoginToken;
import com.icbc.cdcp.pssp.service.UserService;
import com.icbc.cdcp.pssp.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.function.ServerResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Component
public class PsspInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

        // 同源
        // session  cookie (JSESSIONID=asdlfjalksjdlfkajsldf)
        // 登录的目的：判断用户的身份
        // 登陆成功以后，后端生成session

        // key:sessionId value:user对象

//        String origin = request.getHeader("Origin");
//        if (!(origin == null || origin.equals(""))) {
//            response.addHeader("Access-Control-Allow-Origin", origin);
//        }
//        response.addHeader("Access-Control-Allow-Methods", "*");
//        String headers = request.getHeader("Access-Control-Request-Headers");
//        if (!(headers == null || headers.equals(""))) {
//            response.addHeader("Access-Control-Allow-Headers",headers);
//        }
//
//        // 登录超时
//
//        // 点开网页->登录页面->首页
//        // （自动登录）点开网页->首页
//        String token = request.getHeader("x-token");
//        UserInfoVo userInfo = (UserInfoVo) Utils.getCache(token);
//
//        if (userInfo == null) {
//            // 通过输入流writer把401等response信息写回response对象中
//            return false;
//        }
//
//        if (token == null) {
//
//            return false;
//        }
//        return true;
        String token = request.getHeader("token");
        //如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod)object;
        Method method = handlerMethod.getMethod();
        //检查是否有pastoken注释，有则跳过认证
        if(method.isAnnotationPresent(PassToken.class)){
            PassToken passToken = method.getAnnotation(PassToken.class);
            if(passToken.required()){
                return true;
            }
        }

        //检查有没有用户权限的注释
        if(method.isAnnotationPresent(UserLoginToken.class)){
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if(userLoginToken.required()){
                if(token == null){
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json;charset=UTF-8");
                    PrintWriter pw = response.getWriter();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("success","false");
                    jsonObject.put("message","无token，请重新登录");
                    pw.write(jsonObject.toString());
                    pw.flush();
                    return false;
                }

                //获取token中的userId
                String userId;
                try{
                    userId = JWT.decode(token).getAudience().get(0);
                }catch (JWTDecodeException j){
                    //返回401
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    return false;
                }
                UserInfo user = userService.findUserById(Integer.parseInt(userId));
                if(user == null){
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json;charset=UTF-8");
                    PrintWriter pw = response.getWriter();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("success","false");
                    jsonObject.put("message","用户不存在，请重新登录");
                    pw.write(jsonObject.toString());
                    pw.flush();
                    return false;
                }
                //验证token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try{
                    jwtVerifier.verify(token);
                }catch (JWTVerificationException e){
                    //返回401
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    return false;
                }
                return true;
            }
        }
        return true;
    }
}

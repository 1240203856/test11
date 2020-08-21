package com.icbc.cdcp.pssp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.icbc.cdcp.pssp.dto.UserInfo;
import com.icbc.cdcp.pssp.exception.MyException;
import com.icbc.cdcp.pssp.interceptor.annotation.UserLoginToken;
import com.icbc.cdcp.pssp.service.UserService;
import com.icbc.cdcp.pssp.utils.TokenService;
import com.icbc.cdcp.pssp.vo.ResultVo;
//import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@ResponseBody
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    @RequestMapping("/reg")
    public ResultVo reg(@RequestBody String s)
    {

        JSONObject o = JSONObject.parseObject(s);
        JSONObject obj = o.getJSONObject("data");

        ResultVo resultVo = new ResultVo();
        UserInfo ret = new UserInfo();

        ret.setUserName(obj.getString("userName"));
        ret.setGender(obj.getInteger("gender"));
        ret.setRoomNum(obj.getString("roomNum"));
        ret.setPhoneNum(obj.getString("phoneNum"));
        ret.setEmail(obj.getString("email"));
        ret.setPassword(obj.getString("password"));
        //ret.setGroupId(obj.getInteger("groupName"));
        ret.setGroupId(1);
        ret.setCompanyId(obj.getInteger("companyName"));
        ret.setDepId(obj.getInteger("depName"));
        ret.setRoleId(obj.getInteger("roleId"));
        ret.setExt1(obj.getString("answer_1"));
        ret.setExt2(obj.getString("answer_2"));
        ret.setExt3(obj.getString("answer_3"));
        // 数据库要改动！
        ret.setLineLeader(12);

        // 调用业务对象执行注册
        try {
            userService.reg(ret);
            resultVo.setState("0003");
            resultVo.setSuccess(true);
            resultVo.setMessage("注册成功");
        } catch (MyException e) {
            resultVo.setState(e.getErrorCode());
            resultVo.setSuccess(false);
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }

    @RequestMapping("/login")
    public String login(@RequestBody String s)
    {
        JSONObject obj = JSONObject.parseObject(s);
        String username = obj.getString("user");
        String password = obj.getString("pass");
        ResultVo resultVo = new ResultVo();
        // 调用业务对象执行登录
        try {
            UserInfo ret = userService.login(username,password);
            String token = tokenService.getToken(ret);
            resultVo.setState("0004");
            resultVo.setSuccess(true);
            resultVo.setMessage("登录成功");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token",token);
            jsonObject.put("userInfo",ret);
            resultVo.setData(jsonObject);
        }catch (MyException e)
        {
            resultVo.setState(e.getErrorCode());
            resultVo.setMessage(e.getErrorMsg());
            resultVo.setSuccess(false);
        }
        return JSONObject.toJSONString(resultVo);
    }

    /*
    @GetMapping("/changePassword")
    // 登录后的修改密码:postman测试通过
    public String changePassword(@PathParam("user") String username, @PathParam("oldPass") String oldPassword, @PathParam("newPass") String newPassword)
    {
        ResultVo resultVo = new ResultVo();
        try {
            userService.changePassword(username,oldPassword,newPassword);
            resultVo.setSuccess(true);
            resultVo.setState("0001");
            resultVo.setMessage("密码修改成功！");
        } catch (MyException e) {
            resultVo.setSuccess(false);
            resultVo.setMessage(e.getErrorMsg());
            resultVo.setState(e.getErrorCode());
        }
        return JSONObject.toJSONString(resultVo);
    }*/

    @RequestMapping("/changePassword")
    // 登录后的修改密码
    public String changePassword(@RequestBody String s)
    {
        JSONObject obj = JSONObject.parseObject(s);
        String username = obj.getString("user");
        String oldPassword = obj.getString("oldPass");
        String newPassword = obj.getString("newPass");

        ResultVo resultVo = new ResultVo();
        try {
            userService.changePassword(username,oldPassword,newPassword);
            resultVo.setSuccess(true);
            resultVo.setState("0001");
            resultVo.setMessage("密码修改成功！");
        } catch (MyException e) {
            resultVo.setSuccess(false);
            resultVo.setMessage(e.getErrorMsg());
            resultVo.setState(e.getErrorCode());
        }
        return JSONObject.toJSONString(resultVo);
    }

    /*
    @GetMapping("/retrievePassword")
    // 找回密码:postman测试通过
    public String retrievePassword(@PathParam("email") String email, @PathParam("quesNum") Integer quesNum, @PathParam("ans") String ans, @PathParam("pass") String pass)
    {
        ResultVo resultVo = new ResultVo();
        try {
            userService.retrievePassword(email,quesNum,ans,pass);
            resultVo.setSuccess(false);
            resultVo.setMessage("密码修改成功！");
            resultVo.setState("0004");
        }catch (MyException e)
        {
            resultVo.setSuccess(true);
            resultVo.setState(e.getErrorCode());
            resultVo.setMessage(e.getErrorMsg());
        }
        return JSONObject.toJSONString(resultVo);
    }*/

    @RequestMapping("/retrievePassword")
    // 找回密码
    public String retrievePassword(@RequestBody String s)
    {
        JSONObject obj = JSONObject.parseObject(s);
        String email = obj.getString("userName");
        Integer quesNum = obj.getInteger("quesNum");
        String ans = obj.getString("ans");
        String pass = obj.getString("pass");

        ResultVo resultVo = new ResultVo();
        try {
            userService.retrievePassword(email,quesNum,ans,pass);
            resultVo.setSuccess(false);
            resultVo.setMessage("密码修改成功！");
            resultVo.setState("0004");
        }catch (MyException e)
        {
            resultVo.setSuccess(true);
            resultVo.setState(e.getErrorCode());
            resultVo.setMessage(e.getErrorMsg());
        }
        return JSONObject.toJSONString(resultVo);
    }

    /*
    // 分页查询:postman测试通过
    @GetMapping("/selectPage")
    public String selectPage(Integer role_id,Integer ops_id)
    {

        ResultVo resultVo = new ResultVo();
        List<UserInfo> ret = userService.selectAll(role_id,ops_id);

        resultVo.setState("0001");
        resultVo.setSuccess(true);
        resultVo.setMessage("已显示当前小组/部门/中心全部成员");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",ret);
        resultVo.setData(jsonObject);

        return JSONObject.toJSONString(resultVo);
    }*/

    @RequestMapping("/selectPage")
    public String selectPage(@RequestBody String s)
    {
        JSONObject obj = JSONObject.parseObject(s);
        Integer role_id = obj.getInteger("role_id");
        Integer ops_id = obj.getInteger("ops_id");

        ResultVo resultVo = new ResultVo();
        List<UserInfo> ret = userService.selectAll(role_id,ops_id);

        resultVo.setState("0001");
        resultVo.setMessage("已显示当前小组/部门/中心全部成员");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",ret);
        resultVo.setData(jsonObject);

        return JSONObject.toJSONString(resultVo);
    }

    /*
    // 按条件进行查询:postman测试通过
    @GetMapping("/selectByInfo")
    public String selectByInfo(String username, Integer gender, Integer roomNum, String phone,
                               String email, Integer grpId, Integer depId, Integer comId, Integer roleId, String leaderName, Integer isDelete)
    {
        ResultVo resultVo = new ResultVo();
        List<UserInfo> ret =  userService.selectByInfo(username, gender, roomNum, phone, email, grpId, depId, comId, roleId, isDelete);
        resultVo.setSuccess(true);
        resultVo.setMessage("已显示满足当前条件的所有员工信息！");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",ret);
        resultVo.setData(ret);
        resultVo.setState("0001");

        return JSONObject.toJSONString(resultVo);
    }*/

    @RequestMapping("/selectByInfo")
    // 按条件进行查询:postman测试通过
    public String selectByInfo(@RequestBody String s)
    {
        JSONObject obj = JSONObject.parseObject(s);
        String username = obj.getString("user");
        Integer gender = obj.getInteger("gender");
        Integer roomNum = obj.getInteger("roomNum");
        String phone = obj.getString("phone");
        String email = obj.getString("email");
        Integer grpId = obj.getInteger("grpId");
        Integer depId = obj.getInteger("depId");
        Integer comId = obj.getInteger("comId");
        Integer roleId = obj.getInteger("roleId");
        String leaderName = obj.getString("leaderName");
        Integer isDelete = obj.getInteger("isDelete");

        ResultVo resultVo = new ResultVo();
        List<UserInfo> ret =  userService.selectByInfo(username, gender, roomNum, phone, email, grpId, depId, comId, roleId, isDelete);
        resultVo.setSuccess(true);
        resultVo.setMessage("已显示满足当前条件的所有员工信息！");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",ret);
        resultVo.setData(ret);
        resultVo.setState("0001");

        return JSONObject.toJSONString(resultVo);
    }

    /*
    // 编辑用户信息:小组长:postman测试通过
    @GetMapping("/updateUserInfo")
    public String updateUserInfo(String oldEmail,String username,Integer gender, String roomNum ,String phoneNum
    ,String email,Integer depId,Integer comId,Integer grpId ,Integer roleId, Integer status)
    {
        ResultVo resultVo = new ResultVo();
        try {
            userService.updateUserInfo(oldEmail,username,gender,roomNum , phoneNum, email,depId,comId,grpId ,roleId, status);
            resultVo.setSuccess(true);
            resultVo.setState("0001");
            resultVo.setMessage("修改成功！");
        } catch (MyException e) {
            resultVo.setMessage(e.getErrorMsg());
            resultVo.setState("0002");
            resultVo.setSuccess(false);
        }
        return JSONObject.toJSONString(resultVo);
    }*/


    // 编辑用户信息:小组长
    @RequestMapping("/updateUserInfo")
    public String updateUserInfo(@RequestBody String s)
    {
        JSONObject obj = new JSONObject();
        String oldEmail = obj.getString("oldEmail");
        String username = obj.getString("username");
        Integer gender = obj.getInteger("gender");
        String roomNum = obj.getString("roomNum");
        String phoneNum = obj.getString("phone");
        String newEmail = obj.getString("newEmail");
        Integer depId = obj.getInteger("depId");
        Integer comId = obj.getInteger("comId");
        Integer grpId = obj.getInteger("grpId");
        Integer roleId = obj.getInteger("roleId");
        Integer status = obj.getInteger("status");

        ResultVo resultVo = new ResultVo();
        try {
            userService.updateUserInfo(oldEmail,username,gender,roomNum , phoneNum, newEmail,depId,comId,grpId ,roleId, status);
            resultVo.setSuccess(true);
            resultVo.setState("0001");
            resultVo.setMessage("修改成功！");
        } catch (MyException e) {
            resultVo.setMessage(e.getErrorMsg());
            resultVo.setState("0002");
            resultVo.setSuccess(false);
        }
        return JSONObject.toJSONString(resultVo);
    }

    /*
    @GetMapping("/updateLineLeader")
    // 更新直系领导信息:postman测试通过
    public String updateLineLeader(String email, Integer lineLeader)
    {
        ResultVo resultVo = new ResultVo();
        try{
            userService.updateLineLeader(email,lineLeader);
            resultVo.setSuccess(true);
            resultVo.setState("0001");
            resultVo.setMessage("更新直系领导信息成功！");
        }catch (MyException e)
        {
            resultVo.setState(e.getErrorCode());
            resultVo.setMessage(e.getMessage());
            resultVo.setSuccess(false);
        }
        return JSONObject.toJSONString(resultVo);
    }*/

    @RequestMapping("/updateLineLeader")
    // 更新直系领导信息
    public String updateLineLeader(@RequestBody String s)
    {
        JSONObject obj = JSONObject.parseObject(s);
        String email = obj.getString("email");
        Integer lineLeader = obj.getInteger("lineLeader");

        ResultVo resultVo = new ResultVo();
        try{
            userService.updateLineLeader(email,lineLeader);
            resultVo.setSuccess(true);
            resultVo.setState("0001");
            resultVo.setMessage("更新直系领导信息成功！");
        }catch (MyException e)
        {
            resultVo.setState(e.getErrorCode());
            resultVo.setMessage(e.getMessage());
            resultVo.setSuccess(false);
        }
        return JSONObject.toJSONString(resultVo);
    }

    /*
    @GetMapping("/deleteUserInfo")
    // 删除用户信息：postman测试通过
    public String deleteUserInfo(String email)
    {

        ResultVo resultVo = new ResultVo();
        try {
            userService.deleteUserInfo(email);
            resultVo.setState("0001");
            resultVo.setMessage("删除用户信息成功！");
            resultVo.setSuccess(true);
        } catch (MyException e) {
            resultVo.setSuccess(false);
            resultVo.setMessage(e.getErrorMsg());
            resultVo.setState(e.getErrorCode());
        }

        return JSONObject.toJSONString(resultVo);
    }*/

    // 删除用户信息
    @RequestMapping("/deleteUserInfo")
    public String deleteUserInfo(@RequestBody String s)
    {
        JSONObject obj = JSONObject.parseObject(s);
        String email =  obj.getString("email");

        ResultVo resultVo = new ResultVo();
        try {
            userService.deleteUserInfo(email);
            resultVo.setState("0001");
            resultVo.setMessage("删除用户信息成功！");
            resultVo.setSuccess(true);
        } catch (MyException e) {
            resultVo.setSuccess(false);
            resultVo.setMessage(e.getErrorMsg());
            resultVo.setState(e.getErrorCode());
        }

        return JSONObject.toJSONString(resultVo);
    }

    @UserLoginToken
    @PostMapping("/getMessage")
    public String getMessage(){
        return "你已经通过验证";
    }

}
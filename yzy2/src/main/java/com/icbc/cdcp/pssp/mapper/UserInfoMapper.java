package com.icbc.cdcp.pssp.mapper;

import com.icbc.cdcp.pssp.dto.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer userId);

    /**
     * 插入用户数据
     * @param record
     * @return
     */
    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    /**
     * 根据主键查询用户数据
     * @param userId
     * @return
     */
    UserInfo selectByPrimaryKey(Integer userId);

    /**
     * 根据用户名查询用户数据
     * @param name
     * @return
     */
    UserInfo selectByUsername(String name);

    /**
     * 根据小组id查询小组名称
     * @param id
     * @return
     */
    String selectGroupNameById(@Param("groupId") Integer id);

    /**
     * 根据部门id查询部门名称
     * @param id
     * @return
     */
    String selectDepNameById(Integer id);

    /**
     * 根据中心id查询中心名称
     * @param id
     * @return
     */
    String selectComNameById(Integer id);

    /**
     * 根据用户id查询用户名
     * @param id
     * @return
     */
    String selectNameByUid(Integer id);

    /**
     * 修改密码
     * @param email：用户名
     * @param password：新密码
     * @return
     */
    Integer changePassword(String email, String password);

    /**
     * 取出密保答案
     * @param email：用户名
     * @return
     */
    UserInfo selectAnswer(String email);

    /**
     * 查询所有符合条件的数据
     * @param grp_id：小组id
     * @param dep_id：部门id
     * @param com_id：中心id
     * @return
     */
    public List<UserInfo> selectAll(@Param("grpId") Integer grp_id, @Param("depId") Integer dep_id, @Param("comId") Integer com_id);

    /**
     * 用户管理页面根据条件进行查询
     * @param username
     * @param gender
     * @param roomNum
     * @param phone
     * @param email
     * @param grpId
     * @param depId
     * @param comId
     * @param roleId
     * @param isDelete
     * @return
     */
    public List<UserInfo> selectByInfo(@Param("username") String username, Integer gender, Integer roomNum, String phone,
                                       String email,@Param("grpId") Integer grpId,@Param("depId") Integer depId,@Param("comId") Integer comId, Integer roleId,Integer isDelete);

    int updateUserInfo(String oldEmail,String username,Integer gender, String roomNum ,String phoneNum,String newEmail,Integer depId,Integer comId,Integer grpId ,Integer roleId, Integer status);

    int updateLineLeader(String email, Integer lineLeader);

    /**
     * 删除用户信息
     * @param email
     * @return
     */
    int deleteUserInfo (String email);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}
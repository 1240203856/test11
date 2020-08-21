package com.icbc.cdcp.pssp.dto;

import java.util.Date;

public class UserInfo {
    private Integer userId;
    private String userName;
    private Integer gender;
    private String roomNum;
    private String phoneNum;
    private String email;
    private String salt;
    private String password;
    private Integer groupId;
    private Integer companyId;
    private Integer depId;
    private String groupName;
    private String companyName;
    private String depName;
    private Integer roleId;
    private String roleName;
    private Integer status;
    private Integer lineLeader;
    private String leaderName;
    private Date updateTime;
    private Date createTime;
    private String ext1;
    private String ext2;
    private String ext3;

    /**
     * 所有属性
     * @param userId
     * @param userName
     * @param gender
     * @param roomNum
     * @param phoneNum
     * @param email
     * @param salt
     * @param password
     * @param groupId
     * @param companyId
     * @param depId
     * @param groupName
     * @param companyName
     * @param depName
     * @param roleId
     * @param roleName
     * @param status
     * @param lineLeader
     * @param leaderName
     * @param updateTime
     * @param createTime
     * @param ext1
     * @param ext2
     * @param ext3
     */

    public UserInfo(String userName, Integer gender, String roomNum, String phoneNum, String email, Integer groupId, Integer companyId, Integer depId, Integer roleId, Integer status, Integer lineLeader, Date updateTime, Date createTime, String ext1, String ext2, String ext3, String roleName,
                    String groupName, String depName, String companyName, String leaderName )
    {
        //this.userId = userId;
        this.userName = userName;
        this.gender = gender;
        this.roomNum = roomNum;
        this.phoneNum = phoneNum;
        this.email = email;
        //this.salt = salt;
        //this.password = password;
        this.groupId = groupId;
        this.companyId = companyId;
        this.depId = depId;
        this.roleId = roleId;
        this.status = status;
        this.lineLeader = lineLeader;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.ext1 = ext1;
        this.ext2 = ext2;
        this.ext3 = ext3;
        this.roleName = roleName;
        this.groupName = groupName;
        this.depName = depName;
        this.companyName = companyName;
        this.leaderName = leaderName;
    }

    public UserInfo(Integer userId, String userName, Integer gender, String roomNum, String phoneNum, String email, String salt, String password, Integer groupId, Integer companyId, Integer depId, Integer roleId, Integer status, Integer lineLeader, Date updateTime, Date createTime, String ext1, String ext2, String ext3) {
        this.userId = userId;
        this.userName = userName;
        this.gender = gender;
        this.roomNum = roomNum;
        this.phoneNum = phoneNum;
        this.email = email;
        this.salt = salt;
        this.password = password;
        this.groupId = groupId;
        this.companyId = companyId;
        this.depId = depId;
        this.roleId = roleId;
        this.status = status;
        this.lineLeader = lineLeader;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.ext1 = ext1;
        this.ext2 = ext2;
        this.ext3 = ext3;
    }

    public UserInfo(String ext_1, String ext_2, String ext_3)
    {
        this.ext1=ext_1;
        this.ext2=ext_2;
        this.ext3=ext_3;
    }

    public UserInfo() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum == null ? null : roomNum.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLineLeader() {
        return lineLeader;
    }

    public void setLineLeader(Integer lineLeader) {
        this.lineLeader = lineLeader;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", gender=" + gender +
                ", roomNum='" + roomNum + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", salt='" + salt + '\'' +
                ", password='" + password + '\'' +
                ", groupId=" + groupId +
                ", companyId=" + companyId +
                ", depId=" + depId +
                ", groupName='" + groupName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", depName='" + depName + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", status=" + status +
                ", lineLeader=" + lineLeader +
                ", leaderName='" + leaderName + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", ext1='" + ext1 + '\'' +
                ", ext2='" + ext2 + '\'' +
                ", ext3='" + ext3 + '\'' +
                '}';
    }
}

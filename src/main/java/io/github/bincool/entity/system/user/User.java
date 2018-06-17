/**
 * Filename: User.java
 * Package: io.github.bincool.entity.system.user
 * Copyright: Wchy-bingV All Rights Reserved.QQ:891946049.
 * Description: Teee.java
 * Author: wchy.
 * Date: 2018/2/6 14:00.
 * Content: 新增.
 * Version: V1.0.
 */
package io.github.bincool.entity.system.user;

import java.io.Serializable;
import com.google.gson.Gson;

/**
 * 用户实体bean.
 * User实体bean.
 */
public class User implements Serializable
{

    /**
    * 用户ID.
    */
    private String userId ;

    /**
    * 登录帐号.
    */
    private String loginName ;

    /**
    * 登录密码.
    */
    private String loginPassword ;

    /**
     * 组织结构ID.
     */
    private String organizationId;

    /**
     * 次级组织结构ID.
     */
    private String subOrganizationId;

    /**
     * 用户类型，当type为123时只能关联一个角色.
     */
    private String type;

    /**
    * 用户姓名.
    */
    private String username ;

    /**
    * 手机号.
    */
    private String mobile ;

    /**
    * 电子邮箱.
    */
    private String email ;

    /**
    * 登录IP.
    */
    private String loginIp ;

    /**
    * 登录时间.
    */
    private String loginTime ;

    /**
    * 登录次数.
    */
    private String loginCount ;

    /**
    * 用户状态. 0-- 正常状态  1-- 移除态
    */
    private String status = "0";

    /**
    * 创建时间.
    */
    private String createTime ;

    /**
    * 获取userId.
    * @return
    *    用户ID.
    */
    public String getUserId()
    {
        return userId;
    }

    /**
    * 设置userId.
    * @param userId
    *   用户ID.
    */
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    /**
    * 获取loginName.
    * @return
    *    登录帐号.
    */
    public String getLoginName()
    {
        return loginName;
    }

    /**
    * 设置loginName.
    * @param loginName
    *   登录帐号.
    */
    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    /**
    * 获取loginPassword.
    * @return
    *    登录密码.
    */
    public String getLoginPassword()
    {
        return loginPassword;
    }

    /**
    * 设置loginPassword.
    * @param loginPassword
    *   登录密码.
    */
    public void setLoginPassword(String loginPassword)
    {
        this.loginPassword = loginPassword;
    }

    /**
     * 获取组织结构ID.
     * @return
     */
    public String getOrganizationId()
    {
        return organizationId;
    }

    /**
     * 设置组织结构ID.
     * @param organizationId
     */
    public void setOrganizationId(String organizationId)
    {
        this.organizationId = organizationId;
    }

    /**
     * 获取子组织结构ID.
     * @return
     */
    public String getSubOrganizationId()
    {
        return subOrganizationId;
    }

    /**
     * 设置子组织结构ID.
     * @param subOrganizationId
     */
    public void setSubOrganizationId(String subOrganizationId)
    {
        this.subOrganizationId = subOrganizationId;
    }

    /**
     * 获取用户类型
     * @return
     */
    public String getType()
    {
        return type;
    }

    /**
     * 设置子组织结构ID.
     * @param type
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
    * 获取username.
    * @return
    *    用户姓名.
    */
    public String getUsername()
    {
        return username;
    }

    /**
    * 设置username.
    * @param username
    *   用户姓名.
    */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
    * 获取mobile.
    * @return
    *    手机号.
    */
    public String getMobile()
    {
        return mobile;
    }

    /**
    * 设置mobile.
    * @param mobile
    *   手机号.
    */
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    /**
    * 获取email.
    * @return
    *    电子邮箱.
    */
    public String getEmail()
    {
        return email;
    }

    /**
    * 设置email.
    * @param email
    *   电子邮箱.
    */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
    * 获取loginIp.
    * @return
    *    登录IP.
    */
    public String getLoginIp()
    {
        return loginIp;
    }

    /**
    * 设置loginIp.
    * @param loginIp
    *   登录IP.
    */
    public void setLoginIp(String loginIp)
    {
        this.loginIp = loginIp;
    }

    /**
    * 获取loginTime.
    * @return
    *    登录时间.
    */
    public String getLoginTime()
    {
        return loginTime;
    }

    /**
    * 设置loginTime.
    * @param loginTime
    *   登录时间.
    */
    public void setLoginTime(String loginTime)
    {
        this.loginTime = loginTime;
    }

    /**
    * 获取loginCount.
    * @return
    *    登录次数.
    */
    public String getLoginCount()
    {
        return loginCount;
    }

    /**
    * 设置loginCount.
    * @param loginCount
    *   登录次数.
    */
    public void setLoginCount(String loginCount)
    {
        this.loginCount = loginCount;
    }

    /**
    * 获取status.
    * @return
    *    用户状态.
    */
    public String getStatus()
    {
        return status;
    }

    /**
    * 设置status.
    * @param status
    *   用户状态.
    */
    public void setStatus(String status)
    {
        this.status = status;
    }

    /**
    * 获取createTime.
    * @return
    *    创建时间.
    */
    public String getCreateTime()
    {
        return createTime;
    }

    /**
    * 设置createTime.
    * @param createTime
    *   创建时间.
    */
    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    @Override
    public String toString()
    {
        return new Gson().toJson(this);
    }

}
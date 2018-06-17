/**
 * Filename: ShiroCasRealm.java
 * Package: io.github.bincool.sso.shiro.web.realm
 * Copyright: Wchy-bingV All Rights Reserved.QQ:891946049.
 * Description: ShiroCasRealm.java
 * Author: wchy.
 * Date: 2018/6/17 21:03.
 * Content: 新增.
 * Version: V1.0.
 */
package io.github.bincool.sso.shiro.web.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Description: 一句话描述该类.
 * </p>
 * <p>
 * 详细描述.
 * </p>
 * Author: wchy.
 * Since: 2018/6/17.21:03.
 */
public class ShiroCasRealm extends AuthorizingRealm
{

    @Value("${shiro.cas.service}")
    private String shiroCasServiceUrl;

    @Value("${cas.serverUrlPrefix}")
    private String casServerUrlPrefix;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        AttributePrincipal principal = AssertionHolder.getAssertion().getPrincipal();
        if (null != principal)
        {
            Map<String, Object> attributes = principal.getAttributes();
            if (attributes.size() > 0)
            {
//                List<String> roles = CommonUtils.arrayStringtoArrayList((String)attributes.get("roles"));
                List<String> roles = null;
                //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                //用户的角色集合
                info.addRoles(roles);
                //用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的一行可以不要
                //info.addStringPermissions(user.getPermissionList());
            }
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        CasToken casToken = (CasToken) token;
        if (null == token)
        {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return null;
        }

        String ticket = (String) casToken.getCredentials();
        if (!StringUtils.hasText(ticket))
        {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return null;
        }

        Cas20ServiceTicketValidator cas20ServiceTicketValidator = new Cas20ServiceTicketValidator(casServerUrlPrefix);
        cas20ServiceTicketValidator.setEncoding("utf-8");
        TicketValidator ticketValidator = cas20ServiceTicketValidator;
        try
        {
            Assertion casAssertion = ticketValidator.validate(ticket, shiroCasServiceUrl);
            AttributePrincipal casPrincipal = casAssertion.getPrincipal();
            String userId = casPrincipal.getName();
            List principals = new ArrayList();
            if (null != casPrincipal)
            {
                Map<String, Object> attributes = casPrincipal.getAttributes();
                principals.add(userId);
                principals.add(attributes);
            }

            PrincipalCollection principalCollection = new SimplePrincipalCollection(principals, getName());
            return new SimpleAuthenticationInfo(principalCollection, ticket);
        }
        catch (TicketValidationException e)
        {
            throw new CasAuthenticationException((new StringBuilder()).append("Unable to validate ticket [").append(ticket).append("]").toString(), e);
        }

    }

    @Override
    protected void onInit()
    {
        super.onInit();
        this.setAuthenticationTokenClass(CasToken.class);
    }

}

/**
 * Filename: AccessAuthenticationFilter.java
 * Package: io.github.bincool.sso.shiro.web.filter.authc
 * Copyright: Wchy-bingV All Rights Reserved.QQ:891946049.
 * Description: AccessAuthenticationFilter.java
 * Author: wchy.
 * Date: 2018/6/18 0:00.
 * Content: 新增.
 * Version: V1.0.
 */
package io.github.bincool.sso.shiro.web.filter.authc;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p>
 * Description: 一句话描述该类.
 * </p>
 * <p>
 * 详细描述.
 * </p>
 * Author: wchy.
 * Since: 2018/6/18.0:00.
 */
public class AccessAuthenticationFilter extends FormAuthenticationFilter
{

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
    {
        Subject subject = this.getSubject(request, response);
        // 之前登陆的用户.
        Object user = subject.getPrincipal();
        if (null != user && null != subject.getSession(false).getAttribute("logoutRequest"))
        {
            // 如果有过退出标识，则先清除shiro的安全session.
            subject.getSession(false).removeAttribute("logoutRequest");
            subject.logout();
        }

        return super.isAccessAllowed(request, response, mappedValue) || (!isLoginRequest(request, response) && isPermissive(mappedValue));
    }

}

/**
 * Filename: CasLogoutFilter.java
 * Package: io.github.bincool.sso
 * Copyright: Wchy-bingV All Rights Reserved.QQ:891946049.
 * Description: CasLogoutFilter.java
 * Author: wchy.
 * Date: 2018/6/17 20:59.
 * Content: 新增.
 * Version: V1.0.
 */
package io.github.bincool.sso.shiro.cas;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * Description: 一句话描述该类.
 * </p>
 * <p>
 * 详细描述.
 * </p>
 * Author: wchy.
 * Since: 2018/6/17.20:59.
 */
public class CasLogoutFilter extends AdviceFilter
{
    private static final Logger log = LoggerFactory.getLogger(CasLogoutFilter.class);

    private static final SingleSignOutHandler HANDLER = new SingleSignOutHandler();

    private SessionManager sessionManager;

    public void setSessionManager(SessionManager sessionManager)
    {
        this.sessionManager = sessionManager;
    }
    /**
     * 如果请求中包含了ticket参数，记录ticket和sessionID的映射
     * 如果请求中包含logoutRequest参数，标记session为无效
     * 如果session不为空，且被标记为无效，则登出
     *
     * @param request  the incoming ServletRequest
     * @param response the outgoing ServletResponse
     * @return 是logoutRequest请求返回false，否则返回true
     * @throws Exception if there is any error.
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception
    {
        HttpServletRequest req = (HttpServletRequest)request;
        if (HANDLER.isTokenRequest((HttpServletRequest)req))
        {
            //通过浏览器发送的请求，链接中含有token参数，记录token和sessionID
            HANDLER.recordSession(req);
            return true;
        }
        else if (HANDLER.isLogoutRequest(req))
        {

            // Do not continue up filter chain
            //cas服务器发送的请求，链接中含有logoutRequest参数，在之前记录的session中设置logoutRequest参数为true
            //因为Subject是和线程是绑定的，所以无法获取登录的Subject直接logout
            HANDLER.invalidateSession(req,sessionManager);

            return true;
        }
        else
        {
            log.trace("Ignoring URI " + req.getRequestURI());
        }

        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        Session session = subject.getSession(false);
        if (null != session && null != session.getAttribute(HANDLER.getLogoutParameterName()))
        {
            try
            {
                subject.logout();
            }
            catch (SessionException ise)
            {
                log.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
            }
        }
        return true;
    }
}

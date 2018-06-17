/**
 * Filename: LoginHandlerInterceptor.java
 * Package: io.github.bincool.interceptor
 * Copyright: Wchy-bingV All Rights Reserved.QQ:891946049.
 * Description: LoginHandlerInterceptor.java
 * Author: wchy.
 * Date: 2018/3/8 9:40.
 * Content: 新增.
 * Version: V1.0.
 */
package io.github.bincool.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.bincool.entity.system.user.User;
import io.github.bincool.util.common.Const;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * <p>
 * Description: 登录用户资源请求拦截器.
 * </p>
 * <p>
 * 详细描述.
 * </p>
 * Author: wchy.
 * Since: 2018/3/8.9:40.
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter
{

	/**
	 * 日志对象.
	 */
	private static final Log LOGGER = LogFactory.getLog(LoginHandlerInterceptor.class);

	/**
	 * 不鉴权URL:不对匹配该值的访问路径拦截（正则）.
	 */
	private String uncheckUrls;

	public String getUncheckUrls()
	{
		return uncheckUrls;
	}

	public void setUncheckUrls(List<String> uncheckUrls)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(".*(");
		for (String url : uncheckUrls)
		{
			sb.append("(").append(url).append(")|");
		}
		sb.delete(sb.length() - 1, sb.length());
		sb.append(").*");
		this.uncheckUrls = sb.toString();
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		String path = request.getServletPath();
		LOGGER.info("The request url is : " + path);

		if(path.matches(getUncheckUrls()))
		{
			return true;
		}
		else
		{
			// 1 判断是否为ajax请求，默认不是
			boolean isAjaxRequest = false;
			if(!StringUtils.isBlank(request.getHeader("x-requested-with")) && request.getHeader("x-requested-with").equals("XMLHttpRequest"))
			{
				isAjaxRequest = true;
			}

			// 2 shiro管理的session.
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			PrincipalCollection princialCollection = subject.getPrincipals();
			User user = (User)session.getAttribute(Const.SESSION_USER);

			// 3 处理session超时和非法访问.
			if (null == user && null == subject.getPrincipal())
			{
				if(isAjaxRequest)
				{
					// 如果是ajax请求，则不是跳转页面，使用response返回结果.
					response.sendError(518);
					response.setHeader("sessionstatus", "session timeout!");
				}
				else
				{
					String url = request.getContextPath() + Const.LOGIN_OUT;
					response.sendRedirect(url);
				}
				return false;
			}
			else
			{
				// 4 用户相关的业务session创建.
				if (null == user)
				{
					// 创建用户相关的业务session.
					List principals = princialCollection.asList();
					// 这里获取到的list有两个元素，一个是cas返回来的用户名，举例是aaa，一个是cas返回的更多属性的map对象，举例是{uid:aaa,username:aaa,email:aaa}.
					// 通过principals.get(1)来获得属性集合的map对象.
					Map<String,String> attributes = (Map<String,String>) principals.get(1);
					if (null != principals)
					{
						String userInfo = attributes.get("userInfo");
						user = new Gson().fromJson(userInfo, User.class);

						if (null != user)
						{
							session.setAttribute(Const.SESSION_USER, user);
						}
					}
					else
					{
						if(isAjaxRequest)
						{
							// 如果是ajax请求，则不是跳转页面，使用response返回结果.
							response.sendError(518);
							response.setHeader("sessionstatus", "session timeout!");
						}
						else
						{
							String url = request.getContextPath() + Const.LOGIN_OUT;
							response.sendRedirect(url);
						}
						return false;
					}
				}

				return true;
			}
		}
	}

}

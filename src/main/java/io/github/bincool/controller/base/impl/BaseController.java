/**
 * Filename: BaseController.java
 * Package: io.github.bincool.controller.base.impl
 * Copyright: Wchy-bingV All Rights Reserved.QQ:891946049.
 * Description: BaseController.java
 * Author: wchy.
 * Date: 2017/12/4 22:30.
 * Content: 新增.
 * Version: V1.0.
 */
package io.github.bincool.controller.base.impl;

import io.github.bincool.entity.system.user.User;
import io.github.bincool.util.common.Const;
import io.github.bincool.controller.base.IBaseController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * Description: 控制层基类.
 * </p>
 * <p>
 * 详细描述.
 * </p>
 * Author: wchy.
 * Since: 2017/12/4.22:30.
 */
public class  BaseController implements IBaseController
{

    /**
     * 日志对象.
     */
    protected Log LOGGER = LogFactory.getLog(this.getClass());

    /**
     * UID.
     */
    private static final long serialVersionUID = 6357869213649815390L;

    /**
     * 得到ModelAndView.
     * @return
     */
    public ModelAndView getModelAndView()
    {
        return new ModelAndView();
    }

    /**
     * 得到request对象
     * @return
     */
    public HttpServletRequest getRequest()
    {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    protected User getSessionUser(){
        //shiro管理的session
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        return  	(User) session.getAttribute(Const.SESSION_USER);
    }

    /**
     * 返回shiro管理的session
     * @return
     */
    protected Session getShiroSession(){
        Subject currentUser = SecurityUtils.getSubject();
        return currentUser!=null?currentUser.getSession():null;
    }

    /**
     *
     * @param logger
     * @param interfaceName
     */
    public void logBefore(Log logger, String interfaceName)
    {
        logger.info("");
        logger.info("start");
        logger.info(interfaceName);
    }

    /**
     *
     * @param logger
     */
    public void logAfter(Log logger)
    {
        logger.info("end");
        logger.info("");
    }

}

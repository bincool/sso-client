/**
 * Filename: IndexController.java
 * Package: io.github.bincool.controller.system.index
 * Copyright: Wchy-bingV All Rights Reserved.QQ:891946049.
 * Description: IndexController.java
 * Author: wchy.
 * Date: 2017/12/14 22:28.
 * Content: 新增.
 * Version: V1.0.
 */
package io.github.bincool.controller.system.index;

import io.github.bincool.controller.base.impl.BaseController;
import io.github.bincool.entity.system.user.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * Description: 系统首页.
 * </p>
 * <p>
 * 详细描述.
 * </p>
 * Author: wchy.
 * Since: 2017/12/4.22:28.
 */
@Controller
public class
IndexController extends BaseController
{

    /**
     * 用户认证-登录后台首页.
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/index")
    public ModelAndView userAuth()throws Exception
    {
        ModelAndView mv = this.getModelAndView();

        // shiro管理的session.
        Subject subject = SecurityUtils.getSubject();
        if (null != subject.getPrincipal())
        {
            // session不为空，且重新刷新session成功直接跳转到首页.
            mv.setViewName("views/welcome");
        }
        else
        {
            // 直接跳转到登录页面.
            mv.setViewName("redirect:logout");
        }
        return mv;
    }

    /**
     * 检查用户登录session.
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/checkSession")
    public boolean checkSession()
    {
        boolean ret = true;

        try
        {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (null != session && null != session.getAttribute("logoutRequest"))
            {
                try
                {
                    subject.logout();
                }
                catch (SessionException ise)
                {
                    LOGGER.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
                }
                ret = false;
            }
            else
            {
                User user = getSessionUser();
                if (null == user && null == subject.getPrincipal())
                {
                    ret = false;
                }
            }
        }
        catch(Exception e)
        {
            ret = false;
        }

        return ret;
    }

}

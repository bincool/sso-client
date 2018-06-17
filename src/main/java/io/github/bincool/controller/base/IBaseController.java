/**
 * Filename: IBaseController.java
 * Package: io.github.bincool.controller.base
 * Copyright: Wchy-bingV All Rights Reserved.QQ:891946049.
 * Description: IBaseController.java
 * Author: wchy.
 * Date: 2017/12/4 22:30.
 * Content: 新增.
 * Version: V1.0.
 */
package io.github.bincool.controller.base;

import org.apache.commons.logging.Log;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * Description: 控制层基类的接口类.
 * </p>
 * <p>
 * 详细描述.
 * </p>
 * Author: wchy.
 * Since: 2017/12/4.22:35.
 */
public interface IBaseController
{


    /**
     * 得到ModelAndView.。
     * @return
     */
    ModelAndView getModelAndView();

    /**
     * 得到request对象.
     * @return
     */
    HttpServletRequest getRequest();

    /**
     * 调用前日志.
     * @param logger
     * @param interfaceName
     */
    void logBefore(Log logger, String interfaceName);

    /**
     * 调用后日志.
     * @param logger
     */
    void logAfter(Log logger);

}

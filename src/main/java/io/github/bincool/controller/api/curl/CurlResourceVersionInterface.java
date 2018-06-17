/**
 * Filename: CurlResourceVersionInterface.java
 * Package: io.github.bincool.controller.api.curl
 * Copyright: Wchy-bingV All Rights Reserved.QQ:891946049.
 * Description: CurlResourceVersionInterface.java
 * Author: wchy.
 * Date: 2018/3/9 10:34.
 * Content: 新增.
 * Version: V1.0.
 */
package io.github.bincool.controller.api.curl;

import io.github.bincool.controller.base.impl.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;

/**
 * <p>
 * Description: curl命令：临时修改静态资源版本号.
 * </p>
 * <p>
 * 详细描述.
 * </p>
 * Author: wchy.
 * Since: 2018/3/9.10:34.
 */
@Controller
@RequestMapping(value = "/interface/curl")
public class CurlResourceVersionInterface extends BaseController
{

    /**
     * 获取servletContext.
     */
    @Autowired
    private ServletContext servletContext;

    /**
     * 刷新静态资源版本号.
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/flushResourceVersion")
    public boolean flushResourceVersion()
    {
        boolean ret = true;
        try
        {
            servletContext.setAttribute("resourceVersion", System.currentTimeMillis());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ret = false;
        }

        return ret;
    }

}

/**
 * Filename: ResourceVersionExposer.java
 * Package: io.github.bincool.util.system
 * Copyright: Wchy-bingV All Rights Reserved.QQ:891946049.
 * Description: ResourceVersionExposer.java
 * Author: wchy.
 * Date: 2018/3/9 9:22.
 * Content: 新增.
 * Version: V1.0.
 */
package io.github.bincool.util.system;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * <p>
 * Description: 静态资源版本号.
 * </p>
 * <p>
 * 详细描述.
 * </p>
 * Author: wchy.
 * Since: 2018/3/9.9:22.
 */
public class ResourceVersionExposer implements ServletContextAware
{

    // 不对匹配该值的访问路径拦截（正则）.
    @Value("#{configProperties['static.resource.version']}")
    private String STATIC_RESOURCE_VERSION;

    /**
     * servletContext.
     */
    private ServletContext servletContext;

    /**
     * 设置servletContext.
     * @param servletContext
     */
    @Override
    public void setServletContext(ServletContext servletContext)
    {
        this.servletContext = servletContext;
    }

    /**
     * 获取servletContext.
     * @return
     */
    public ServletContext getServletContext()
    {
        return servletContext;
    }

    /**
     * 初始化.
     */
    public void init()
    {
        // 初始化静态资源版本号.
        initResourceVersion();
    }

    /**
     * 初始化静态资源版本号.
     */
    private void initResourceVersion()
    {
        String resourceVersion = "1.0";
        if (StringUtils.isNotEmpty(STATIC_RESOURCE_VERSION))
        {
            resourceVersion = STATIC_RESOURCE_VERSION;
        }
        getServletContext().setAttribute("resourceVersion", resourceVersion);
    }

}

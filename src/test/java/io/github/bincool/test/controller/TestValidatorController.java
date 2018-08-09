/**
 * Filename: TestTemplateController.java
 * Package: io.github.bincool.test.controller
 * Copyright: Wchy-bingV All Rights Reserved.QQ:891946049.
 * Description: TestTemplateController.java
 * Author: wchy.
 * Date: 2017/9/10 0:45.
 * Content: 新增.
 * Version: V1.0.
 */
package io.github.bincool.test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * <p>
 * Description: 测试controller.
 * </p>
 * <p>
 * 详细描述：WebAppConfiguration需要引入，并且需要Add Server Libraries.
 * </p>
 * Author: wchy.
 * Since: 2017/9/10.0:45.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/spring-context-mybatis.xml", "classpath:spring/spring-context-shiro.xml", "classpath:spring/spring-mvc.xml", "classpath:spring/spring-context-redis.xml"})
public class TestValidatorController
{

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before()
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void test() throws Exception
    {

        // 通过mockMvc对我们的系统的Controller进行单元测试.
        String responseString = mockMvc.perform(
                // 请求的url,请求的方法是get.
                get("/test/save")
                        // 数据格式.
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        // 添加参数.
                        .param("id","1")
                        .param("stringTest", "stringTest")
        )
                // 添加ResultMatcher验证规则.
                .andExpect(status().isOk())
                // 添加ResultHandler结果处理器.
                .andDo(print())
                // 返回相应的MvcResult.
                .andReturn()
                // 获取响应.
                .getResponse()
                // 将相应的数据转换为字符串.
                .getContentAsString();
        System.out.println("-----返回的json = " + responseString);
    }

}

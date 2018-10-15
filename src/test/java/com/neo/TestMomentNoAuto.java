package com.neo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

/**
 **不使用AutoConfigureMockMvc
 ** 手动注入MockMvc实例
 * @author sinky
 */
public class TestMomentNoAuto extends BaseTest {

    private MockMvc mvc;
    
    @Autowired
    private WebApplicationContext context;
    
    @Before
    public void setupMockMvc() throws Exception {
    	mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }   
    
    /**
     *	moment接口测试 权限验证 带参数
     * @throws Exception
     */
    @Test
    @Description(value = "动态测试")
    public void getTestNoAuth() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.get("/pet/moment").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();
        System.err.println(response.getContentAsString());
    }

    /**
     *	moment接口测试 权限验证 带参数
     * @throws Exception
     */
    @Test
    @Description(value = "动态")
    public void getTest() throws Exception {
        //header token
        String tokenAuthorization = "1vd2d9a6c97513e285d8b9c48aeed5bc4a76a75311cd21d316854c401dcc6f40029f6d9c625c7f292f4d3cec44e9576bc3";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cursor", "0");
        params.add("limit", "10");
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.get("/pet/moment").accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .header("Authorization", tokenAuthorization).params(params))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();

        System.out.println(response.getContentAsString());
    }
    
    @Test
    @Description(value = "post测试")
    public void postTest() throws Exception{
    	
    }
}

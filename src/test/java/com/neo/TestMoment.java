package com.neo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.entity.Moment;

public class TestMoment extends BaseTest {

    @Autowired
    private MockMvc mvc;
    
    
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
	 * 1、mockMvc.perform执行一个请求。
	 * 2、MockMvcRequestBuilders.get("XXX")构造一个请求。
	 * 3、ResultActions.param添加请求传值
	 * 4、ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型
	 * 5、ResultActions.andExpect添加执行完成后的断言。
	 * 6、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情
	 	*   比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
	 * 5、ResultActions.andReturn表示执行完成后返回相应的结果。
	 */
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
                MockMvcRequestBuilders
                		.get("/pet/moment")   // 根据URL获取一个get请求方式的MockHttpServletRequestBuilder 
                		.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)//指定请求的Accept头信息
                        .header("Authorization", tokenAuthorization)//添加头信息
                        .params(params)) // 请求参数
                .andDo(MockMvcResultHandlers.print()) // 添加结果处理器，用于对验证成功后执行的动作，如输出下请求/结果信息用于调试；
                .andExpect(MockMvcResultMatchers.status().isOk())//添加验证断言，判断请求后的结果是否符合预期
                .andReturn()	//  返回验证成功后的MVCResult，用于自定义验证/下一步的异步处理；
                .getResponse();
        System.out.println(response.getContentAsString());
    }
    
    /**
     * post测试
     * @throws Exception
     */
    @Test
    @Description(value = "post测试")
    public void postTest() throws Exception{
    	//ObjectMapper mapper = new ObjectMapper();
    	//Moment moment = new Moment();
    	//moment.setMomentId(772923037L);
    	//moment.setAccessCnt(22222);
    	MultiValueMap<String, String> params = new  LinkedMultiValueMap<>();
    	params.add("momentId","772923037");
    	params.add("accessCnt","22222");
    	MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/pet/add_moment")
    			//.content(mapper.writeValueAsString(moment))
    			.params(params)
    			.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
    	.andDo(MockMvcResultHandlers.print())
    	.andExpect(MockMvcResultMatchers.status().isOk())
    	.andReturn().getResponse();
    	System.err.println(response.getContentAsString());
    }
}

package com.neo;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
/**
 *   注入一个MockMvc实例；
 *	注入web环境的ApplicationContext容器：
 *	mvc = MockMvcBuilders.webAppContextSetup(context).build();
 */
@AutoConfigureMockMvc  
public class BaseTest {

	public BaseTest() {
		super();
	}

}
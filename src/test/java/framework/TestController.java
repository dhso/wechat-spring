package framework;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.minws.util.test.ConcurrencyTestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml",	"classpath:spring-*.xml" })
public class TestController {
	static List list = Collections.synchronizedList(new ArrayList());
	static Integer threadNumber=2000;
	
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	//@Test
	public void testMethod() throws Exception {
		mockMvc.perform(
				post("/test/testMethod.do").characterEncoding("UTF-8")
						.contentType(MediaType.ALL)).andExpect(status().isOk())
				.andDo(print()).andReturn();
	}

	@Test
	public void testMethodParam() throws Exception {
		mockMvc.perform(
				post("/test/testMethodParam.do").contentType(MediaType.ALL)
						.param("param", "参数")).andExpect(status().isOk())
				.andDo(print()).andReturn();
	}
	//@Test
	public void testConcurrency() throws Exception {
		
		List<Runnable> tasks = new ArrayList<Runnable> (threadNumber);
        for(int i = 0; i < threadNumber; i++) {
            tasks.add(new Runnable() {
                 
                //@Override
                public void run() {
                    
                    	try {
							mockMvc.perform(
									post("/test/testMethodParam.do").contentType(MediaType.ALL)
											.param("param", "参数")).andExpect(status().isOk())
									.andDo(print()).andReturn();
							list.add(new Object());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                   
                }
                 
            });
        }
        //参数分别是测试任务名、要运行的任务、最大允许执行总时间和线程池最大大小
        ConcurrencyTestUtil.assertConcurrent("1024tasks", tasks, 5, 20);
        System.out.println(list.size()==threadNumber);
		
	}
}
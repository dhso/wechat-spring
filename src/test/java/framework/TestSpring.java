package framework;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:spring-*.xml" })
public class TestSpring{
	/*
	private static final Logger logger = Logger.getLogger(TestMybatis.class);
	
	@Autowired
	private GoodsServiceImpl goodsService;
		
	@Test
	public void test2() throws Exception{
		System.out.println("///////////////////////////////////////////////////");
		Goods g=goodsService.selectByPrimaryKey(1L);
		System.out.println(g.getName());
		//logger.info(JSON.toJSONStringWithDateFormat(listAll, "yyyy-MM-dd HH:mm:ss"));
		//BeanUtils.copyProperties(source, target);
		
	}
	*/
	@Test
	public void test2() throws Exception{
		System.out.println("/////");
		
	}
}

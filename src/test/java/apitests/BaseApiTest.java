package apitests;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import service.RestService;

public class BaseApiTest {
	protected static Logger log = LogManager.getLogger(BaseApiTest.class);
	RestService restService = new RestService();

	@BeforeMethod
	public void testSetUp(Method method) {
		log.info("Test starts: [" + method.getName() + "]");
	}

	@AfterMethod
	public void testQuite(Method method) {
		log.info("Test finished: [" + method.getName() + "]");
	}
}

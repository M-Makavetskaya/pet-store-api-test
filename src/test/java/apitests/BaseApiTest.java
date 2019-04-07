package apitests;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;

import service.RestService;
import utility.Log;

public class BaseApiTest {
    protected static Logger Log = Logger.getLogger(BaseApiTest.class.getName());

	RestService restService = new RestService();

	@BeforeMethod
	public void testSetUp(Method method) {
	Log.info(method.getName() + ": Test is Starting...");
	}
}

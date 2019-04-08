package com.petstore.apitests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;

import com.petstore.service.PetStoreService;
import com.petstore.service.RestService;
import com.petstore.testlistener.TestListener;
@Listeners(TestListener.class)
public class BaseApiTest {
	protected static Logger log = LogManager.getLogger(BaseApiTest.class);
	protected PetStoreService petStoreService = new PetStoreService();
}

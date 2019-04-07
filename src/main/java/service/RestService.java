package service;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.apache.log4j.Logger;

import bean.Pet;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utility.Log;

public class RestService {
    protected static Logger Log = Logger.getLogger(RestService.class.getName());

	private static final String BASE_URL = "https://petstore.swagger.io";
	private static final String BASE_PATH = "/v2/pet/";
	private static final String CONTENT_TYPE = "application/json";

	protected RequestSpecification requestSpec;
	protected ResponseSpecification responseSpec;

	public RestService() {
		RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
		requestBuilder.setBaseUri(BASE_URL);
		requestBuilder.setBasePath(BASE_PATH);
		requestBuilder.log(LogDetail.URI);
		requestSpec = requestBuilder.build();

		ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
		responseSpec = responseBuilder.build();
	}

	public Response run(Method action, Pet body) {
		RequestSpecification request = given().spec(requestSpec).contentType(CONTENT_TYPE).body(body);
		return run(action, request, null);
	}

	public Response run(Method action, String getParam) {
		RequestSpecification request = given().spec(requestSpec).contentType(CONTENT_TYPE);
		return run(action, request, getParam);
	}
	
	public Response run(Method action, String getPath, String paramName, List<String> requestParam) {
		RequestSpecification request = given().spec(requestSpec).contentType(CONTENT_TYPE)
				.param(paramName, requestParam);
		return run(action, request, getPath);
	}
	
	private Response run(Method action, RequestSpecification request, String getParam) {
		Response response = null;
		Log.info("URL:" + request.log().uri().toString());
		switch (action) {
		case GET:
			response = request.when().get(getParam);
			break;
		case POST:
			response = request.when().post();
			break;
		case PUT:
			response = request.when().put();
			break;
		default:
			new RuntimeException("Incorrect action:" + action);
			break;
		}
		Log.info("Status code: "+response.getStatusCode());
		response.then().log().status();
		return response;
	}
}

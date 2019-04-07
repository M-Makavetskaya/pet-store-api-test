package apitests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import bean.Pet;
import bean.Status;
import factory.PetFactory;
import io.restassured.http.Method;
import io.restassured.response.Response;
import utility.Log;

public class PetStoreTests extends BaseApiTest {
	protected static final String FIND_BY_STATUS_PATH = "/findByStatus";

	// post https://petstore.swagger.io/v2/pet/
	// add new Pet
	@Test
	public void postPetStoreTest() {
		Pet testPet = PetFactory.pendingPet();
		Response response = restService.run(Method.POST, testPet);
		System.out.println(response.getStatusCode());

		assertEquals(response.getStatusCode(), 200);
	}

	// put https://petstore.swagger.io/v2/pet/
	// update existing Pet
	@Test
	public void putPetStoreTest() {
		Pet testPet = PetFactory.updatedPet();
		Response response = restService.run(Method.PUT, testPet);
		response.then().log().status();
		System.out.println(response.getBody());
		System.out.println(response.getStatusCode());

		assertEquals(response.getStatusCode(), 200);
	}

	// get https://petstore.swagger.io/v2/pet/1
	@Test
	public void getPetStoreTest() {
		String petId = "13";
		Response response = restService.run(Method.GET, petId);
		Pet myPet = response.as(Pet.class);
		assertTrue(myPet.getName().equals("Snoopy"), "Expected name: Snoopy, but found:" + myPet.getName());
		assertTrue(myPet.getCategory().getName().equals("medium"),
				"Expected category: medium, but found:" + myPet.getCategory().getName());
	}

	// GET
	// "https://petstore.swagger.io/v2/pet/findByStatus?status=pending&status=sold"
	@Test
	public void getfindByStatus() {		
		Response response = restService.run(Method.GET, FIND_BY_STATUS_PATH, "status", Arrays.asList(Status.PENDING.toString()));
		List<Pet> petList = Arrays.asList(response.as(Pet[].class));
		Pet expectedPet = PetFactory.pendingPet();
		Log.info(expectedPet.toString());
		System.out.println(petList.get(0).toString());

		assertTrue(petList.contains(expectedPet));
	}
}

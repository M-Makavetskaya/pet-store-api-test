package com.petstore.apitests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.petstore.bean.Pet;
import com.petstore.factory.PetFactory;

import io.restassured.response.Response;

public class CreatePetTest extends BaseApiTest {
	Pet testPet = PetFactory.newPet();

	@BeforeClass
	public void testDataPreparation() {
		petStoreService.deletePetIfExists(Integer.toString(testPet.getId()));
	}

	@Test
	public void addNewPetTest() {
		Response response = petStoreService.addPet(testPet);
		assertEquals(response.getStatusCode(), 200);
	}
}

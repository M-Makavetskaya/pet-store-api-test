package com.petstore.apitests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.petstore.bean.Pet;
import com.petstore.factory.PetFactory;

import io.restassured.response.Response;

public class UpdatePetTest extends BaseApiTest {
	Pet updatedPet = PetFactory.updatedPet();
	Pet newPet = PetFactory.newPet();

	@BeforeClass
	public void testDataPreparation() {
		petStoreService.addPet(newPet);
	}

	@Test
	public void updatePetTest() {
		assertTrue(petStoreService.readPetById("12").as(Pet.class).getCategory().getName().equals("small"),
				"Expected category: small, but found:" + newPet.getCategory().getName());
		Response response = petStoreService.updatePet(updatedPet);
		assertEquals(response.getStatusCode(), 200);
		assertTrue(response.as(Pet.class).getCategory().getName().equals("medium"),
				"Expected category: medium, but found:" + updatedPet.getCategory().getName());
	}

}

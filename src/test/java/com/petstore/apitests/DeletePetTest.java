package com.petstore.apitests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.petstore.bean.Pet;
import com.petstore.bean.Status;
import com.petstore.factory.PetFactory;

import io.restassured.response.Response;

public class DeletePetTest extends BaseApiTest{
	Pet soldPet = PetFactory.soldPet();

	@BeforeClass
	public void testDataPreparation() {
		petStoreService.addPet(soldPet);
	}

	@Test
	public void deletePetTest() {
		assertEquals(petStoreService.readPetById(Integer.toString(soldPet.getId())).getStatusCode(), 200);
		Response response = petStoreService.deletePet("14");
		assertEquals(response.getStatusCode(), 200);
		assertEquals(petStoreService.readPetById("14").getStatusCode(), 404);		
	}
}

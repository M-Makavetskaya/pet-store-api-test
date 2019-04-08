package com.petstore.apitests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.petstore.bean.Pet;
import com.petstore.bean.Status;
import com.petstore.factory.PetFactory;

import io.restassured.response.Response;

public class ReadPetTest extends BaseApiTest {
	private Pet availablePet = PetFactory.newPet();
	private Pet pendingPet = PetFactory.pendingPet();
	private Pet soldPet = PetFactory.soldPet();

	@BeforeClass
	public void testDataPreparation() {
		petStoreService.deletePetIfExists(Integer.toString(availablePet.getId()));
		petStoreService.deletePetIfExists(Integer.toString(pendingPet.getId()));
		petStoreService.deletePetIfExists(Integer.toString(soldPet.getId()));
		petStoreService.addPet(availablePet);
		petStoreService.addPet(pendingPet);
		petStoreService.addPet(soldPet);

	}

	@Test
	public void findPetByIdTest() {
		String petId = "13";
		Response response = petStoreService.readPetById(petId);
		Pet myPet = response.as(Pet.class);
		assertEquals(response.getStatusCode(), 200);
		assertTrue(myPet.getName().equals("Snoopy"), "Expected name: Snoopy, but found:" + myPet.getName());
		assertTrue(myPet.getCategory().getName().equals("medium"),
				"Expected category: medium, but found:" + myPet.getCategory().getName());
	}

	@Test
	public void findPetByStatus() {
		Response response = petStoreService.readPetByStatus("status",
				Arrays.asList(Status.PENDING.toString()));
		List<Pet> petList = Arrays.asList(response.as(Pet[].class));
		log.info("List of found pets: " + petList.toString());

		assertEquals(response.getStatusCode(), 200);
		assertTrue(petList.contains(pendingPet), "Pending pet was not found: " + pendingPet.toString());
		assertFalse(petList.contains(availablePet), "Available pet was found");

	}
}

package com.petstore.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.petstore.bean.Pet;

import io.restassured.http.Method;
import io.restassured.response.Response;

public class PetStoreService {
	protected static final String FIND_BY_STATUS_PATH = "/findByStatus";
	protected static Logger log = LogManager.getLogger(PetStoreService.class);
	protected RestService restService = new RestService();

	/**
	 * Delete Pet by Id if it exists
	 * 
	 * @param petId
	 */
	public void deletePetIfExists(String petId) {
		log.info("Find Pet to delete with Id" + petId);
		if (readPetById(petId).getStatusCode() == 200) {
			deletePet(petId);
		} else
			log.info("Pet with Id " + petId + " was not found");
	}

	/**
	 * Add New Pet - POST /v2/pet/
	 * 
	 * @param pet
	 * @return
	 */
	public Response addPet(Pet pet) {
		log.info("Add new Pet " + pet.toString());
		return restService.run(Method.POST, pet);
	}

	/**
	 * Get Pet By Id - GET /v2/pet/1
	 * 
	 * @param petId
	 * @return
	 */
	public Response readPetById(String petId) {
		log.info("Get Pet with Id" + petId);
		return restService.run(Method.GET, petId);
	}

	/**
	 * Get Pet By Status GET /v2/pet/findByStatus?status=pending&status=sold
	 * 
	 * @param paramName
	 * @param paramValues
	 * @return
	 */
	public Response readPetByStatus(String paramName, List<String> paramValues) {
		log.info("Get list of Pets by param: " + paramName + " with values: " + paramValues.toString());
		return restService.run(Method.GET, FIND_BY_STATUS_PATH, paramName, paramValues);

	}

	/**
	 * Update Pet PUT /v2/pet/
	 * 
	 * @param updatedPet
	 * @return
	 */
	public Response updatePet(Pet updatedPet) {
		log.info("Update Pet to: " + updatedPet.toString());
		return restService.run(Method.PUT, updatedPet);
	}

	public Response deletePet(String petId) {
		log.info("Delete Pet with Id" + petId);
		return restService.run(Method.DELETE, petId);
	}

}

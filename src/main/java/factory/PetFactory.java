package factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bean.Category;
import bean.Pet;
import bean.Status;
import bean.Tag;

public class PetFactory {

	public static Pet newPet() {
		return new Pet(12, new Category(1, "small"), "Snoopy", Arrays.asList(""), null, "available");
	}
	
	public static Pet updatedPet() {
		return new Pet(12, new Category(1, "medium"), "Snoopy", Arrays.asList(""), null, "available");
	}
	
	public static Pet pendingPet() {
		List<Tag> tagList = Arrays.asList(new Tag(1, "cute"));
		return new Pet(13, new Category(1, "medium"), "Snoopy", Arrays.asList(""), tagList, Status.PENDING.toString());
	}
	
	public static Pet soldPet() {
		return new Pet(14, new Category(1, "medium"), "Snoopy", Arrays.asList(""), null, Status.SOLD.toString());
	}
	
}

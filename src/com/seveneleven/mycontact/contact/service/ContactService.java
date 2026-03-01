//author : developer
//version : 6.0
//created contact service to update a contact

package com.seveneleven.mycontact.contact.service;
import com.seveneleven.mycontact.contact.Model.Contact;
import com.seveneleven.mycontact.contact.Model.Organization;
import com.seveneleven.mycontact.contact.Model.Person;
import com.seveneleven.mycontact.contact.Model.Tag;
import com.seveneleven.mycontact.user.model.User;
//import com.sun.tools.javac.util.List;

import java.util.Optional;
import java.util.UUID;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
public class ContactService {
	
	//viewing contact details
	public void viewContactDetails(User user, String contactName) {
		
		if(user.getContacts().isEmpty()) {
			System.out.println("No contacts available.");
			return;
		}
		
		Optional<Contact> contactOptional = 
				user.getContacts()
						.stream()
						.filter(c -> c.getName().equals(contactName))
						.findFirst();
		
		if(contactOptional.isPresent()) {
			System.out.println(contactOptional.get());
			
		}else {
			System.out.println("Contact not found");
			
			
		}
		
	}
	
	
	//allows to add new contact details
	public void editContactByName(User user, String name, String newName, String newNumber, String label) {
		Optional<Contact> contactOptional =
				user.getContacts()
					.stream()
					.filter(c -> c.getName().equalsIgnoreCase(name))
					.findFirst();
		
		if(contactOptional.isEmpty()) {
			System.out.println("Contact not found.");
			return;
		}
		
		Contact original = contactOptional.get();
		
		
		Contact backup;

		if (original instanceof Person) {
		    backup = new Person((Person) original);
		} else {
		    backup = new Organization((Organization) original);
		}
		
		
		try {
		    original.setName(newName);
		    original.updatePhoneByLabel(label, newNumber);
		    System.out.println("Contact updated");

		} catch(Exception e) {

		    int index = user.getContacts().indexOf(original);
		    user.getContacts().set(index, backup);

		    System.out.println("Update failed. Reverted changes.");
		}
		
		
	}
	
	public void deleteContactByName(User user, String name, boolean hardDelete, boolean confirm) {
		if(!confirm) {
			System.out.println("Deletion cancelled by user.");
	        return;
		}
		
		Optional<Contact> contactOptional =
				user.getContacts()
					.stream()
					.filter(c -> c.getName().equalsIgnoreCase(name))
					.findFirst();
		
		if(contactOptional.isEmpty()) {
			System.out.println("Contact not found");
			return;
		}
		
		Contact contact = contactOptional.get();
		
		try {

	        if (hardDelete) {

	            // HARD DELETE → remove from list
	            user.getContacts().remove(contact);

	            System.out.println("Contact permanently deleted.");

	        } else {

	            // SOFT DELETE → mark as deleted
	            contact.markDeleted();

	            System.out.println("Contact marked as deleted (soft delete).");
	        }

	    } catch (Exception e) {
	        System.out.println("Error deleting contact.");
	    }
					
		
	}
	//bulk functions for deleting tagging and exporting
	public void bulkDelete(User user, List<String> names) {
		for(String name : names) {
			
			Optional<Contact> contactOptional = 
					user.getContacts()
					.stream()
					.filter(c -> c.getName().equalsIgnoreCase(name))
					.findFirst();
		
			contactOptional.ifPresent(c -> {
				c.markDeleted();
				System.out.println("Deleted " + c.getName());
			});
					
					
		}
		
		
	}
	
	
public void  bulkTag(User user, List<String> names, String tag) {
    	
    	for(Contact contact : user.getContacts()) {
    		
    		if(names.contains(contact.getName())) {
    			contact.addTag(new Tag(tag));
    			System.out.println("Tagged " + contact.getName() + " with " + tag);
    		}
    		
    	}
    	
    }
    
    public void bulkExport(User user,
            List<String> names,
            String fileName) {

			try (FileWriter writer = new FileWriter(fileName)) {
			
			for (Contact contact : user.getContacts()) {
			
			 if (names.contains(contact.getName()) && !contact.isDeleted()) {
			     writer.write(contact.toString());
			     writer.write("\n");
	}
	}
			
			System.out.println("Contacts exported successfully.");
			
			} catch (IOException e) {
			System.out.println("Error exporting contacts.");
}
	}
    
    public void createTagForContact(User user,
            String contactName,
            String tagName) {

Optional<Contact> contactOptional =
user.getContacts()
.stream()
.filter(c -> c.getName().equalsIgnoreCase(contactName))
.findFirst();

if (contactOptional.isEmpty()) {
System.out.println("Contact not found.");
return;
}

Contact contact = contactOptional.get();

Tag tag = new Tag(tagName);
contact.addTag(tag);

System.out.println("Tag '" + tagName + "' added to " + contactName);
}
    
    
}

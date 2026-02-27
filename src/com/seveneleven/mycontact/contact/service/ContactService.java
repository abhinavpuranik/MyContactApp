//author : developer
//version : 4.0
//created contact service to view a contact

package com.seveneleven.mycontact.contact.service;
import com.seveneleven.mycontact.contact.Model.Contact;
import com.seveneleven.mycontact.contact.Model.Organization;
import com.seveneleven.mycontact.contact.Model.Person;
import com.seveneleven.mycontact.user.model.User;

import java.util.Optional;
import java.util.UUID;
public class ContactService {
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
}

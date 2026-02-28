//author: Developer
//version : 10.0

package com.seveneleven.mycontact.user;

import com.seveneleven.mycontact.user.auth.*;
import com.seveneleven.mycontact.user.model.User;
import com.seveneleven.mycontact.contact.Model.*;
import com.seveneleven.mycontact.contact.filter.ContactFilter;
import com.seveneleven.mycontact.contact.filter.DateAddedFilter;
import com.seveneleven.mycontact.contact.filter.FrequentContactFilter;
import com.seveneleven.mycontact.contact.filter.TagFilter;
import com.seveneleven.mycontact.contact.search.ContactSearch;
import com.seveneleven.mycontact.contact.search.EmailSearch;
import com.seveneleven.mycontact.contact.search.NameSearch;
import com.seveneleven.mycontact.contact.search.PhoneSearch;
import com.seveneleven.mycontact.contact.search.TagSearch;
import com.seveneleven.mycontact.contact.service.ContactService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

    	//UC1

        UserService userService = new UserService();

        User user = userService.register(
                "FREE",
                "abhinav@email.com",
                "Password@123",
                "Abhinav",
                22
        );

        System.out.println("User Registered Successfully!");
        System.out.println("User Type: " + user.getUserType());

        // Store registered users
        List<User> users = new ArrayList<>();
        users.add(user);

        //UC2

        Authentication auth;

        // Change this to "OAUTH" to test OAuth
        //Can be BASIC or OAUTH
        String authType = "BASIC";

        if (authType.equalsIgnoreCase("BASIC")) {
            auth = new BasicAuth(users);
        } else {
            auth = new OAuthAuth(users);
        }

        Optional<User> loggedIn =
                auth.login("abhinav@email.com", "Password@123");

        if (loggedIn.isPresent()) {

            User sessionUser = loggedIn.get();

            System.out.println("Login Successful!");
            System.out.println("Welcome " + sessionUser.getUserName());
            System.out.println("User Type: " + sessionUser.getUserType());

        } else {
            System.out.println("Login Failed!");
        }
        
        
        //UC3
        if (loggedIn.isPresent()) {

            User sessionUser = loggedIn.get();

            System.out.println("Login Successful!");

           
            sessionUser.updateUserName("Abhinav P");
            sessionUser.updateAge(23);
            sessionUser.updatePreference("Dark Mode");

            System.out.println("Updated Username: " + sessionUser.getUserName());
            System.out.println("Updated Age: " + sessionUser.getAge());
            System.out.println("Preference: " + sessionUser.getPreference());

            sessionUser.changePassword("Password@123", "NewPass@456");
            System.out.println("Password changed successfully!");

        //UC4
            Contact contact;

            String contactType = "PERSON"; // Can be PERSON or ORGANIZATION

            if (contactType.equalsIgnoreCase("PERSON")) {
                contact = new Person("John Doe", "1998-05-10");
            } else {
                contact = new Organization("OpenAI", "AI Research");
            }

            // Add phone numbers
            contact.addPhoneNumber(new PhoneNumber("Mobile", "9876543210"));
            contact.addPhoneNumber(new PhoneNumber("Work", "9123456780"));

            // Add emails
            contact.addEmail(new Email("Personal", "john@gmail.com"));
            contact.addEmail(new Email("Work", "john@company.com"));

            // Add contact to logged-in user
            sessionUser.addContact(contact);

            System.out.println("\nContact Added Successfully!");
            System.out.println("Contact ID: " + contact.getId());
            System.out.println("Created At: " + contact.getCreatedAt());

           

            System.out.println("\n===== USER CONTACTS =====");

            for (Contact c : sessionUser.getContacts()) {

                System.out.println("ID: " + c.getId());
                System.out.println("Name: " + c.getName());
                System.out.println("Type: " + c.getContactType());
                System.out.println("Created At: " + c.getCreatedAt());

                System.out.println("Phone Numbers:");
                for (PhoneNumber p : c.getPhoneNumbers()) {
                    System.out.println(" - " + p.getLabel() + ": " + p.getNumber());
                }

                System.out.println("Emails:");
                for (Email e : c.getEmails()) {
                    System.out.println(" - " + e.getLabel() + ": " + e.getAddress());
                }

                System.out.println("---------------------------");
            }
            
            //UC5
            ContactService contactService = new ContactService();
            String contactName = sessionUser.getContacts().get(0).getName();
            
            contactService.viewContactDetails(sessionUser, contactName);
            
            //UC6
            ContactService newContactService = new ContactService();


	         newContactService.editContactByName(
	                 sessionUser,
	                 "John Doe",        // existing name
	                 "John Updated",    // new name
	                 "8888888888",      // new phone number
	                 "Mobile"           // label to update
	         );
	
         
	         System.out.println("\n===== CONTACTS AFTER EDIT :  =====");
	
	         for (Contact c : sessionUser.getContacts()) {
	             System.out.println(c);
	         }
	         
	         //UC7
	         
	         

	        

	         
	         boolean hardDelete = false;   // true = permanent
	         boolean confirm = true;       

	         contactService.deleteContactByName(
	                 sessionUser,
	                 "John Updated",
	                 hardDelete,
	                 confirm
	         );

	         // Show remaining contacts
	         System.out.println("\n===== CONTACTS AFTER DELETE =====");

	         for (Contact c : sessionUser.getContacts()) {

	             if (!c.isDeleted()) {   // skip soft deleted contacts
	                 System.out.println(c);
	             }
	         }
	         
	         //UC8
	         System.out.println("\n===== UC8: BULK OPERATIONS =====");

	        
	         List<String> selectedNames = new ArrayList<>();
	         selectedNames.add("John Updated");
	         selectedNames.add("Alice");

	         
	         contactService.bulkTag(sessionUser, selectedNames, "Friends");

	        
	         contactService.bulkDelete(sessionUser, selectedNames);

	         
	         contactService.bulkExport(sessionUser, selectedNames, "contacts_export.txt");
	         
	         
	         //UC9
	         System.out.println("\n===== UC9: SEARCH CONTACTS =====");

	         ContactSearch searchStrategy;

	         //can search by name/email/phone/tag
	         String searchType = "NAME";
	         String keyword = "John Updated";

	         if (searchType.equalsIgnoreCase("NAME")) {
	             searchStrategy = new NameSearch();
	         } else if (searchType.equalsIgnoreCase("PHONE")) {
	             searchStrategy = new PhoneSearch();
	         } else if (searchType.equalsIgnoreCase("EMAIL")) {
	             searchStrategy = new EmailSearch();
	         } else {
	             searchStrategy = new TagSearch();
	         }

	         List<Contact> results =
	                 searchStrategy.search(sessionUser.getContacts(), keyword);

	         if (results.isEmpty()) {
	             System.out.println("No contacts found.");
	         } else {
	             for (Contact c : results) {
	                 System.out.println(c);
	             }
	         }
	         
	         System.out.println("\n===== UC10: BASIC FILTERING =====");

	         ContactFilter filter;

	         //Can filter based on tag/date/frequent
	         String filterType = "TAG";

	         if (filterType.equalsIgnoreCase("TAG")) {
	             filter = new TagFilter("Friends");
	         } else if (filterType.equalsIgnoreCase("DATE")) {
	             filter = new DateAddedFilter();
	         } else {
	             filter = new FrequentContactFilter();
	         }

	         List<Contact> filtered =
	                 filter.apply(sessionUser.getContacts());

	         for (Contact c : filtered) {
	             System.out.println(c);
	         }
	         
	         
            
            
        }
    }
}
        
    
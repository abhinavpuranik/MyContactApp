//author: Developer
//version : 6.0
//abstract Contact model for contacts

package com.seveneleven.mycontact.contact.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class Contact {

    private UUID id;
    private String name;
    private List<PhoneNumber> phoneNumbers;
    private List<Email> emails;
    private LocalDateTime createdAt;
    private boolean deleted = false;
   
    protected Contact(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.phoneNumbers = new ArrayList<>();
        this.emails = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }
    
    public Contact(Contact other) {
    	this.id = other.id;
    	this.name = other.name;
    	this.createdAt = other.createdAt;
    	
    	this.phoneNumbers = new ArrayList<>();
    	for(PhoneNumber p : other.phoneNumbers) {
    		this.phoneNumbers.add(new PhoneNumber(p.getLabel(), p.getNumber()));
    		
    	}
    	
    	this.emails = new ArrayList<>();
    	for(Email e: other.emails) {
    		this.emails.add(new Email(e.getLabel(), e.getAddress()));
    		
    	}
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public List<PhoneNumber> getPhoneNumbers() { return new ArrayList<>(phoneNumbers); }
    public List<Email> getEmails() { return emails; }

    public void addPhoneNumber(PhoneNumber phone) {
        phoneNumbers.add(phone);
    }

    public void addEmail(Email email) {
        emails.add(email);
    }
    
    //update phone number based on label like home or work
    public void updatePhoneByLabel(String label, String newNumber) {

        Optional<PhoneNumber> phoneOptional =
                phoneNumbers.stream()
                        .filter(p -> p.getLabel().equalsIgnoreCase(label))
                        .findFirst();

        if (phoneOptional.isEmpty()) {
            throw new IllegalArgumentException("Phone label not found");
        }

        phoneOptional.get().setNumber(newNumber);
    }

    public abstract String getContactType();
    
    @Override
    //overrided tostring()
    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append(String.format("ID: %s%n", id));
    	builder.append(String.format("Name: %s%n", name));
    	builder.append(String.format("Type: %s%n", getContactType()));
    	
        builder.append("\nPhone Numbers:\n");
        
        for(PhoneNumber p : phoneNumbers) {
        	builder.append(String.format(" - %s: %s%n", p.getLabel(), p.getNumber()));
        	
        }
        builder.append("\nEmails:\n");
        for (Email e : emails) {
            builder.append(String.format(" - %s: %s%n", e.getLabel(), e.getAddress()));
        }
        
        return builder.toString();
        
        
    	
    }
    
    
    public void setName(String name) {
    	if(name == null || name.isBlank()) {
    		throw new IllegalArgumentException("Name cannot be blank");
    		
    	}
    	this.name = name;
    }
    
    
    public void updatePhoneNumber(int index, String newNumber) {
    	if(index < 0 || index >= phoneNumbers.size()) {
    		throw new IllegalArgumentException("Invalid phone index");
    		
    	}
    	
    	if(newNumber == null || newNumber.isBlank()) {
    		throw new IllegalArgumentException("Invalid phone number");
    		
    	}
    	phoneNumbers.get(index).setNumber(newNumber);
    	
    }
    
    public boolean isDeleted() {
    	return deleted;
    }
    
    public void markDeleted() {
    	this.deleted = true;
    }
    
    
    
    
    
    
    
}
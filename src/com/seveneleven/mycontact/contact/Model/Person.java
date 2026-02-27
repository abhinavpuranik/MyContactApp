//author: Developer
//version : 3.0
package com.seveneleven.mycontact.contact.Model;

import java.util.Optional;

public class Person extends Contact{
	private String birthday;
	public Person(Person other) {
	    super(other);   
	    this.birthday = other.birthday;
	}
	
	public Person(String name, String birthday) {
		super(name);
		this.birthday = birthday;
	}
	
	public String getBirthday() {
		return birthday;
	}
	@Override
	public String getContactType() {
		return "PERSON";
	}
	
	@Override
	public String toString() {
		return super.toString() + String.format("Birthday: %s%n", Optional.ofNullable(birthday).orElse("Not Provided"));
		
	}
	
	

}

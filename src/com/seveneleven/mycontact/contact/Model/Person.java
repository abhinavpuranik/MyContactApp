//author: Developer
//version : 3.0
package com.seveneleven.mycontact.contact.Model;

public class Person extends Contact{
	private String birthday;
	
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

}

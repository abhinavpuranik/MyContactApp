
//author: Developer
//version : 12.0
//Phone number model
package com.seveneleven.mycontact.contact.Model;

public class PhoneNumber {

    private String label;
    private String number;

    public PhoneNumber(String label, String number) {
        this.label = label;
        this.number = number;
    }

    public String getLabel() { return label; }
    public String getNumber() { return number; }
    
    public void setNumber(String newNumber) {
    	if(newNumber == null || newNumber.isBlank()) {
    		throw new IllegalArgumentException("Number cannot be empty");
    	}
    	this.number = newNumber;
    }
    
  
}
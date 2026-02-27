
//author: Developer
//version : 3.0
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
}
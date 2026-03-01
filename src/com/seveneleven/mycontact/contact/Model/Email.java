//author: Developer
//version : 12.0
//Email model
package com.seveneleven.mycontact.contact.Model;

public class Email {

    private String label;
    private String address;

    public Email(String label, String address) {
        this.label = label;
        this.address = address;
    }

    public String getLabel() { return label; }
    public String getAddress() { return address; }
}
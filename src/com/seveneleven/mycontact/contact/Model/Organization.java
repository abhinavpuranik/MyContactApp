//author: Developer
//version : 3.0
package com.seveneleven.mycontact.contact.Model;

public class Organization extends Contact {

    private String industry; 

    public Organization(String name, String industry) {
        super(name);
        this.industry = industry;
    }

    public String getIndustry() {
        return industry;
    }

    @Override
    public String getContactType() {
        return "ORGANIZATION";
    }
}
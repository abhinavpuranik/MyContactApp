//author: Developer
//version : 3.0
package com.seveneleven.mycontact.contact.Model;

import java.util.Optional;

public class Organization extends Contact {

    private String industry; 
    public Organization(Organization other) {
        super(other);
        this.industry = other.industry;
    }

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
    
    @Override
    public String toString() {
        return super.toString() +
                String.format("Industry: %s%n",
                        Optional.ofNullable(industry).orElse("Not Provided"));
    }
}
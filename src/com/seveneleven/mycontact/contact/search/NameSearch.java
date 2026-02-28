
//author: Developer
//version : 9.0
package com.seveneleven.mycontact.contact.search;

import com.seveneleven.mycontact.contact.Model.Contact;

import java.util.ArrayList;
import java.util.List;

public class NameSearch implements ContactSearch {

    @Override
    public List<Contact> search(List<Contact> contacts, String keyword) {

        List<Contact> result = new ArrayList<>();

        for (Contact contact : contacts) {

            if (contact.getName().equalsIgnoreCase(keyword)
                    || contact.getName().contains(keyword)) {

                result.add(contact);
            }
        }

        return result;
    }
}

//author: Developer
//version : 9.0
package com.seveneleven.mycontact.contact.search;

import com.seveneleven.mycontact.contact.Model.*;

import java.util.ArrayList;
import java.util.List;

public class EmailSearch implements ContactSearch {

    @Override
    public List<Contact> search(List<Contact> contacts, String keyword) {

        List<Contact> result = new ArrayList<>();

        for (Contact contact : contacts) {

            for (Email email : contact.getEmails()) {

                if (email.getAddress().equalsIgnoreCase(keyword)
                        || email.getAddress().contains(keyword)) {

                    result.add(contact);
                    break;
                }
            }
        }

        return result;
    }
}
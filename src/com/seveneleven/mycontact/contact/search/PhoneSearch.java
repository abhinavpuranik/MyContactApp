package com.seveneleven.mycontact.contact.search;

//author: Developer
//version : 9.0

import com.seveneleven.mycontact.contact.Model.*;

import java.util.ArrayList;
import java.util.List;

public class PhoneSearch implements ContactSearch {

    @Override
    public List<Contact> search(List<Contact> contacts, String keyword) {

        List<Contact> result = new ArrayList<>();

        for (Contact contact : contacts) {

            for (PhoneNumber phone : contact.getPhoneNumbers()) {

                if (phone.getNumber().contains(keyword)) {
                    result.add(contact);
                    break; // avoid duplicate entries
                }
            }
        }

        return result;
    }
}
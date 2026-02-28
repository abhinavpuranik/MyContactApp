//author: Developer
//version : 9.0
//Searching interface

package com.seveneleven.mycontact.contact.search;

import com.seveneleven.mycontact.contact.Model.Contact;
import java.util.List;

public interface ContactSearch {

    List<Contact> search(List<Contact> contacts, String keyword);
}
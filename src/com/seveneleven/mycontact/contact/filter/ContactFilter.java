
//author: Developer
//version : 12.0
//Contact filter interface
package com.seveneleven.mycontact.contact.filter;

import com.seveneleven.mycontact.contact.Model.Contact;
import java.util.List;

public interface ContactFilter {
	List<Contact> apply(List<Contact> Contacts);
	
}
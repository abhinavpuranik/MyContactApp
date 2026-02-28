package com.seveneleven.mycontact.contact.filter;
import com.seveneleven.mycontact.contact.Model.Contact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class FrequentContactFilter implements ContactFilter {
	@Override
	public List<Contact> apply(List<Contact> contacts) {
		List<Contact> result = new ArrayList<>();
		
		for(Contact contact : contacts) {
			if(!contact.isDeleted()) {
				result.add(contact);
			}
		}
		
		Collections.sort(result, new Comparator<Contact>() {
			@Override
			public int compare(Contact c1, Contact c2) {
				return Integer.compare(c2.getContactCount(), c1.getContactCount());
				
			}
		});
		
		return result;
	}
}

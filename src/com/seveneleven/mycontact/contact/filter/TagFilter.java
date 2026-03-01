
//author: Developer
//version : 10.0
package com.seveneleven.mycontact.contact.filter;
import com.seveneleven.mycontact.contact.Model.Contact;
import com.seveneleven.mycontact.contact.Model.Tag;

import java.util.ArrayList;
import java.util.List;
public class TagFilter implements ContactFilter{
	private String tag;
	
	public TagFilter(String tag) {
		this.tag = tag;
	}
	
	@Override
	public List<Contact> apply(List<Contact> contacts) {
		List<Contact> result = new ArrayList<>();
		for(Contact contact : contacts) {
			if(contact.getTags().contains(new Tag(tag)) && !contact.isDeleted()) {
				result.add(contact);
			}
		}
		
		return result;
	}
}

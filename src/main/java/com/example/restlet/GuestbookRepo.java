package com.example.restlet;

import java.util.*;

import com.example.guestbook.Greeting;
import com.example.guestbook.Guestbook;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class GuestbookRepo implements Repo {

	@Override
	public GreetingList GetGuestbook() {
		// TODO Auto-generated method stub
		Key<Guestbook> theBook = Key.create(Guestbook.class, "default");
		List<Greeting> greetings = ObjectifyService.ofy()
			          .load()
					  .type(Greeting.class) 
			          .ancestor(theBook)   
			          .order("-date")       
			          .list();
	  
	    GreetingList greetingList = new GreetingList(greetings);
		return greetingList;
	}

	@Override
	public Greeting GetGreeting(Long id) {
		// TODO Auto-generated method stub
		Key<Guestbook> theBook = Key.create(Guestbook.class, "default");
		Key<Greeting> secondKey = Key.create(theBook, Greeting.class, id);
		List<Greeting> list = ObjectifyService.ofy()
			          .load()
					  .type(Greeting.class)
					  .ancestor(theBook)  
					  //.filter("date <", new Date())
					  .filterKey(secondKey) 
					  .list();
		if (list == null || list.size() == 0) return null;
		return list.get(0);
	}

}

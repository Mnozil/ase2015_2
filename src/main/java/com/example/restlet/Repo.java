package com.example.restlet;

import com.example.guestbook.*;

public interface Repo {
	public GreetingList GetGuestbook();
	public Greeting GetGreeting(Long id);	
}

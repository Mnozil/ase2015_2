package com.example.restlet;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.example.guestbook.Greeting;
import com.example.guestbook.Guestbook;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class GuestbookResource extends ServerResource {
		
	@Get("xml")
	public String represent() {
	Repo repo = new GuestbookRepo();
	String s = null;
	
	try {
		XmlParser<GreetingList> parser = new XmlParser<GreetingList>(JAXBContext.newInstance(GreetingList.class), repo.GetGuestbook());
		parser.parseToXml();
		s = parser.getXmlData();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return s; 
	}
}



package com.example.restlet;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.example.guestbook.Greeting;

@XmlRootElement(name="guestbook")
@XmlAccessorType(XmlAccessType.FIELD)
public class GreetingList {
	
	@XmlElement(name="greeting")
	List<Greeting> greetings; 
	public GreetingList() {
		greetings = new ArrayList<Greeting>();
	}
	
	public GreetingList(List<Greeting> greetings) {
		this.greetings = greetings;
	}
	
	
	public List<Greeting> getGreetings() {
		return greetings;
	}
}
package com.example.restlet;

/*
import org.restlet.data.*;
import org.restlet.ext.jaxb.*;
import org.restlet.ext.xml.*;
import org.restlet.representation.Representation;
*/
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.example.guestbook.Greeting;

import java.io.StringWriter;

import javax.xml.bind.*;

/**
 * Resource which has only one representation.
 *
 */
public class HardcodedGreetingResource extends ServerResource {
	
    @Get("xml")
    //public Representation represent() {
    public String represent() {
    	String s = null;
		try {
			Greeting greeting = new Greeting("default", 200, "Content 2", "Author ID 2", "foo2@example.com");
			XmlParser<Greeting> parser = new XmlParser<Greeting>(JAXBContext.newInstance(Greeting.class), greeting);
			parser.parseToXml();
			s = parser.getXmlData();
 		} catch(Exception e) {
 			System.out.println("Error");
			e.printStackTrace();
		}
		
		return s;
    }

}

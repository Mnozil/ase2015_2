package com.example.guestbook;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Resource which has only one representation.
 *
 */
public class HelloWorldResource extends ServerResource {
	
    @Get("xml")
    //public Representation represent() {
    public String represent() {
        return "<testXml>hello, world</testXml>";
    	
    }

}

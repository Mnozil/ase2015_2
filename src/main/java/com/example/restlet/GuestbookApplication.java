package com.example.restlet;

import javax.xml.bind.JAXBContext;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.routing.Router;

import com.example.guestbook.Greeting;
import com.example.restlet.HardcodedGreetingResource;

public class GuestbookApplication extends Application {

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public synchronized Restlet createInboundRoot() {
        // Create a router Restlet that routes each call 	
    	Restlet greetingById = new Restlet(getContext()) {
    		public void handle(Request request, Response response) {
    			Repo repo = new GuestbookRepo();
    			String msg = null;
    			String sid = (String) request.getAttributes().get("id");
    			if (sid == null) msg = badRequest("No ID given\n");
    			try {
    				Long id = null;
    				id = Long.parseLong(sid.trim());
    				//get greeting
    				Greeting greeting = repo.GetGreeting(id);
  
    				if (greeting == null) {
    					msg = badRequest("No results for given ID\n");
    				} else {
    					XmlParser<Greeting> parser = new XmlParser<Greeting>(JAXBContext.newInstance(Greeting.class), greeting);
    					parser.parseToXml();
    					msg = parser.getXmlData();
    				}
    			} catch (Exception e) {
    				msg = badRequest(e.toString());
    			}
    			response.setEntity(msg, MediaType.APPLICATION_XML);
    		}
    	};
    			
        Router router = new Router(getContext());

        // define routes
        router.attachDefault(GuestbookResource.class);
		router.attach("/hardcodedGreeting", HardcodedGreetingResource.class);
		router.attach("/{id}", greetingById);
		
        return router;
    }
    
    private String badRequest(String msg) {
    	Status error = new Status(Status.CLIENT_ERROR_BAD_REQUEST, msg);
    	return "<error>"+error.toString()+"</error>";
    }
}
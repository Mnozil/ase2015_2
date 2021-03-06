package com.example.guestbook;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import com.example.restlet.HardcodedGreetingResource;

public class FirstStepsApplication extends Application {

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public synchronized Restlet createInboundRoot() {
        // Create a router Restlet that routes each call to a
        // new instance of HelloWorldResource.
        Router router = new Router(getContext());

        // Defines only one route
        router.attachDefault(HelloWorldResource.class);

        return router;
    }
}
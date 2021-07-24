package com.mainul35.springbootjaxrs;

import org.glassfish.jersey.server.ResourceConfig;

public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        registerEndpoints();
    }

    private void registerEndpoints() {
        packages("com.mainul35.springbootjaxrs.resource");
    }
}

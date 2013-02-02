/*
 * ventplan-server
 * ventplan-storage
 * Copyright (C) 2011-2013 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 02.01.13 19:05
 */

package com.ventplan.storage;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

public class VpxServer {

    private VpxServer() {
    }

    public static void httpServer() throws IOException {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(8080).build();
        System.out.println("Starting grizzly...");
        ResourceConfig rc = new PackagesResourceConfig("com.ventplan.storage");
        HttpServer httpServer = GrizzlyServerFactory.createHttpServer(baseUri, rc);
        System.out.println(String.format("Jersey app started with WADL available at %sapplication.wadl", baseUri));
        /*
        System.out.println("Hit enter to stop it...");
        System.in.read();
        httpServer.stop();
        */
    }

    public static void main(String[] args) throws IOException {
        httpServer();
    }

}

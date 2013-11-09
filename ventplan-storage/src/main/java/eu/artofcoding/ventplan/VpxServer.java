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

package eu.artofcoding.ventplan;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

public class VpxServer {

    private static final Logger logger = Logger.getLogger(VpxServer.class.getName());

    private VpxServer() {
    }

    public static HttpServer httpServer(String hostname, int port) throws IOException {
        URI baseUri = UriBuilder.fromUri(String.format("http://%s/", hostname)).port(port).build();
        logger.fine("Starting grizzly...");
        ResourceConfig rc = new PackagesResourceConfig(VpxServer.class.getPackage().getName());
        HttpServer httpServer = GrizzlyServerFactory.createHttpServer(baseUri, rc);
        logger.fine(String.format("Jersey app started with WADL available at %sapplication.wadl", baseUri));
        /*
        System.out.println("Hit enter to stop it...");
        System.in.read();
        httpServer.stop();
        */
        return httpServer;
    }

}

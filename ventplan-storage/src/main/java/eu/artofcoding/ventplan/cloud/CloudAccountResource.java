/*
 * ventplan-server
 * ventplan-storage
 * Copyright (C) 2011-2013 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 02.01.13 17:52
 */

package eu.artofcoding.ventplan.cloud;

import eu.artofcoding.ventplan.api.vpx.VentplanProject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.logging.Logger;

@Path("/vpx/cloud/account")
public class CloudAccountResource {

    private static final Logger logger = Logger.getLogger(CloudAccountResource.class.getName());

    @GET
    @Path("/show/{name}")
    @Produces(MediaType.APPLICATION_XML)
    public Response show(@PathParam("name") String name) {
        Response response = Response.created(URI.create("/yeeha")).build();
        return response;
    }

    @PUT
    @Path("/create/{name}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response create(@PathParam("name") String name, VentplanProject content) {
        Response response = Response.created(URI.create("/yeeha")).entity(content).build();
        return response;
    }

    @POST
    @Path("/update/{name}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response update(@PathParam("name") String name, VentplanProject content) {
        Response response = Response.created(URI.create("/yeeha")).entity(content).build();
        return response;
    }

}

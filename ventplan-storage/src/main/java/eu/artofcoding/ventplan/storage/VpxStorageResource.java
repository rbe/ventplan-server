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

package eu.artofcoding.ventplan.storage;

import eu.artofcoding.ventplan.api.vpx.VentplanProject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.logging.Logger;

@Path("/vpx/storage")
public class VpxStorageResource {

    private static final Logger logger = Logger.getLogger(VpxStorageResource.class.getName());

    @PUT
    @Path("/{username}/project/{projectname}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response create(@PathParam("username") String username, @PathParam("projectname") String projectname, VentplanProject content) {
        System.out.printf("create: username=%s projectname=%s bauvorhaben=%s%n", username, projectname, content.getProjekt().getBauvorhaben());
        Response vpx = Response.created(URI.create("/" + projectname)).entity(content).build();
        System.out.println(vpx);
        return vpx;
    }

    @POST
    @Path("/{username}/project/{projectname}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response update(@PathParam("username") String username, @PathParam("projectname") String projectname, VentplanProject content) {
        System.out.printf("update: username=%s name=%s bauvorhaben=%s%n", username, projectname, content.getProjekt().getBauvorhaben());
        Response vpx = Response.created(URI.create("/yeeha")).entity(content).build();
        System.out.println(vpx);
        return vpx;
    }

    @DELETE
    @Path("/{username}/project/{projectname}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response delete(@PathParam("username") String username, @PathParam("projectname") String projectname, VentplanProject content) {
        System.out.printf("delete: username=%s name=%s bauvorhaben=%s%n", username, projectname, content.getProjekt().getBauvorhaben());
        Response vpx = Response.created(URI.create("/yeeha")).entity(content).build();
        System.out.println(vpx);
        return vpx;
    }

}

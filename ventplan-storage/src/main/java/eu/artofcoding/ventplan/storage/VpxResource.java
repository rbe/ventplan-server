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

import eu.artofcoding.ventplan.api.VpxConstants;
import eu.artofcoding.ventplan.api.vpx.VentplanProject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/vpx")
public class VpxResource {

    private static final Logger logger = Logger.getLogger(VpxResource.class.getName());

    @GET
    @Path("/template/{name}")
    @Produces(MediaType.APPLICATION_XML)
    public String getTemplate(@PathParam("name") String name) {
        String xml = VpxConstants.EMPTY_VENTPLAN_PROJECT;
        //VpxHelper.
        try {
            URL url = this.getClass().getResource(String.format("/%s.vpx", name));
            if (null != url) {
                List<String> lines = Files.readAllLines(Paths.get(url.toURI()), Charset.forName("UTF-8"));
                StringBuilder builder = new StringBuilder(lines.size() * 20);
                for (String s : lines) {
                    builder.append(s);
                }
                xml = builder.toString();
            }
        } catch (IOException | URISyntaxException e) {
            logger.log(Level.SEVERE, null, e);
        }
        return xml;
    }

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

}

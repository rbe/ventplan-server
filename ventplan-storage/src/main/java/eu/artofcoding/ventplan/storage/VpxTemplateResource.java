/*
 * ventplan-server
 * ventplan-storage
 * Copyright (C) 2011-2013 art of coding UG, http://www.art-of-coding.eu
 * Copyright (C) 2005-2010 Informationssysteme Ralf Bensmann, http://www.bensmann.com
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 12.04.13 12:16
 */

package eu.artofcoding.ventplan.storage;

import eu.artofcoding.ventplan.api.VpxConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/vpx/template")
public class VpxTemplateResource {

    private static final Logger logger = Logger.getLogger(VpxTemplateResource.class.getName());

    @GET
    @Path("/{name}")
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

}

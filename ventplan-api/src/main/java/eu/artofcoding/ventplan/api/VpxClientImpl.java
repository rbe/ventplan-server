package eu.artofcoding.ventplan.api;/*
 * ventplan-api
 * ventplan-api
 * Copyright (C) 2011-2013 art of coding UG, http://www.art-of-coding.eu
 * Copyright (C) 2005-2010 Informationssysteme Ralf Bensmann, http://www.bensmann.com
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 04.02.13 09:11
 */

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.apache.ApacheHttpClient;
import eu.artofcoding.ventplan.api.vpx.VentplanProject;

import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.UUID;
import java.util.prefs.Preferences;

public class VpxClientImpl implements VpxClient {

    private final URI uri;

    private final DefaultClientConfig clientConfig;

    private final Client client;

    public VpxClientImpl(URI uri) {
        this.uri = uri;
        clientConfig = new DefaultClientConfig();
        client = ApacheHttpClient.create(clientConfig);
    }

    @Override
    public URI makeProjectURI(String project) throws VpxException {
        try {
            String username = URLEncoder.encode(getUsername(), VpxConstants.UTF8);
            String _project = URLEncoder.encode(project, VpxConstants.UTF8);
            String format = String.format("%s/storage/%s/project/%s", uri.toString(), username, _project);
            System.out.println(format);
            return URI.create(format);
        } catch (UnsupportedEncodingException e) {
            throw new VpxException(e);
        }
    }

    @Override
    public URI makeTemplateURI(String template) throws VpxException {
        try {
            String _template = URLEncoder.encode(template, VpxConstants.UTF8);
            String format = String.format("%s/template/%s", uri.toString(), _template);
            System.out.println(format);
            return URI.create(format);
        } catch (UnsupportedEncodingException e) {
            throw new VpxException(e);
        }
    }

    @Override
    public URI makeAccountURI() throws VpxException {
        try {
            String username = URLEncoder.encode(getUsername(), VpxConstants.UTF8);
            String format = String.format("%s/account/%s", uri.toString(), username);
            System.out.println(format);
            return URI.create(format);
        } catch (UnsupportedEncodingException e) {
            throw new VpxException(e);
        }
    }

    @Override
    public String getUsername() {
        Preferences preferences = Preferences.userNodeForPackage(this.getClass());
        String username = preferences.get("username", null);
        if (null == username) {
            String osUsername = System.getProperty("user.name");
            username = String.format("%s-%s", osUsername, UUID.randomUUID());
            preferences.put("username", username);
        } else {
            // Got username through Preferences API 
        }
        return username;
    }

    @Override
    public VentplanProject getTemplate(String template) throws VpxException {
        URI uri = makeTemplateURI(template);
        WebResource webResource = client.resource(uri);
        try {
            VentplanProject response = webResource.accept(MediaType.APPLICATION_XML).get(VentplanProject.class);
            System.out.printf("%s.getTemplate: %s response=%s%n", this, uri, response);
            return response;
        } catch (ClientHandlerException e) {
            throw new VpxException(e);
        }
    }

    @Override
    public VentplanProject getProject(String project) throws VpxException {
        URI uri = makeProjectURI(project);
        WebResource webResource = client.resource(uri);
        try {
            VentplanProject response = webResource.accept(MediaType.APPLICATION_XML).get(VentplanProject.class);
            System.out.printf("%s.getProject: %s response=%s%n", this, uri, response);
            return response;
        } catch (ClientHandlerException e) {
            throw new VpxException(e);
        }
    }

    @Override
    public VentplanProject createProject(VentplanProject ventplanProject) throws VpxException {
        String projectname = ventplanProject.getProjekt().getBauvorhaben();
        URI uri = makeProjectURI(projectname);
        WebResource webResource = client.resource(uri);
        try {
            VentplanProject response = webResource.accept(MediaType.APPLICATION_XML).put(VentplanProject.class, ventplanProject);
            System.out.printf("%s.createProject: %s response=%s%n", this, uri, response);
            return response;
        } catch (ClientHandlerException e) {
            throw new VpxException(e);
        }
    }

    @Override
    public VentplanProject updateProject(VentplanProject ventplanProject) throws VpxException {
        String projectname = ventplanProject.getProjekt().getBauvorhaben();
        URI uri = makeProjectURI(projectname);
        WebResource webResource = client.resource(uri);
        try {
            VentplanProject response = webResource.accept(MediaType.APPLICATION_XML).post(VentplanProject.class, ventplanProject);
            System.out.printf("%s.updateProject: %s response=%s%n", this, uri, response);
            return response;
        } catch (ClientHandlerException e) {
            throw new VpxException(e);
        }
    }

    @Override
    public VentplanProject deleteProject(VentplanProject ventplanProject) throws VpxException {
        String projectname = ventplanProject.getProjekt().getBauvorhaben();
        URI uri = makeProjectURI(projectname);
        WebResource webResource = client.resource(uri);
        try {
            VentplanProject response = webResource.delete(VentplanProject.class, ventplanProject);
            System.out.printf("%s.deleteProject: %s response=%s%n", this, uri, response);
            return ventplanProject;
        } catch (ClientHandlerException e) {
            throw new VpxException(e);
        }
    }

}

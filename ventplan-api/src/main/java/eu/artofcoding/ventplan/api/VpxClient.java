package eu.artofcoding.ventplan.api;/*
 * ventplan-api
 * ventplan-api
 * Copyright (C) 2011-2013 art of coding UG, http://www.art-of-coding.eu
 * Copyright (C) 2005-2010 Informationssysteme Ralf Bensmann, http://www.bensmann.com
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 04.02.13 10:51
 */

import eu.artofcoding.ventplan.api.vpx.VentplanProject;

import java.net.URI;

public interface VpxClient {

    URI makeProjectURI(String projectname) throws VpxException;

    URI makeTemplateURI(String template) throws VpxException;

    URI makeAccountURI() throws VpxException;

    String getUsername();

    VentplanProject getTemplate(String template) throws VpxException;

    VentplanProject getProject(String project) throws VpxException;

    VentplanProject createProject(VentplanProject ventplanProject) throws VpxException;

    VentplanProject updateProject(VentplanProject ventplanProject) throws VpxException;

    VentplanProject deleteProject(VentplanProject ventplanProject) throws VpxException;

}

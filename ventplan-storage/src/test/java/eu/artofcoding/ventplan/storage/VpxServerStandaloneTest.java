/*
 * ventplan-server
 * ventplan-api
 * Copyright (C) 2011-2013 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 02.01.13 16:27
 */

package eu.artofcoding.ventplan.storage;

import eu.artofcoding.ventplan.VpxServer;
import eu.artofcoding.ventplan.api.VpxClient;
import eu.artofcoding.ventplan.api.VpxCloud;
import eu.artofcoding.ventplan.api.vpx.ObjectFactory;
import eu.artofcoding.ventplan.api.vpx.VentplanProject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.testng.annotations.Test;

import javax.inject.Inject;

public class VpxServerStandaloneTest extends Arquillian {

    @Inject
    @VpxCloud(port = 8080)
    private VpxClient vpxClient;

    @Deployment
    public static JavaArchive createDeployment() throws Exception {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "vpx-standalone-test.jar")
                .addPackage(VentplanProject.class.getPackage().getName())
                .addPackage(VpxServer.class.getPackage().getName())
                .addPackage(VpxCloud.class.getPackage().getName())
                .addAsManifestResource(EmptyAsset.INSTANCE, "MANIFEST.MF")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(jar.toString(true));
        return jar;
    }

    @Test
    @RunAsClient
    public void startServer() throws Exception {
        VpxServer.httpServer("localhost", 8080);
    }

    @Test
    public void testGetTemplate() throws Exception {
        // Get a template
        VentplanProject ventplanProject = vpxClient.getTemplate("EFH-4ZKB-WC");
        //JaxbHelper.marshal(VentplanProject.class, ventplanProject, System.out);
        System.out.printf("%s.testGetTemplate: after testGetTemplate: ventplanProject=%s%n", this, ventplanProject);
    }

    @Test
    public void testGetProject() throws Exception {
        // Create project
        VentplanProject ventplanProject = new VentplanProject();
        ventplanProject.setProjekt(new ObjectFactory().createProject());
        ventplanProject.getProjekt().setBauvorhaben("Testprojekt Villa Nottuln");
        // Create
        ventplanProject = vpxClient.createProject(ventplanProject);
        System.out.printf("%s.testGetTemplate: after createProject: ventplanProject=%s%n", this, ventplanProject);
    }

    @Test
    public void testCreateProject() throws Exception {
        // Create project
        VentplanProject ventplanProject = new VentplanProject();
        ventplanProject.setProjekt(new ObjectFactory().createProject());
        ventplanProject.getProjekt().setBauvorhaben("Testprojekt Villa Nottuln");
        // Create
        ventplanProject = vpxClient.createProject(ventplanProject);
        System.out.printf("%s.testCreateProject: after testCreateProject: ventplanProject=%s%n", this, ventplanProject);
    }

    @Test
    public void testUpdateProject() throws Exception {
        // Create project
        VentplanProject ventplanProject = new VentplanProject();
        ventplanProject.setProjekt(new ObjectFactory().createProject());
        ventplanProject.getProjekt().setBauvorhaben("Testprojekt Villa Nottuln");
        // Update
        ventplanProject = vpxClient.updateProject(ventplanProject);
        System.out.printf("%s.testUpdateProject: after testUpdateProject: ventplanProject=%s%n", this, ventplanProject);
    }

/*
    @Test
    public void testDeleteProject() throws Exception {
        // Create project
        VentplanProject ventplanProject = new VentplanProject();
        ventplanProject.setProjekt(new ObjectFactory().createProject());
        ventplanProject.getProjekt().setBauvorhaben("Testprojekt Villa Nottuln");
        // Delete
        vpxClient.deleteProject(ventplanProject);
        System.out.printf("%s.testDeleteProject: after testDeleteProject: ventplanProject=%s%n", this, ventplanProject);
    }
*/

}

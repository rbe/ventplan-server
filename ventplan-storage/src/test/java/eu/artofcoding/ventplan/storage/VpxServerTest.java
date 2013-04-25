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

import eu.artofcoding.ventplan.api.VpxClient;
import eu.artofcoding.ventplan.api.VpxCloud;
import eu.artofcoding.ventplan.api.vpx.ObjectFactory;
import eu.artofcoding.ventplan.api.vpx.VentplanProject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.testng.annotations.Test;

import javax.inject.Inject;

public class VpxServerTest extends Arquillian {

    @Inject
    @VpxCloud(port = 8080)
    private VpxClient vpxClient;

    @Deployment
    public static JavaArchive createDeployment() throws Exception {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addPackage(VpxServer.class.getPackage().getName())
                .addPackage(VpxCloud.class.getPackage().getName())
                .addPackage(VentplanProject.class.getPackage().getName())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        //System.out.println(jar.toString(true));
        return jar;
    }

    @Test
    public void startServer() throws Exception {
        VpxServer.httpServer("localhost", 8080);
    }

    @Test
    public void testCreate() throws Exception {
        // Create project
        VentplanProject ventplanProject = new VentplanProject();
        ventplanProject.setProjekt(new ObjectFactory().createProject());
        ventplanProject.getProjekt().setBauvorhaben("Testprojekt Villa Nottuln");
        // Create
        ventplanProject = vpxClient.create(ventplanProject);
        System.out.printf("%s.testCreate: after create: ventplanProject=%s%n", this, ventplanProject);
    }

    @Test
    public void testUpdate() throws Exception {
        // Create project
        VentplanProject ventplanProject = new VentplanProject();
        ventplanProject.setProjekt(new ObjectFactory().createProject());
        ventplanProject.getProjekt().setBauvorhaben("Testprojekt Villa Nottuln");
        // Update
        ventplanProject = vpxClient.update(ventplanProject);
        System.out.printf("%s.testUpdate: after update: ventplanProject=%s%n", this, ventplanProject);
    }

    @Test
    public void testDelete() throws Exception {
        // Create project
        VentplanProject ventplanProject = new VentplanProject();
        ventplanProject.setProjekt(new ObjectFactory().createProject());
        ventplanProject.getProjekt().setBauvorhaben("Testprojekt Villa Nottuln");
        // Delete
        vpxClient.delete(ventplanProject);
        System.out.printf("%s.testDelete: %s deleted%n", this, ventplanProject);
    }

}

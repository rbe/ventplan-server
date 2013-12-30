/*
 * ventplan-api
 * ventplan-api
 * Copyright (C) 2011-2013 art of coding UG, http://www.art-of-coding.eu
 * Copyright (C) 2005-2010 Informationssysteme Ralf Bensmann, http://www.bensmann.com
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 04.02.13 11:11
 */

package eu.artofcoding.ventplan.api;

import eu.artofcoding.ventplan.api.vpx.VentplanProject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class VpxClientImplTest {

    @Inject
    @VpxCloud(port = 8080)
    private VpxClient vpxClient;

    @Deployment
    public static JavaArchive createDeployment() throws Exception {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "vpx-client-test.jar")
                .addPackage(VpxCloud.class.getPackage().getName())
                .addPackage(VentplanProject.class.getPackage().getName())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(jar.toString(true));
        return jar;
    }

    @Test
    public void testGetUsername() {
        String username1 = vpxClient.getUsername();
        System.out.printf("1. call to getUsername()=%s%n", username1);
        // Username must be consistent across calls
        String username2 = vpxClient.getUsername();
        System.out.printf("2. call to getUsername()=%s%n", username2);
        String username3 = vpxClient.getUsername();
        System.out.printf("3. call to getUsername()=%s%n", username3);
        String message = "Username not equal across different calls!";
        assertEquals(message, username1, username2);
        assertEquals(message, username1, username3);
        assertEquals(message, username2, username3);
    }

/*
    @Test
    public void testCreate() throws Exception {
        // Create project
        VentplanProject ventplanProject = new VentplanProject();
        ventplanProject.setProjekt(new ObjectFactory().createProject());
        ventplanProject.getProjekt().setBauvorhaben("Testprojekt Villa Nottuln");
        // Create
        ventplanProject = vpxClient.createProject(ventplanProject);
        System.out.printf("after createProject: ventplanProject=%s%n", ventplanProject);
    }

    @Test
    public void testUpdate() throws Exception {
        // Create project
        VentplanProject ventplanProject = new VentplanProject();
        ventplanProject.setProjekt(new ObjectFactory().createProject());
        ventplanProject.getProjekt().setBauvorhaben("Testprojekt Villa Nottuln");
        // Update
        ventplanProject = vpxClient.updateProject(ventplanProject);
        System.out.printf("after updateProject: ventplanProject=%s%n", ventplanProject);
    }

    @Test
    public void testDelete() throws Exception {
        // Create project
        VentplanProject ventplanProject = new VentplanProject();
        ventplanProject.setProjekt(new ObjectFactory().createProject());
        ventplanProject.getProjekt().setBauvorhaben("Testprojekt Villa Nottuln");
        // Delete
        vpxClient.deleteProject(ventplanProject);
        System.out.printf("%s deleted%n", ventplanProject);
    }
*/

}

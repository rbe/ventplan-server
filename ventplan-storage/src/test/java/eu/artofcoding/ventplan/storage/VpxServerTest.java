/*
 * ventplan-server
 * ventplan-storage
 * Copyright (C) 2011-2013 art of coding UG, http://www.art-of-coding.eu
 * Copyright (C) 2005-2010 Informationssysteme Ralf Bensmann, http://www.bensmann.com
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 01.05.13 10:23
 */

package eu.artofcoding.ventplan.storage;

import eu.artofcoding.beetlejuice.xml.JaxbHelper;
import eu.artofcoding.ventplan.api.VpxClient;
import eu.artofcoding.ventplan.api.VpxCloud;
import eu.artofcoding.ventplan.api.vpx.ObjectFactory;
import eu.artofcoding.ventplan.api.vpx.VentplanProject;
import eu.artofcoding.ventplan.cloud.CloudAccountResource;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;

@Ignore
@RunWith(Arquillian.class)
public class VpxServerTest {

    @Inject
    @VpxCloud(port = 8080)
    private VpxClient vpxClient;

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "vpx-server-test.war")
                .addPackage(VentplanProject.class.getPackage())
                .addPackage(CloudAccountResource.class.getPackage())
                .addPackage(VpxStorageResource.class.getPackage())
                .addPackage(VpxCloud.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "MANIFEST.MF")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/web.xml"));
        System.out.println(war.toString(true));
        return war;
    }

    @Test
    @RunAsClient
    public void testGetTemplate() throws Exception {
        // Get a template
        VentplanProject ventplanProject = vpxClient.getTemplate("EFH-4ZKB-WC");
        JaxbHelper.marshal(VentplanProject.class, ventplanProject, System.out);
        System.out.printf("%s.testGetTemplate: after testGetTemplate: ventplanProject=%s%n", this, ventplanProject);
    }

    @Test
    @RunAsClient
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
    @RunAsClient
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
    @RunAsClient
    public void testUpdateProject() throws Exception {
        // Create project
        VentplanProject ventplanProject = new VentplanProject();
        ventplanProject.setProjekt(new ObjectFactory().createProject());
        ventplanProject.getProjekt().setBauvorhaben("Testprojekt Villa Nottuln");
        // Update
        ventplanProject = vpxClient.updateProject(ventplanProject);
        System.out.printf("%s.testUpdateProject: after testUpdateProject: ventplanProject=%s%n", this, ventplanProject);
    }

}

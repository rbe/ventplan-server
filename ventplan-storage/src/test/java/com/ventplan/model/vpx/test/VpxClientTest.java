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

package com.ventplan.model.vpx.test;

import com.ventplan.model.VpxClient;
import com.ventplan.model.vpx.ObjectFactory;
import com.ventplan.model.vpx.VentplanProject;
import com.ventplan.storage.VpxServer;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VpxClientTest {

    private VentplanProject vp;

    private VpxClient vpxClient;

    @BeforeClass
    public void init() throws Exception {
        VpxServer.httpServer();
    }

    @BeforeMethod
    public void setUp() throws Exception {
        // Create project
        vp = new VentplanProject();
        vp.setProjekt(new ObjectFactory().createProject());
        vp.getProjekt().setBauvorhaben("BAUVORHABEN");
        //
        vpxClient = VpxClient.createClient("192.168.180.103", 8080);
        System.out.printf("Ventplan username=%s%n", vpxClient.username());
    }

    @Test
    public void testUsername() throws Exception {
        String username1 = vpxClient.username();
        String username2 = vpxClient.username();
        Assert.assertEquals(username1, username2);
    }

    @Test
    public void testCreate() throws Exception {
        // Create
        vp = vpxClient.create(vp);
        System.out.println("after create: vp=" + vp);
    }

    @Test
    public void testUpdate() throws Exception {
        // Update
        vp = vpxClient.update(vp);
        System.out.println("after update: vp=" + vp);
    }

}

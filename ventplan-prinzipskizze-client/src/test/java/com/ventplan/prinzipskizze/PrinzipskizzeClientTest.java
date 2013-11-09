/*
 * Ventplan
 * ventplan202client, ventplan-prinzipskizze-client
 * Copyright (C) 2005-2010 Informationssysteme Ralf Bensmann, http://www.bensmann.com/
 * Copyright (C) 2011-2013 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 17.03.13 13:41
 */

package com.ventplan.prinzipskizze;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PrinzipskizzeClientTest {

    private static String aussenluft;
    private static String fortluft;
    private static String zentralgeraet;

    private static List<String> abluftEG;
    private static List<String> abluftDG;
    private static List<String> abluftOG;

    private static List<String> zuluftEG;
    private static List<String> zuluftDG;
    private static List<String> zuluftOG;

    @BeforeClass
    public static void setUp() throws Exception {
        aussenluft = "200LE01";
        fortluft = "200LG01";
        zentralgeraet = "300WAC";

        abluftEG = new ArrayList<String>();
        abluftEG.add("Kinderzimmer 1 / 100ULC");
        abluftEG.add("Wohnzimmer / 100ULC");
        abluftEG.add("Kinderzimmer 1 / 100ULC");
        abluftEG.add("Kinderzimmer 1 / 100ULC");
        abluftEG.add("Wohnzimmer / 100ULC");
        abluftEG.add("Wohnzimmer / 100ULC");

        abluftDG = new ArrayList<String>();
        abluftDG.add("Wohnzimmer / 100ULC");
        abluftDG.add("Wohnzimmer / 100ULC");

        abluftOG = new ArrayList<String>();
        abluftOG.add("Kinderzimmer 1 / 100ULC");
        abluftOG.add("Wohnzimmer / 100ULC");
        abluftOG.add("Wohnzimmer / 100ULC");

        zuluftEG = new ArrayList<String>();
        zuluftEG.add("Büro / 125 URH");
        zuluftEG.add("Büro / 125 URH");

        zuluftDG = new ArrayList<String>();
        zuluftDG.add("Büro / 125 URH");
        zuluftDG.add("Schlafzimmer / 125 URH");

        zuluftOG = new ArrayList<String>();
        zuluftOG.add("Büro / 125 URH");
        zuluftOG.add("Schlafzimmer / 125 URH");
    }

    @Test
    public void testCreateWAC202() throws Exception {
        String userHome = System.getProperty("user.home");
        //String url = "http://service.ventplan.com:80/ventplan237/Prinzipskizze";
        String url = "http://localhost:8080/ventplan237/PrinzipskizzeService";
        byte[] b = PrinzipskizzeClient.create(
                new URL(url),
                aussenluft, fortluft, zentralgeraet,
                abluftEG, abluftDG, abluftOG,
                zuluftEG, zuluftDG, zuluftOG);
        Assert.assertNotNull(b);
        URI uri = URI.create(String.format("file://%s/Prinzipskizze.png", userHome));
        File p = new File(uri);
        FileOutputStream fos = new FileOutputStream(p);
        fos.write(b);
        fos.close();
        Desktop.getDesktop().open(p);
    }

    @Test
    public void testCreateWAC237() throws Exception {
        String userHome = System.getProperty("user.home");
        //String url = "http://service.ventplan.com:80/ventplan237/PrinzipskizzeWAC237";
        String url = "http://localhost:8080/ventplan237/PrinzipskizzeWAC237";
        byte[] b = PrinzipskizzeClient.createWAC237(
                new URL(url),
                "Projekt Abc", "I hob koa Plan", "Vroni Ventplan", "23.07.2012",
                aussenluft, fortluft, zentralgeraet,
                abluftEG, abluftDG, abluftOG,
                zuluftEG, zuluftDG, zuluftOG);
        Assert.assertNotNull(b);
        URI uri = URI.create(String.format("file://%s/PrinzipskizzeWAC237.png", userHome));
        File p = new File(uri);
        FileOutputStream fos = new FileOutputStream(p);
        fos.write(b);
        fos.close();
        Desktop.getDesktop().open(p);
    }

}

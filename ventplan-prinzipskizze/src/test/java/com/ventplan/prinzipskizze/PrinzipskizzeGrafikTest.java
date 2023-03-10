/*
 * Ventplan
 * ventplan202, ventplan202
 * Copyright (C) 2005-2010 Informationssysteme Ralf Bensmann, http://www.bensmann.com/
 * Copyright (C) 2011-2012 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 7/7/12 6:50 PM
 */

package com.ventplan.prinzipskizze;

import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ventplan.prinzipskizze.PrinzipskizzeConstants.*;
import static org.junit.Assert.assertNotNull;

public class PrinzipskizzeGrafikTest {

    public static final String USER_HOME = System.getProperty("user.home");
    public static final String DESKTOP = String.format("%s/Desktop", USER_HOME);

    @Test
    public void generate() throws IOException {
        PrinzipskizzeGrafik prinzipskizzeGrafik = new PrinzipskizzeGrafik();
        // Names
        Map<String, String> names = new HashMap<String, String>();
        names.put(AUSSENLUFT, "200LE04/8");
        names.put(FORTLUFT, "200LG002/4");
        names.put(ZENTRALGERAET, "300WAC");
        // Connector
        Map<String, HashMap<String, List<String>>> connector = new HashMap<String, HashMap<String, List<String>>>();
        prinzipskizzeGrafik.addConnector(connector, AB, "EG", "Kinderzimmer 1 / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "EG", "Wohnzimmer / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "EG", "Kinderzimmer 1 / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "EG", "Kinderzimmer 1 / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "EG", "Wohnzimmer / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "EG", "Wohnzimmer / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "DG", "Wohnzimmer / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "DG", "Wohnzimmer / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "OG", "Kinderzimmer 1 / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "OG", "Wohnzimmer / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "OG", "Wohnzimmer / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, ZU, "DG", "B??ro / 125 URH");
        prinzipskizzeGrafik.addConnector(connector, ZU, "DG", "Schlafzimmer / 125 URH");
        prinzipskizzeGrafik.addConnector(connector, ZU, "EG", "B??ro / 125 URH");
        prinzipskizzeGrafik.addConnector(connector, ZU, "EG", "B??ro / 125 URH");
        prinzipskizzeGrafik.addConnector(connector, ZU, "OG", "B??ro / 125 URH");
        prinzipskizzeGrafik.addConnector(connector, ZU, "OG", "Schlafzimmer / 125 URH");
        //
        File file = prinzipskizzeGrafik.drawText("plain", names, connector);
        if (file.length() > 0) {
            Path target = Paths.get(DESKTOP, "Plain.png");
            Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
            Desktop.getDesktop().open(target.toFile());
        }
        assertNotNull(file);
    }

    @Test
    public void generateWAC237() throws IOException {
        PrinzipskizzeGrafik prinzipskizzeGrafik = new PrinzipskizzeGrafik();
        // Names
        Map<String, String> names = new HashMap<String, String>();
        names.put(PROJEKT, "Mein gro??artiges Bauvorhaben");
        names.put(PLAN, "Prinzipskizze ohne Ma??stab");
        names.put(DOKUMENTERSTELLER, "Vroni Ventplan");
        names.put(ERSTELLDATUM, "23.07.2012");
        names.put(AUSSENLUFT, "200LE04/8");
        names.put(FORTLUFT, "200LG002/4");
        names.put(ZENTRALGERAET, "300WAC");
        // Connector
        Map<String, HashMap<String, List<String>>> connector = new HashMap<String, HashMap<String, List<String>>>();
        prinzipskizzeGrafik.addConnector(connector, AB, "EG", "Kinderzimmer 1 / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "EG", "Wohnzimmer / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "EG", "Kinderzimmer 1 / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "EG", "Kinderzimmer 1 / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "EG", "Wohnzimmer / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "EG", "Wohnzimmer / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "DG", "Wohnzimmer / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "DG", "Wohnzimmer / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "OG", "Kinderzimmer 1 / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "OG", "Wohnzimmer / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "OG", "Wohnzimmer / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, ZU, "DG", "B??ro / 125 URH");
        prinzipskizzeGrafik.addConnector(connector, ZU, "DG", "Schlafzimmer / 125 URH");
        prinzipskizzeGrafik.addConnector(connector, ZU, "EG", "B??ro / 125 URH");
        prinzipskizzeGrafik.addConnector(connector, ZU, "EG", "B??ro / 125 URH");
        prinzipskizzeGrafik.addConnector(connector, ZU, "OG", "B??ro / 125 URH");
        prinzipskizzeGrafik.addConnector(connector, ZU, "OG", "Schlafzimmer / 125 URH");
        //
        File file = prinzipskizzeGrafik.drawText("WAC237", names, connector);
        if (file.length() > 0) {
            Path target = Paths.get(DESKTOP, "WAC-237.png");
            Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
            Desktop.getDesktop().open(target.toFile());
        }
        assertNotNull(file);
    }

}

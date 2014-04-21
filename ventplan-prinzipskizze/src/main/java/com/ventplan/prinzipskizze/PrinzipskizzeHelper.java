/*
 * ventplan-server
 * ventplan-prinzipskizze
 * Copyright (C) 2011-2013 art of coding UG, http://www.art-of-coding.eu
 * Copyright (C) 2005-2010 Informationssysteme Ralf Bensmann, http://www.bensmann.com
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 18.03.13 08:40
 */

package com.ventplan.prinzipskizze;

import com.ventplan.prinzipskizze.wac202.WAC202Prinzipskizze;
import com.ventplan.prinzipskizze.wac237.WAC237Prinzipskizze;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.ventplan.prinzipskizze.PrinzipskizzeConstants.*;

public class PrinzipskizzeHelper {

    private static final Logger logger = Logger.getLogger(PrinzipskizzeHelper.class.getName());

    private void addConnector(PrinzipskizzeGrafik prinzipskizzeGrafik, Map<String, HashMap<String, List<String>>> connector, String luftart, String geschoss, ArrayList<String> list) {
        if (null != list && list.size() > 0) {
            for (String s : list) {
                prinzipskizzeGrafik.addConnector(connector, luftart, geschoss, s);
            }
        }
    }

    private Map<String, String> makeNames(String projekt, String plan, String dokumentersteller, String datum, String aussenluft, String fortluft, String zentralgerat) {
        Map<String, String> names = new HashMap<>();
        names.put(PROJEKT, projekt);
        names.put(PLAN, plan);
        names.put(DOKUMENTERSTELLER, dokumentersteller);
        names.put(ERSTELLDATUM, datum);
        names.put(AUSSENLUFT, aussenluft);
        names.put(FORTLUFT, fortluft);
        names.put(ZENTRALGERAET, zentralgerat);
        return names;
    }

    private Map<String, HashMap<String, List<String>>> makeConnector(PrinzipskizzeGrafik prinzipskizzeGrafik, ArrayList<String> abluft1, ArrayList<String> abluft2, ArrayList<String> abluft3, ArrayList<String> zuluft1, ArrayList<String> zuluft2, ArrayList<String> zuluft3) {
        Map<String, HashMap<String, List<String>>> connector = new HashMap<>();
        // Abluft
        if (null != abluft1 && abluft1.size() > 0) {
            addConnector(prinzipskizzeGrafik, connector, AB, "LVKAB1", abluft1);
        }
        if (null != abluft2 && abluft2.size() > 0) {
            addConnector(prinzipskizzeGrafik, connector, AB, "LVKAB2", abluft2);
        }
        if (null != abluft3 && abluft3.size() > 0) {
            addConnector(prinzipskizzeGrafik, connector, AB, "LVKAB3", abluft3);
        }
        // Zuluft
        if (null != zuluft1 && zuluft1.size() > 0) {
            addConnector(prinzipskizzeGrafik, connector, ZU, "LVKZU1", zuluft1);
        }
        if (null != zuluft2 && zuluft2.size() > 0) {
            addConnector(prinzipskizzeGrafik, connector, ZU, "LVKZU2", zuluft2);
        }
        if (null != zuluft3 && zuluft3.size() > 0) {
            addConnector(prinzipskizzeGrafik, connector, ZU, "LVKZU3", zuluft3);
        }
        return connector;
    }

    public byte[] makePrinzipskizze(PrinzipskizzeGrafik prinzipskizze, String projekt, String plan, String dokumentersteller, String datum, String aussenluft, String fortluft, String zentralgerat, ArrayList<String> abluft1, ArrayList<String> abluft2, ArrayList<String> abluft3, ArrayList<String> zuluft1, ArrayList<String> zuluft2, ArrayList<String> zuluft3) {
        byte[] b = null;
        try {
            Map<String, String> names = makeNames(projekt, plan, dokumentersteller, datum, aussenluft, fortluft, zentralgerat);
            Map<String, HashMap<String, List<String>>> connector = makeConnector(prinzipskizze, abluft1, abluft2, abluft3, zuluft1, zuluft2, zuluft3);
            File file = prinzipskizze.drawText(names, connector);
            if (null != file) {
                b = Files.readAllBytes(file.toPath());
            } else {
                if (logger.isLoggable(Level.SEVERE)) {
                    logger.severe("No image was generated");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    public byte[] makeWAC202Prinzipskizze(String projekt, String plan, String dokumentersteller, String datum, String aussenluft, String fortluft, String zentralgerat, ArrayList<String> abluft1, ArrayList<String> abluft2, ArrayList<String> abluft3, ArrayList<String> zuluft1, ArrayList<String> zuluft2, ArrayList<String> zuluft3) {
        WAC202Prinzipskizze prinzipskizze = new WAC202Prinzipskizze();
        byte[] bytes = makePrinzipskizze(prinzipskizze, projekt, plan, dokumentersteller, datum, aussenluft, fortluft, zentralgerat, abluft1, abluft2, abluft3, zuluft1, zuluft2, zuluft3);
        return bytes;
    }

    public byte[] makeWAC237Prinzipskizze(String projekt, String plan, String dokumentersteller, String datum, String aussenluft, String fortluft, String zentralgerat, ArrayList<String> abluft1, ArrayList<String> abluft2, ArrayList<String> abluft3, ArrayList<String> zuluft1, ArrayList<String> zuluft2, ArrayList<String> zuluft3) {
        WAC237Prinzipskizze prinzipskizze = new WAC237Prinzipskizze();
        byte[] bytes = makePrinzipskizze(prinzipskizze, projekt, plan, dokumentersteller, datum, aussenluft, fortluft, zentralgerat, abluft1, abluft2, abluft3, zuluft1, zuluft2, zuluft3);
        return bytes;
    }

}

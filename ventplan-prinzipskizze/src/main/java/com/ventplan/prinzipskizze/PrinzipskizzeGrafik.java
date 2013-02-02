/*
 * Ventplan
 * ventplan202, ventplan202
 * Copyright (C) 2005-2010 Informationssysteme Ralf Bensmann, http://www.bensmann.com/
 * Copyright (C) 2011-2012 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 7/18/12 4:21 PM
 */
package com.ventplan.prinzipskizze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ventplan.prinzipskizze.PrinzipskizzeConstants.*;
import static com.ventplan.prinzipskizze.PrinzipskizzeCoord.*;

/**
 * @author rbe
 */
public class PrinzipskizzeGrafik {

    /**
     * Map for positions of AB LVK descriptions: this is the
     * Y offset of the lowest connection of the LVK.
     */
    private static final Map<String, Integer> LVK_AB_OFFSET_Y = new HashMap<String, Integer>();

    /**
     * Map for positions of ZU LVK descriptions: this is the
     * Y offset of the highest connection of the LVK.
     */
    private static final Map<String, Integer> LVK_ZU_OFFSET_Y = new HashMap<String, Integer>();

    static {
        // Abluft
        LVK_AB_OFFSET_Y.put(AB_LVK_1, LVK_AB_LAST);
        //noinspection PointlessArithmeticExpression
        LVK_AB_OFFSET_Y.put(AB_LVK_2, LVK_AB_LAST - 1 * LVK_HEIGHT);
        LVK_AB_OFFSET_Y.put(AB_LVK_3, LVK_AB_LAST - 2 * LVK_HEIGHT);
        // Zuluft
        LVK_ZU_OFFSET_Y.put(ZU_LVK_1, LVK_ZU_FIRST);
        //noinspection PointlessArithmeticExpression
        LVK_ZU_OFFSET_Y.put(ZU_LVK_2, LVK_ZU_FIRST + 1 * LVK_HEIGHT);
        LVK_ZU_OFFSET_Y.put(ZU_LVK_3, LVK_ZU_FIRST + 2 * LVK_HEIGHT);
    }

    public PrinzipskizzeGrafik() {
    }

    /**
     * @param luftart      Luftart.
     * @param verteilebene Verteilebene.
     * @param raumname     Raum.
     */
    public void addConnector(Map<String, HashMap<String, List<String>>> connector, String luftart, String verteilebene, String raumname) {
        HashMap<String, List<String>> verteilebeneMap = connector.get(luftart);
        if (null == verteilebeneMap) {
            verteilebeneMap = new HashMap<String, List<String>>();
            connector.put(luftart.toUpperCase(), verteilebeneMap);
        }
        List<String> raumList = verteilebeneMap.get(verteilebene);
        if (null == raumList) {
            raumList = new ArrayList<String>();
            verteilebeneMap.put(verteilebene.toUpperCase(), raumList);
        }
        raumList.add(raumname);
    }

    /**
     * Add descriptions for 'Abluft'.
     * @param img The image.
     * @param ab  Two-dimensional String[] 1=LVK, 2=Verbinder am LVK
     */
    private void drawAbluft(BufferedImage img, Map<String, List<String>> ab) {
        // Create graphics
        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.black);
        g2.setFont(AB_ZU_FONT);
        int idx = 1;
        for (String verteilebene : ab.keySet()) {
            int offset = LVK_AB_OFFSET_Y.get(String.format("AB_LVK_%d", idx));
            List<String> strings = ab.get(verteilebene);
            String[] cn = strings.toArray(new String[strings.size()]);
            for (int abConnector = 0; abConnector < cn.length; abConnector++) {
                // Draw text
                g2.drawString(cn[abConnector], AB_ZU_X_OFFSET, offset - abConnector * LVK_AB_SPACER);
            }
            idx++;
        }
        /*
        for (int abLvk = 0; abLvk < ab.length; abLvk++) {
        int offset = LVK_AB_OFFSET_Y.get("AB_LVK_" + (abLvk + 1));
        for (int abConnector = 0; abConnector < ab[abLvk].length; abConnector++) {
        // Draw text
        g2.drawString(ab[abLvk][abConnector], AB_ZU_X_OFFSET, offset - abConnector * LVK_AB_SPACER);
        }
        }
         */
    }

    /**
     * Add descriptions for 'Zuluft'.
     * @param img The image.
     * @param zu  Two-dimensional String[] 1=LVK, 2=Verbinder am LVK
     */
    private void drawZuluft(BufferedImage img, Map<String, List<String>> zu) {
        // Create graphics
        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.black);
        g2.setFont(AB_ZU_FONT);
        int idx = 1;
        for (String verteilebene : zu.keySet()) {
            int offset = LVK_ZU_OFFSET_Y.get(String.format("ZU_LVK_%d", idx));
            List<String> strings = zu.get(verteilebene);
            String[] cn = strings.toArray(new String[strings.size()]);
            for (int zuConnector = 0; zuConnector < cn.length; zuConnector++) {
                // Draw text
                g2.drawString(cn[zuConnector], AB_ZU_X_OFFSET, offset + zuConnector * LVK_ZU_SPACER);
            }
            idx++;
        }
        /*
        for (int zuLvk = 0; zuLvk < zu.length; zuLvk++) {
        int offset = LVK_ZU_OFFSET_Y.get("ZU_LVK_" + (zuLvk + 1));
        for (int zuConnector = 0; zuConnector < zu[zuLvk].length; zuConnector++) {
        // Draw text
        g2.drawString(zu[zuLvk][zuConnector], AB_ZU_X_OFFSET, offset + zuConnector * LVK_ZU_SPACER);
        }
        }
         */
    }

    /**
     * @param img  The image.
     * @param name Product name.
     */
    private void drawAussenluft(BufferedImage img, String name) {
        // Create graphics
        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.black);
        g2.setFont(GERAET_FONT);
        // Draw text
        g2.drawString(name, AUSSENLUFT_X, AUSSENLUFT_Y);
    }

    /**
     * @param img  The image.
     * @param name Product name.
     */
    private void drawFortluft(BufferedImage img, String name) {
        // Create graphics
        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.black);
        g2.setFont(GERAET_FONT);
        // Draw text
        g2.drawString(name, FORTLUFT_X, FORTLUFT_Y);
    }

    /**
     * @param img  The image.
     * @param name Product name.
     */
    private void drawZentralgeraet(BufferedImage img, String name) {
        // Create graphics
        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.black);
        g2.setFont(GERAET_FONT);
        // Draw text
        g2.drawString(String.format(ZENTRALLUEFTUNGSGERAET_TYP_S, name), ZENTRALGERAT_X, ZENTRALGERAT_Y);
    }

    private void drawSchriftfeld(BufferedImage img, String name, int y) {
        // Create graphics
        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.black);
        g2.setFont(GERAET_FONT);
        // Draw text
        g2.drawString(name, SCHRIFTFELD_X, y);
    }

    /**
     * @param basis Which directory for images to use?
     * @return File reference to constructed image.
     * @throws java.io.IOException
     */
    public File drawText(String basis, Map<String, String> names, Map<String, HashMap<String, List<String>>> connector) throws IOException {
        File output = null;
        Map<String, List<String>> abluft = connector.get(AB);
        Map<String, List<String>> zuluft = connector.get(ZU);
        if (null != abluft && abluft.size() > 0 && null != zuluft && zuluft.size() > 0) {
            // Which graphic to use as base?
            String filename = String.format("AB%d_ZU%d", abluft.size(), zuluft.size());
            // Load graphic
            String format = String.format("images/%s/%s.png", basis, filename);
            System.out.println(getClass().getResource(".").toString());
            InputStream resourceAsStream = getClass().getResourceAsStream(format);
            BufferedImage img = ImageIO.read(resourceAsStream);
            // Projekt
            if (names.containsKey(PROJEKT)) {
                drawSchriftfeld(img, names.get(PROJEKT), SCHRIFTFELD_PROJEKT_Y);
            }
            // Plan
            if (names.containsKey(PLAN)) {
                drawSchriftfeld(img, names.get(PLAN), SCHRIFTFELD_PLAN_Y);
            }
            // Dokumentersteller
            if (names.containsKey(DOKUMENTERSTELLER)) {
                drawSchriftfeld(img, names.get(DOKUMENTERSTELLER), SCHRIFTFELD_DOKUMENTERSTELLER_Y);
            }
            // Erstelldatum
            if (names.containsKey(ERSTELLDATUM)) {
                drawSchriftfeld(img, names.get(ERSTELLDATUM), SCHRIFTFELD_ERSTELLDATUM_Y);
            }
            // Aussenluft
            if (names.containsKey(AUSSENLUFT)) {
                drawAussenluft(img, names.get(AUSSENLUFT));
            }
            // Fortluft
            if (names.containsKey(FORTLUFT)) {
                drawFortluft(img, names.get(FORTLUFT));
            }
            // Zentralgeraet
            if (names.containsKey(ZENTRALGERAET)) {
                drawZentralgeraet(img, names.get(ZENTRALGERAET));
            }
            // Abluft
            drawAbluft(img, abluft);
            // Zuluft
            drawZuluft(img, zuluft);
            // Save file
            output = File.createTempFile(VENTPLAN_, DOT_PNG);
            // Delete file when application/JVM is shut down
            output.deleteOnExit();
            ImageIO.write(img, PNG, output);
        }
        // Return File refernce/full path of generated file
        return output;
    }

}

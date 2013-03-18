/*
 * ventplan-server
 * ventplan-prinzipskizze
 * Copyright (C) 2011-2013 art of coding UG, http://www.art-of-coding.eu
 * Copyright (C) 2005-2010 Informationssysteme Ralf Bensmann, http://www.bensmann.com
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 10.12.12 19:42
 */

package com.ventplan.prinzipskizze;

import java.awt.*;

public class PrinzipskizzeConstants {

    public static final String PROJEKT = "projekt".intern();
    public static final String PLAN = "plan".intern();
    public static final String DOKUMENTERSTELLER = "dokumentersteller".intern();
    public static final String ERSTELLDATUM = "erstelldatum".intern();
    public static final String AUSSENLUFT = "aussenluft".intern();
    public static final String FORTLUFT = "fortluft".intern();
    public static final String ZENTRALGERAET = "zentralgeraet".intern();
    public static final String ARIAL = "Arial".intern();

    public static final String AB_LVK_1 = "AB_LVK_1".intern();
    public static final String AB_LVK_2 = "AB_LVK_2".intern();
    public static final String AB_LVK_3 = "AB_LVK_3".intern();
    public static final String ZU_LVK_1 = "ZU_LVK_1".intern();
    public static final String ZU_LVK_2 = "ZU_LVK_2".intern();
    public static final String ZU_LVK_3 = "ZU_LVK_3".intern();

    public static final String AB = "AB".intern();
    public static final String ZU = "ZU".intern();

    public static final String VENTPLAN_ = "ventplan_".intern();
    public static final String DOT_PNG = ".png".intern();
    public static final String PNG = "png".intern();

    public static final String ZENTRALLUEFTUNGSGERAET_TYP_S = "Zentrallüftungsgerät Typ %s".intern();

    public static final String FONT_FAMILY = "Arial";
    public static final int FONT_SIZE = 12;

    /**
     * Font for Aussenluft/Fortluft/Zentralgeraet
     */
    public static final Font GERAET_FONT = new Font(ARIAL, Font.BOLD, 14);

}

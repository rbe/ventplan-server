/*
 * ventplan-server
 * ventplan-prinzipskizze
 * Copyright (C) 2011-2013 art of coding UG, http://www.art-of-coding.eu
 * Copyright (C) 2005-2010 Informationssysteme Ralf Bensmann, http://www.bensmann.com
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 18.03.13 08:42
 */

package com.ventplan.prinzipskizze;

import java.awt.*;

import static com.ventplan.prinzipskizze.PrinzipskizzeConstants.FONT_FAMILY;
import static com.ventplan.prinzipskizze.PrinzipskizzeConstants.FONT_SIZE;

public class PrinzipskizzeCoord {

    /**
     * Spacer in pixels between AB LVK descriptions.
     */
    public static final int LVK_AB_SPACER = 19;

    /**
     * Spacer in pixels between ZU LVK descriptions.
     */
    public static final int LVK_ZU_SPACER = 19;

    /**
     * Font for Abluft/Zuluft.
     */
    public static final Font AB_ZU_FONT = new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE);

    /**
     * x Offset for Abluft/Zuluft.
     */
    public static final int AB_ZU_X_OFFSET = 1165;

    /**
     * X coordinate for label 'Aussenluft'.
     */
    public static final int AUSSENLUFT_X = 10;

    /**
     * Y coordinate for label 'Aussenluft'.
     */
    public static final int AUSSENLUFT_Y = 431 + FONT_SIZE;

    /**
     * X coordinate for label 'Fortluft'.
     */
    public static final int FORTLUFT_X = 10;

    /**
     * Y coordinate for label 'Fortluft'.
     */
    public static final int FORTLUFT_Y = 593 + FONT_SIZE;

    /**
     * X coordinate for label 'Zentralgerat'.
     */
    public static final int ZENTRALGERAT_X = 395;

    /**
     * Y coordinate for label 'Zentralgerat'.
     */
    public static final int ZENTRALGERAT_Y = 625 + FONT_SIZE;

    public static final int LVK_HEIGHT = 156;
    public static final int LVK_AB_LAST = 447;
    public static final int LVK_ZU_FIRST = 504;

    public static final int SCHRIFTFELD_X = 139;
    public static final int SCHRIFTFELD_PROJEKT_Y = 68 + FONT_SIZE;
    public static final int SCHRIFTFELD_PLAN_Y = 96 + FONT_SIZE;
    public static final int SCHRIFTFELD_DOKUMENTERSTELLER_Y = 122 + FONT_SIZE;
    public static final int SCHRIFTFELD_ERSTELLDATUM_Y = 149 + FONT_SIZE;

}

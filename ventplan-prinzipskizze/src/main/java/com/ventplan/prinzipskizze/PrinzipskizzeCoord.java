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
    public static final int LVK_AB_LAST = 443;
    public static final int LVK_ZU_FIRST = 500;

    public static final int SCHRIFTFELD_X = 219;
    public static final int SCHRIFTFELD_PROJEKT_Y = 67 + FONT_SIZE;
    public static final int SCHRIFTFELD_PLAN_Y = 95 + FONT_SIZE;
    public static final int SCHRIFTFELD_DOKUMENTERSTELLER_Y = 121 + FONT_SIZE;
    public static final int SCHRIFTFELD_ERSTELLDATUM_Y = 148 + FONT_SIZE;

}

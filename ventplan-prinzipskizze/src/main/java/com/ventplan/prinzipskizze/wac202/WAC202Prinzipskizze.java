/*
 * ventplan-server
 * ventplan-prinzipskizze
 * Copyright (C) 2011-2013 art of coding UG, http://www.art-of-coding.eu
 * Copyright (C) 2005-2010 Informationssysteme Ralf Bensmann, http://www.bensmann.com
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 18.03.13 08:36
 */

package com.ventplan.prinzipskizze.wac202;

import com.ventplan.prinzipskizze.PrinzipskizzeGrafik;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WAC202Prinzipskizze extends PrinzipskizzeGrafik {

    public File drawText(Map<String, String> names, Map<String, HashMap<String, List<String>>> connector) throws IOException {
        return super.drawText(names, connector);
    }

}

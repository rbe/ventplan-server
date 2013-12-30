/*
 * ventplan-server
 * ventplan-prinzipskizze
 * Copyright (C) 2011-2013 art of coding UG, http://www.art-of-coding.eu
 * Copyright (C) 2005-2010 Informationssysteme Ralf Bensmann, http://www.bensmann.com
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 17.03.13 17:30
 */

package com.ventplan.prinzipskizze.wac237;

import com.ventplan.prinzipskizze.PrinzipskizzeHelper;
import org.jvnet.jax_ws_commons.thread_scope.ThreadScope;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.logging.Logger;

@WebService(targetNamespace = "http://service.ventplan.com/prinzipskizze/wac237", serviceName = "PrinzipskizzeWAC237")
@ThreadScope
public class WAC237Service {

    private static Logger logger = Logger.getLogger(WAC237Service.class.getName());

    @WebMethod
    @WebResult
    public byte[] prinzipskizzeWAC237(@WebParam(name = "projekt") String projekt,
                                      @WebParam(name = "plan") String plan,
                                      @WebParam(name = "dokumentersteller") String dokumentersteller,
                                      @WebParam(name = "erstelldatum") String erstelldatum,
                                      @WebParam(name = "aussenluft") String aussenluft,
                                      @WebParam(name = "fortluft") String fortluft,
                                      @WebParam(name = "zentralgerat") String zentralgerat,
                                      @WebParam(name = "abluft1") ArrayList<String> abluft1,
                                      @WebParam(name = "abluft2") ArrayList<String> abluft2,
                                      @WebParam(name = "abluft3") ArrayList<String> abluft3,
                                      @WebParam(name = "zuluft1") ArrayList<String> zuluft1,
                                      @WebParam(name = "zuluft2") ArrayList<String> zuluft2,
                                      @WebParam(name = "zuluft3") ArrayList<String> zuluft3) {
        byte[] prinzipskizze = new PrinzipskizzeHelper().makeWAC237Prinzipskizze(
                projekt, plan, dokumentersteller, erstelldatum,
                aussenluft, fortluft, zentralgerat,
                abluft1, abluft2, abluft3,
                zuluft1, zuluft2, zuluft3);
        return prinzipskizze;
    }

}

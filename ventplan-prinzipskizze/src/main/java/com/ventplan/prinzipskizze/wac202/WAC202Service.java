/*
 * ventplan-server
 * ventplan-prinzipskizze
 * Copyright (C) 2011-2013 art of coding UG, http://www.art-of-coding.eu
 * Copyright (C) 2005-2010 Informationssysteme Ralf Bensmann, http://www.bensmann.com
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 18.03.13 11:14
 */

package com.ventplan.prinzipskizze.wac202;

import com.ventplan.prinzipskizze.PrinzipskizzeHelper;
import org.jvnet.jax_ws_commons.thread_scope.ThreadScope;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.logging.Logger;

@WebService(targetNamespace = "http://service.ventplan.com/prinzipskizze", serviceName = "PrinzipskizzeService", portName = "PrinzipskizzePort")
@ThreadScope
public class WAC202Service {

    private static Logger logger = Logger.getLogger(WAC202Service.class.getName());

    @WebMethod
    @WebResult
    public byte[] prinzipskizze(@WebParam(name = "aussenluft") String aussenluft,
                                @WebParam(name = "fortluft") String fortluft,
                                @WebParam(name = "zentralgerat") String zentralgerat,
                                @WebParam(name = "abluft1") ArrayList<String> abluft1,
                                @WebParam(name = "abluft2") ArrayList<String> abluft2,
                                @WebParam(name = "abluft3") ArrayList<String> abluft3,
                                @WebParam(name = "zuluft1") ArrayList<String> zuluft1,
                                @WebParam(name = "zuluft2") ArrayList<String> zuluft2,
                                @WebParam(name = "zuluft3") ArrayList<String> zuluft3) {
        byte[] prinzipskizze = new PrinzipskizzeHelper().makeWAC202Prinzipskizze(
                null, null, null, null,
                aussenluft, fortluft, zentralgerat,
                abluft1, abluft2, abluft3,
                zuluft1, zuluft2, zuluft3);
        return prinzipskizze;
    }

}

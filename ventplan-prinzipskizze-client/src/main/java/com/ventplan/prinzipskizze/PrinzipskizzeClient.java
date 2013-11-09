/*
 * Ventplan
 * ventplan202client, ventplan-prinzipskizze-client
 * Copyright (C) 2005-2010 Informationssysteme Ralf Bensmann, http://www.bensmann.com/
 * Copyright (C) 2011-2013 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 15.01.13 12:17
 */

package com.ventplan.prinzipskizze;

import com.ventplan.prinzipskizze.wsclient.wac202.PrinzipskizzeService;
import com.ventplan.prinzipskizze.wsclient.wac202.WAC202Service;
import com.ventplan.prinzipskizze.wsclient.wac237.PrinzipskizzeWAC237_Service;
import com.ventplan.prinzipskizze.wsclient.wac237.WAC237Service;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Save it using NIO.2:
 * <pre>
 *     Path p = Paths.get(URI.create("file:///Users/rbe/bla.png"));
 *     Files.write(p, b, StandardOpenOption.CREATE_NEW);
 * </pre>
 */
public class PrinzipskizzeClient {

    public static final String WSDL_NAMESPACE = "http://service.ventplan.com/prinzipskizze";

    public static byte[] create(URL serviceUrl, String aussenluft, String fortluft, String zentralgeraet, List<String> abluft1, List<String> abluft2, List<String> abluft3, List<String> zuluft1, List<String> zuluft2, List<String> zuluft3) throws IOException {
        URL serviceWsdlUrl = new URL(String.format("%s?wsdl", serviceUrl.toString()));
        QName qName = new QName(String.format("%s", WSDL_NAMESPACE), "PrinzipskizzeService");
        PrinzipskizzeService prinzipskizzeService = new PrinzipskizzeService(serviceWsdlUrl, qName);
        WAC202Service port = prinzipskizzeService.getPrinzipskizzePort();
        BindingProvider bindingProvider = (BindingProvider) port;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serviceUrl.toString());
        return port.prinzipskizze(aussenluft, fortluft, zentralgeraet,
                abluft1, abluft2, abluft3,
                zuluft1, zuluft2, zuluft3);
    }

    public static byte[] createWAC237(URL serviceUrl, String projekt, String plan, String dokumentersteller, String datum, String aussenluft, String fortluft, String zentralgeraet, List<String> abluft1, List<String> abluft2, List<String> abluft3, List<String> zuluft1, List<String> zuluft2, List<String> zuluft3) throws IOException {
        URL serviceWsdlUrl = new URL(String.format("%s?wsdl", serviceUrl.toString()));
        QName qName = new QName(String.format("%s/wac237", WSDL_NAMESPACE), "PrinzipskizzeWAC237");
        PrinzipskizzeWAC237_Service wac237Service = new PrinzipskizzeWAC237_Service(serviceWsdlUrl, qName);
        WAC237Service port = wac237Service.getWAC237ServicePort();
        BindingProvider bindingProvider = (BindingProvider) port;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serviceUrl.toString());
        return port.prinzipskizzeWAC237(projekt, plan, dokumentersteller, datum,
                aussenluft, fortluft, zentralgeraet,
                abluft1, abluft2, abluft3,
                zuluft1, zuluft2, zuluft3);
    }

}

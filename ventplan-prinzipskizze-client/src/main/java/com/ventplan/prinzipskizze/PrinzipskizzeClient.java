/*
 * ventplan-server
 * ventplan-prinzipskizze-client
 * Copyright (C) 2011-2013 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 11.12.12 16:45
 */
package com.ventplan.prinzipskizze;

import com.ventplan.prinzipskizze.wsclient.Prinzipskizze;
import com.ventplan.prinzipskizze.wsclient.PrinzipskizzeService;
import com.ventplan.prinzipskizze.wsclient.PrinzipskizzeWAC237;
import com.ventplan.prinzipskizze.wsclient.WAC237;

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

    public static final String SERIVCE_BASE_URL = "http://service.ventplan.com/ventplan202";

    public static byte[] create(URL serviceUrl, String aussenluft, String fortluft, String zentralgeraet, List<String> abluft1, List<String> abluft2, List<String> abluft3, List<String> zuluft1, List<String> zuluft2, List<String> zuluft3) throws IOException {
        URL serviceWsdlUrl = new URL(String.format("%s?wsdl", serviceUrl.toString()));
        PrinzipskizzeService prinzipskizzeService = new PrinzipskizzeService(serviceWsdlUrl, new QName(String.format("%s/Prinzipskizze", SERIVCE_BASE_URL), "PrinzipskizzeService"));
        Prinzipskizze port = prinzipskizzeService.getPrinzipskizzePort();
        BindingProvider bindingProvider = (BindingProvider) port;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serviceUrl.toString());
        return port.prinzipskizze(
                aussenluft, fortluft, zentralgeraet,
                abluft1, abluft2, abluft3,
                zuluft1, zuluft2, zuluft3);
    }

    public static byte[] createWAC237(URL serviceUrl, String projekt, String plan, String dokumentersteller, String datum, String aussenluft, String fortluft, String zentralgeraet, List<String> abluft1, List<String> abluft2, List<String> abluft3, List<String> zuluft1, List<String> zuluft2, List<String> zuluft3) throws IOException {
        URL serviceWsdlUrl = new URL(String.format("%s?wsdl", serviceUrl.toString()));
        WAC237 wac237Service = new WAC237(serviceWsdlUrl, new QName(String.format("%s/wac237", SERIVCE_BASE_URL), "WAC-237"));
        PrinzipskizzeWAC237 port = wac237Service.getPrinzipskizzeWAC237Port();
        BindingProvider bindingProvider = (BindingProvider) port;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serviceUrl.toString());
        return port.prinzipskizzeWAC237(
                projekt, plan, dokumentersteller, datum,
                aussenluft, fortluft, zentralgeraet,
                abluft1, abluft2, abluft3,
                zuluft1, zuluft2, zuluft3);
    }

}

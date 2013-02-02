package com.ventplan.prinzipskizze;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ventplan.prinzipskizze.PrinzipskizzeConstants.*;

public class PrinzipskizzeHelper {

    private PrinzipskizzeGrafik prinzipskizzeGrafik=new PrinzipskizzeGrafik();
    
    public void addConnector(Map<String, HashMap<String, List<String>>> connector, String luftart, String geschoss, ArrayList<String> list) {
        if (null != list && list.size() > 0) {
            for (String s : list) {
                prinzipskizzeGrafik.addConnector(connector, luftart, geschoss, s);
            }
        }
    }

    public byte[] makePrinzipskizze(String basis, String projekt, String plan, String dokumentersteller, String datum, String aussenluft, String fortluft, String zentralgerat, ArrayList<String> abluft1, ArrayList<String> abluft2, ArrayList<String> abluft3, ArrayList<String> zuluft1, ArrayList<String> zuluft2, ArrayList<String> zuluft3) {
        // Names
        Map<String, String> names = new HashMap<String, String>();
        names.put(PROJEKT, aussenluft);
        names.put(PLAN, plan);
        names.put(DOKUMENTERSTELLER, dokumentersteller);
        names.put(ERSTELLDATUM, datum);
        names.put(AUSSENLUFT, aussenluft);
        names.put(FORTLUFT, fortluft);
        names.put(ZENTRALGERAET, zentralgerat);
        // Connector
        Map<String, HashMap<String, List<String>>> connector = new HashMap<String, HashMap<String, List<String>>>();
        // Abluft
        if (null != abluft1 && abluft1.size() > 0) {
            addConnector(connector, AB, "LVKAB1", abluft1);
        }
        if (null != abluft2 && abluft2.size() > 0) {
            addConnector(connector, AB, "LVKAB2", abluft2);
        }
        if (null != abluft3 && abluft3.size() > 0) {
            addConnector(connector, AB, "LVKAB3", abluft3);
        }
        // Zuluft
        if (null != zuluft1 && zuluft1.size() > 0) {
            addConnector(connector, ZU, "LVKZU1", zuluft1);
        }
        if (null != zuluft2 && zuluft2.size() > 0) {
            addConnector(connector, ZU, "LVKZU2", zuluft2);
        }
        if (null != zuluft3 && zuluft3.size() > 0) {
            addConnector(connector, ZU, "LVKZU3", zuluft3);
        }
        //
        byte[] b = null;
        try {
            File file = prinzipskizzeGrafik.drawText(basis, names, connector);
            if (null != file) {
                b = Files.readAllBytes(file.toPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

}

package com.ventplan.prinzipskizze;

import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PrinzipskizzeClientTest {

    private String aussenluft;
    private String fortluft;
    private String zentralgeraet;

    private List<String> abluftEG;
    private List<String> abluftDG;
    private List<String> abluftOG;

    private List<String> zuluftEG;
    private List<String> zuluftDG;
    private List<String> zuluftOG;

    public void setUp() throws Exception {
        aussenluft = "200LE01";
        fortluft = "200LG01";
        zentralgeraet = "300WAC";

        abluftEG = new ArrayList<String>();
        abluftEG.add("Kinderzimmer 1 / 100ULC");
        abluftEG.add("Wohnzimmer / 100ULC");
        abluftEG.add("Kinderzimmer 1 / 100ULC");
        abluftEG.add("Kinderzimmer 1 / 100ULC");
        abluftEG.add("Wohnzimmer / 100ULC");
        abluftEG.add("Wohnzimmer / 100ULC");

        abluftDG = new ArrayList<String>();
        abluftDG.add("Wohnzimmer / 100ULC");
        abluftDG.add("Wohnzimmer / 100ULC");

        abluftOG = new ArrayList<String>();
        abluftOG.add("Kinderzimmer 1 / 100ULC");
        abluftOG.add("Wohnzimmer / 100ULC");
        abluftOG.add("Wohnzimmer / 100ULC");

        zuluftEG = new ArrayList<String>();
        zuluftEG.add("Büro / 125 URH");
        zuluftEG.add("Büro / 125 URH");

        zuluftDG = new ArrayList<String>();
        zuluftDG.add("Büro / 125 URH");
        zuluftDG.add("Schlafzimmer / 125 URH");

        zuluftOG = new ArrayList<String>();
        zuluftOG.add("Büro / 125 URH");
        zuluftOG.add("Schlafzimmer / 125 URH");
    }

    @Test
    public void testCreate() throws Exception {
        String userHome = System.getProperty("user.home");
        //String url = "http://service.ventplan.com:80/ventplan202/Prinzipskizze";
        String url = "http://localhost:8080/ventplan202/PrinzipskizzeService";
        byte[] b = PrinzipskizzeClient.create(
                new URL(url),
                aussenluft, fortluft, zentralgeraet,
                abluftEG, abluftDG, abluftOG,
                zuluftEG, zuluftDG, zuluftOG);
        URI uri = URI.create(String.format("file://%s/Prinzipskizze.png", userHome));
        File p = new File(uri);
        FileOutputStream fos = new FileOutputStream(p);
        fos.write(b);
        fos.close();
        Desktop.getDesktop().open(p);
    }

    @Test
    public void testCreateWAC237() throws Exception {
        String userHome = System.getProperty("user.home");
        //String url = "http://service.ventplan.com:80/ventplan202/Prinzipskizze";
        String url = "http://localhost:8080/ventplan202/WAC-237";
        byte[] b = PrinzipskizzeClient.createWAC237(
                new URL(url),
                "Projekt Abc", "I hob koa Plan", "Vroni Ventplan", "23.07.2012",
                aussenluft, fortluft, zentralgeraet,
                abluftEG, abluftDG, abluftOG,
                zuluftEG, zuluftDG, zuluftOG);
        URI uri = URI.create(String.format("file://%s/PrinzipskizzeWAC237.png", userHome));
        File p = new File(uri);
        FileOutputStream fos = new FileOutputStream(p);
        fos.write(b);
        fos.close();
        Desktop.getDesktop().open(p);
    }

}

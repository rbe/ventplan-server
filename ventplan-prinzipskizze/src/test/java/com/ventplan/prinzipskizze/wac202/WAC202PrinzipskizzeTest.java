package com.ventplan.prinzipskizze.wac202;

import com.ventplan.prinzipskizze.PrinzipskizzeGrafik;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ventplan.prinzipskizze.PrinzipskizzeConstants.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class WAC202PrinzipskizzeTest {

    public static final String USER_HOME = System.getProperty("user.home");
    public static final String DESKTOP = String.format("%s/Desktop", USER_HOME);

    @Test
    public void generateWAC202() throws IOException {
        PrinzipskizzeGrafik prinzipskizzeGrafik = new WAC202Prinzipskizze();
        // Names
        Map<String, String> names = new HashMap<>();
        names.put(AUSSENLUFT, "200LE04/8");
        names.put(FORTLUFT, "200LG002/4");
        names.put(ZENTRALGERAET, "300WAC");
        // Connector
        Map<String, HashMap<String, List<String>>> connector = new HashMap<>();
        prinzipskizzeGrafik.addConnector(connector, AB, "EG", "Kinderzimmer 1 / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "EG", "Wohnzimmer / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "EG", "Kinderzimmer 1 / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "EG", "Kinderzimmer 1 / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "EG", "Wohnzimmer / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "EG", "Wohnzimmer / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "DG", "Wohnzimmer / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "DG", "Wohnzimmer / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "OG", "Kinderzimmer 1 / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "OG", "Wohnzimmer / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, AB, "OG", "Wohnzimmer / 100ULC");
        prinzipskizzeGrafik.addConnector(connector, ZU, "DG", "Büro / 125 URH");
        prinzipskizzeGrafik.addConnector(connector, ZU, "DG", "Schlafzimmer / 125 URH");
        prinzipskizzeGrafik.addConnector(connector, ZU, "EG", "Büro / 125 URH");
        prinzipskizzeGrafik.addConnector(connector, ZU, "EG", "Büro / 125 URH");
        prinzipskizzeGrafik.addConnector(connector, ZU, "OG", "Büro / 125 URH");
        prinzipskizzeGrafik.addConnector(connector, ZU, "OG", "Schlafzimmer / 125 URH");
        //
        File file = prinzipskizzeGrafik.drawText(names, connector);
        assertNotNull("File has been produced", file);
        assertThat("File length must be greater than 100", file.length(), CoreMatchers.is(greaterThan(100l)));
    }

}

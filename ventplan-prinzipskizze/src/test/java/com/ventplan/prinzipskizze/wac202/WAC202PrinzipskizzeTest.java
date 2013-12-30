package com.ventplan.prinzipskizze.wac202;

import com.ventplan.prinzipskizze.PrinzipskizzeGrafik;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ventplan.prinzipskizze.PrinzipskizzeConstants.*;
import static org.junit.Assert.assertNotNull;

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
        if (file.length() > 0) {
            Path target = Paths.get(DESKTOP, "Plain.png");
            Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
            Desktop.getDesktop().open(target.toFile());
        }
        assertNotNull(file);
    }

}

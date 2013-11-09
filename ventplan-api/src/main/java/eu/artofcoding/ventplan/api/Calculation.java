/*
 * Ventplan
 * ventplan, ventplan
 * Copyright (C) 2011-2013 art of coding UG, http://www.art-of-coding.eu
 * Copyright (C) 2005-2010 Informationssysteme Ralf Bensmann, http://www.bensmann.com
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 18.10.13 21:33
 */

package eu.artofcoding.ventplan.api;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimaps;
import com.google.common.primitives.Doubles;
import eu.artofcoding.ventplan.api.vpx.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.google.common.base.Preconditions.checkArgument;
import static eu.artofcoding.ventplan.api.VpxConstants.ZU_AB;
import static eu.artofcoding.ventplan.api.vpx.GebaudeTyp.MFH;
import static java.lang.Math.*;

public class Calculation {

    /**
     * Dezimalzahl auf 5 runden.
     */
    public static int round5(double factor) {
        return (int) (5.0d * (round(factor / 5.0d)));
    }

    /**
     * Hole alle Zuluft-Räume.
     */
    public static List<Room> filterRaume(@NotNull List<Room> rooms, @NotNull final Luftart... luftart) {
        Predicate<Room> pred = new Predicate<Room>() {
            @Override
            public boolean apply(@NotNull Room input) {
                for (Luftart art : luftart) {
                    if (input.getLuftart().equals(art)) {
                        return true;
                    }
                }
                return false;
            }
        };
        Iterable<Room> filter = Iterables.filter(rooms, pred);
        ArrayList<Room> rooms1 = Lists.newArrayList(filter);
        return rooms1;
    }

    /**
     * Hole alle Zuluft-Räume.
     */
    public static List<Room> filterRaume(@NotNull List<Room> rooms, @NotNull final String... luftart) {
        Predicate<Room> pred = new Predicate<Room>() {
            @Override
            public boolean apply(@NotNull Room input) {
                for (String art : luftart) {
                    if (input.getLuftart().toString().contains(art)) {
                        return true;
                    }
                }
                return false;
            }
        };
        Iterable<Room> filter = Iterables.filter(rooms, pred);
        ArrayList<Room> rooms1 = Lists.newArrayList(filter);
        return rooms1;
    }

    /**
     * Projekt, Gebäudedaten, Geplante Belegung.
     */
    public static double mindestaussenluftrate(@NotNull Building building) {
        BigInteger personenanzahl = building.getPersonenAnzahl();
        double aussluftVsProPerson = building.getPersonenVolumen();
        double mal = personenanzahl.intValue() * aussluftVsProPerson;
        building.setMindestaussenluftrate(mal);
        return mal;
    }

    /**
     * Gebäudedaten - Geometrie anhand eingegebener Räume berechnen.
     */
    public static void geometrieAusRaumdaten(@NotNull Building building) {
        double wohnflache = 0.0d;
        double mittlereRaumhohe = 0.0d;
        for (Room room : building.getRaum()) {
            // Gesamtfläche berechnen
            wohnflache += room.getRaumflache();
            // Mittlere Raumhöhe berechnen
            mittlereRaumhohe += room.getRaumhohe();
        }
        building.getGeometrie().setWohnflache(wohnflache);
        building.getGeometrie().setMittlereRaumhohe(mittlereRaumhohe);
        // Geometrie berechnen
        geometrie(building);
    }

    /**
     * Gebäudedaten - Geometrie berechnen.
     */
    public static void geometrie(@NotNull Building building) {
        checkArgument(null != building.getGeometrie(), "Geometrie fehlt");
        Geometrie geometrie = building.getGeometrie();
        // Luftvolumen der Nutzungseinheit = Wohnfläche * mittlere Raumhöhe
        checkArgument(null != geometrie.getWohnflache(), "Geometrie/Wohnfläche fehlt");
        checkArgument(null != geometrie.getMittlereRaumhohe(), "Geometrie/Mittlere Raumhöhe fehlt");
        double luftvolumen = geometrie.getWohnflache() * geometrie.getMittlereRaumhohe();
        geometrie.setLuftvolumen(luftvolumen);
        // Gelüftetes Volumen = gelüftete Fläche * mittlere Raumhöhe
        checkArgument(null != geometrie.getGelufteteFlache(), "Geometrie/gelüftete Fläche fehlt");
        Optional<Double> geluftetesVolumen = Optional.of(geometrie.getGelufteteFlache() * geometrie.getMittlereRaumhohe());
        // Gelüftetes Volumen = Luftvolumen, wenn kein gelüftetes Volumen berechnet
        geometrie.setGeluftetesVolumen(geluftetesVolumen.or(0.0d));
        // Raumvolumenströme - Gesamtvolumen der Nutzungseinheit
        building.getRaumVs().setGesamtVolumenNE(luftvolumen);
    }

    /**
     * Summe der Fläche aller Räume.
     */
    public static double summeRaumFlache(@NotNull List<Room> rooms) {
        double flache = 0.0d;
        for (Room room : rooms) {
            flache += room.getRaumflache();
        }
        return flache;
    }

    /**
     * Summe der Volumen aller Räume.
     */
    public static double summeRaumVolumen(@NotNull Building building) {
        double volumen = 0.0d;
        for (Room room : building.getRaum()) {
            volumen += room.getRaumvolumen();
        }
        double mittlereRaumhohe = building.getGeometrie().getMittlereRaumhohe();
        double x = 30.0d * mittlereRaumhohe;
        if (volumen < x) {
            volumen = x;
        }
        return volumen;
    }

    /**
     * Addiere alle Volumen(m³)-Spalten aus der Tabelle für Raumvolumenströme (Luftmengenermittlung).
     */
    public static double summeLuftmengeVolumen(@NotNull Building building, @Null Luftart luftart) {
        // Hole alle Räume mit der entsprechenden Luftart; oder alle
        double volumenMitLuftart = 0.0d;
        for (Room room : building.getRaum()) {
            if (null != luftart) {
                if (room.getLuftart().equals(luftart)) {
                    volumenMitLuftart += room.getRaumvolumen();
                }
            } else {
                volumenMitLuftart += room.getRaumvolumen();
            }
        }
        double mittlereRaumhohe = building.getGeometrie().getMittlereRaumhohe();
        double x = 30.0d * mittlereRaumhohe;
        if (volumenMitLuftart < x) {
            volumenMitLuftart = x;
        }
        return volumenMitLuftart;
    }

    private static double geluftetesVolumen(@NotNull Geometrie geometrie) {
        Optional<Double> _geluftetesVolumen = Optional.fromNullable(geometrie.getGeluftetesVolumen());
        return _geluftetesVolumen.or(0.0d);
    }

    /**
     * Eine Raumnummer anhand der Raumdaten erzeugen:
     * Sie besteht immer aus drei Ziffern:
     * 1. Die erste Ziffer steht für das Geschoß (Keller = 0; Erdgeschoß = 1; usw.).
     * 2. Die beiden folgenden Ziffern ergeben sich als fortlaufende Nummer bei 1 angefangen.
     * Beispiel: erster Raum im Erdgeschoß = 101; dritter Raum im Kellergeschoß = 003
     */
    public static void berechneRaumnummern(@NotNull List<Room> rooms) {
        String[] geschosse = {"KG", "EG", "OG", "DG", "SB"};
        for (int geschossIndex = 0; geschossIndex < geschosse.length; geschossIndex++) {
            for (int raumIndex = 0; raumIndex < rooms.size(); raumIndex++) {
                Room room = rooms.get(raumIndex);
                // WAC-151: Nur berechnen, wenn keine Daten eingegeben wurden
                if (Strings.isNullOrEmpty(room.getRaumnummer())) {
                    room.setRaumnummer(String.format("%s%02d", geschossIndex, raumIndex + 1));
                }
            }
        }
    }

    /**
     * Prüfe den Zuluftfaktor
     */
    private static double minMax(double actual, double min, double max) {
        if (actual < min) {
            return min;
        } else if (actual > max) {
            return max;
        } else {
            return actual;
        }
    }

    public static void prufeZuluftfaktor(@NotNull Room room) {
        double neuerZuluftfaktor = 0.0;
        switch (room.getRaumtyp()) {
            case WOH:
                neuerZuluftfaktor = minMax(room.getZuluftfaktor(), 2.5d, 3.5d);
                break;
            case KIN:
            case SLF:
                neuerZuluftfaktor = minMax(room.getZuluftfaktor(), 1.0d, 3.0d);
                break;
            case ESS:
            case ARB:
            case GAS:
                neuerZuluftfaktor = minMax(room.getZuluftfaktor(), 1.0d, 2.0d);
                break;
            default:
        }
        room.setZuluftfaktor(neuerZuluftfaktor);
    }

    /**
     * Gesamt-Außenluft-Volumenstrom berechnen.
     */
    public static double gesamtAussenluftVs(@NotNull Building building) {
        // Fläche = Geometrie, gelüftetes Volumen / mittlere Raumhöhe
        Geometrie g = building.getGeometrie();
        Optional<Double> geluftetesVolumen = Optional.fromNullable(g.getGeluftetesVolumen());
        Optional<Double> mittlereRaumhohe = Optional.fromNullable(g.getMittlereRaumhohe());
        double flache = 0.0d;
        if (geluftetesVolumen.isPresent() && mittlereRaumhohe.isPresent()) {
            flache = g.getGeluftetesVolumen() / g.getMittlereRaumhohe();
        } else { // Fläche = addiere alle Flächen der Räume
            flache = summeRaumFlache(building.getRaum());
            // Wenn Summe < 30, dann 30
            if (flache < 30.0d) {
                flache = 30.0d;
            }
        }
        double r = 0.0d;
        if (flache > 0.0d) {
            r = -0.001 * flache * flache + 1.15 * flache + 20;
        }
        return r;
    }

    /**
     * Wärmeschutz: hoch, niedrig
     */
    public static double warmeschutzFaktor(@NotNull Building building) {
        switch (building.getWarmeschutz()) {
            case HOC:
                return 0.3d;
            case NIE:
                return 0.4d;
            default:
                return 0.0d;
        }
    }

    /**
     * DiffDruck ist abhängig von der Gebäudelage.
     * DIN 1946-6, Seite 37, Tabelle 10, Freie Lüftung
     */
    public static double diffDruck(@NotNull Building building) {
        switch (building.getGebaudeLage()) {
            case SCH:
                return 2.0d;
            case STA:
                return 4.0d;
            default:
                return 0.0d;
        }
    }

    /**
     * Wirksamen Infiltrationsanteil berechnen.
     * DIN 1946-6, Seite 34
     */
    public static double infiltration(@NotNull Building building, boolean ventilator) {
        // TODO unused? double sys = 0.6d;
        // TODO unused? double inf = 1.0d;
        double n50 = 1.0d; // Je nach Kategorie (A,B,C) setzen (Tabelle 9)
        double druckExpo = 2.0d / 3;
        double diffDruck = diffDruck(building);
        //
        if (ventilator) {
            // TODO unused? inf = 0.9f;
            n50 = building.getLuftdichtheitLuftwechsel();
            if (building.getGebaudeTyp().equals(MFH)) {
                // TODO unused? sys = 0.45d;
            }
        } else {
            if (building.getGebaudeTyp().equals(MFH)) {
                // TODO unused? sys = 0.5d;
            }
            //if (building.getLuftdichtheit() != 0) { n50 = 1.5d; }
        }
        //
        if (building.getLuftdichtheit().equals(Luftdichtheit.M)) {
            n50 = building.getLuftdichtheitLuftwechsel();
            druckExpo = building.getLuftdichtheitDruckexponent();
        } else {
            if (building.getGebaudeLage().equals(GebaudeLage.SCH)) {
                diffDruck = 2.0d;
            } else {
                diffDruck = 4.0d;
            }
            if (!ventilator && !building.getGebaudeTyp().equals(MFH)) {
                if (building.getGebaudeLage().equals(GebaudeLage.SCH)) {
                    diffDruck = 5.0d;
                } else {
                    diffDruck = 7.0d;
                }
            }
        }
        //
        Optional<Double> geluftetesVolumen = Optional.fromNullable(building.getGeometrie().getGeluftetesVolumen());
        // TODO unused? Optional<Double> mittlereRaumhohe = Optional.fromNullable(building.getGeometrie().getMittlereRaumhohe());
        // TODO unused? double flache;
        double volumen;
        if (!geluftetesVolumen.isPresent()) {
            // TODO unused? flache = summeRaumFlache(building.getRaum());
            volumen = summeRaumVolumen(building);
        } else {
            // TODO unused? flache = geluftetesVolumen.get() / mittlereRaumhohe.get();
            volumen = geluftetesVolumen.get();
        }
        // wirk wird nach Tabelle 11 Seite 29 ermittelt, DIN 1946-6
        double wirk = ventilator ? 0.45d : 0.5d;
        //
        double r = 0.0d;
        if (volumen > 0 && n50 > 0 && diffDruck > 0 && druckExpo > 0) {
            r = wirk * volumen * n50 * pow(diffDruck / 50, druckExpo);
        }
        return r;
    }

    /**
     * Sind lüftungstechnische Maßnahmen erforderlich?
     */
    public static boolean ltmErforderlich(@NotNull Building building) {
        double infiltration = infiltration(building, false);
        Optional<Double> fakBesAnf = Optional.fromNullable(building.getBesAnfFaktor());
        double volFL = gesamtAussenluftVs(building) * warmeschutzFaktor(building) * fakBesAnf.get();
        return volFL > infiltration;
    }

    public static double gesamtZuluftfaktor(@NotNull Building building) {
        List<Room> zuluftRaume = filterRaume(building.getRaum(), Luftart.ZU, Luftart.ZUA);
        double gesamtZuluftfaktor = 0.0d;
        for (Room room : zuluftRaume) {
            gesamtZuluftfaktor += room.getZuluftfaktor();
        }
        return gesamtZuluftfaktor;
    }

    public static double gesamtAbluftvolumenstrom(@NotNull Building building) {
        List<Room> abluftRaume = filterRaume(building.getRaum(), Luftart.AB, Luftart.ZUA);
        double gesamtAbluftVs = 0.0d;
        for (Room room : abluftRaume) {
            gesamtAbluftVs += room.getAbluftvolumenstrom();
        }
        return gesamtAbluftVs;
    }

    public static List<Room> raumMitAbluftvolumenstromGroesserAls(@NotNull List<Room> rooms, final double abluftvolumenstrom) {
        Predicate<Room> pred = new Predicate<Room>() {
            @Override
            public boolean apply(@NotNull Room input) {
                return input.getAbluftvolumenstrom() > abluftvolumenstrom;
            }
        };
        Iterable<Room> filter = Iterables.filter(rooms, pred);
        ArrayList<Room> rooms1 = Lists.newArrayList(filter);
        return rooms1;
    }

    public static List<Room> raumMitZuluftfaktorGroesserAls(@NotNull List<Room> rooms, final double zuluftfaktor) {
        Predicate<Room> pred = new Predicate<Room>() {
            @Override
            public boolean apply(@NotNull Room input) {
                return input.getZuluftfaktor() > zuluftfaktor;
            }
        };
        Iterable<Room> filter = Iterables.filter(rooms, pred);
        ArrayList<Room> rooms1 = Lists.newArrayList(filter);
        return rooms1;
    }

    /**
     * Automatische Berechnung der Luftmenge und Luftwechsel pro Stunde
     * getrennt nach Abluft und Zuluft im Verhältnis der einzelnen Raumluftvolumenströme
     * zum Gesamt-Raumluftvolumenstrom.
     */
    public static void autoLuftmenge(@NotNull Project project, boolean infiltrationAbziehen) {
        //
        // LTM: erste Berechnung für Raumvolumenströme
        //
        Building building = project.getGebaude();
        // Summiere Daten aus Raumdaten
        List<Room> zuluftRaume = filterRaume(building.getRaum(), Luftart.ZU, Luftart.ZUA);
        double gesamtZuluftfaktor = gesamtZuluftfaktor(building);
        // Eingegebener oder berechneter Wert? Eingegebener wird erst weiter unten errechnet!
        List<Room> abluftRaume = filterRaume(building.getRaum(), Luftart.AB, Luftart.ZUA);
        double gesamtAbluftVs = gesamtAbluftvolumenstrom(building);
        // Gesamt-Außenluftvolumenstrom bestimmen
        double max1 = max(gesamtAbluftVs, gesamtAussenluftVs(building));
        double mindestaussenluftrate = building.getPersonenAnzahl().intValue() * building.getPersonenVolumen();
        building.setMindestaussenluftrate(mindestaussenluftrate);
        double gesamtAussenluft = max(max1, mindestaussenluftrate) * building.getBesAnfFaktor();
        // Gesamt-Außenluftvolumenstrom für lüftungstechnische Maßnahmen
        double gesamtAvsLTM;
        double infilt = infiltration(building, true);
        if (infiltrationAbziehen) {
            gesamtAvsLTM = gesamtAussenluft - infilt;
        } else {
            gesamtAvsLTM = gesamtAussenluft;
        }
        //
        double ltmAbluftSumme = 0.0d;
        building.getRaumVs().setAbluftsumme(0.0d);
        double ltmZuluftSumme = 0.0d;
        building.getRaumVs().setZuluftsumme(0.0d);
        // Alle Räume, die einen Abluftvolumenstrom > 0 haben...
        List<Room> raumMitAbluftvolumenstromGroesser0 = raumMitAbluftvolumenstromGroesserAls(building.getRaum(), 0.0d);
        for (Room room : raumMitAbluftvolumenstromGroesser0) {
            // Abluftvolumenstrom abzgl. Infiltration errechnen
            double ltmAbluftRaum = round(gesamtAvsLTM / gesamtAbluftVs * room.getAbluftvolumenstrom());
            room.setAbluftvolumenstromAbzglInfiltration(ltmAbluftRaum);
            if (room.getRaumvolumen() > 0.0d) {
                room.setLuftwechsel(ltmAbluftRaum / room.getRaumvolumen());
            } else {
                room.setLuftwechsel(0.0d);
            }
            // ZU/AB
            double ltmZuluftRaum;
            if (room.getLuftart().toString().contains(ZU_AB)) {
                ltmZuluftRaum = round(gesamtAvsLTM * room.getZuluftfaktor() / gesamtZuluftfaktor);
                room.setZuluftfaktorAbzglInfiltration(ltmZuluftRaum);
                room.setAbluftvolumenstromAbzglInfiltration(ltmAbluftRaum);
                if (ltmZuluftRaum > ltmAbluftRaum) {
                    room.setLuftwechsel(ltmZuluftRaum / room.getRaumvolumen());
                } else {
                    room.setLuftwechsel(ltmAbluftRaum / room.getRaumvolumen());
                }
                ltmZuluftSumme += ltmAbluftRaum;
            }
        }
        building.getRaumVs().setAbluftsumme(ltmAbluftSumme);
        //
        // LTM: zweite Berechnung für Raumvolumenströme
        //
        Predicate<Room> pred = new Predicate<Room>() {
            @Override
            public boolean apply(@NotNull Room input) {
                return (input.getLuftart().equals(Luftart.ZU) || input.getLuftart().equals(Luftart.AB)) && input.getZuluftfaktor() > 0.0d;
            }
        };
        Iterable<Room> filter = Iterables.filter(building.getRaum(), pred);
        ArrayList<Room> raumMitZuluftfaktorGroesser0 = Lists.newArrayList(filter);
        for (Room room : raumMitZuluftfaktorGroesser0) {
            double ltmZuluftRaum = round(gesamtAvsLTM * room.getZuluftfaktor() / gesamtZuluftfaktor);
            room.setZuluftfaktorAbzglInfiltration(ltmZuluftRaum);
            room.setLuftwechsel(ltmZuluftRaum / room.getRaumvolumen());
            ltmZuluftSumme += ltmZuluftRaum; // TODO OK?
        }
        building.getRaumVs().setZuluftsumme(ltmZuluftSumme);
        // Überströmvolumenstrom = Vorschlag: Raumvolumenstrom
        // WAC-151: Wegen möglicher manueller Änderung nur vorschlagen, wenn kein Wert vorhanden ist
        if (!project.getZentralgerat().isManuell()) {
            for (Room room : building.getRaum()) {
                Optional<Double> uberstromvolumenstrom = Optional.fromNullable(room.getUberstromvolumenstrom());
                if (!uberstromvolumenstrom.isPresent()) {
                    switch (room.getLuftart()) {
                        case ZU:
                            // Abzgl. Infiltration
                            room.setUberstromvolumenstrom(room.getZuluftfaktorAbzglInfiltration());
                            break;
                        case AB:
                            // Abzgl. Infiltration
                            room.setUberstromvolumenstrom(room.getAbluftvolumenstromAbzglInfiltration());
                            break;
                        case ZUA:
                            // Abzgl. Infiltration
                            room.setUberstromvolumenstrom(Math.abs(room.getZuluftfaktorAbzglInfiltration() - room.getAbluftvolumenstromAbzglInfiltration()));
                            break;
                    }
                }
            }
        }
    }

    /**
     * Gesamt-Außenluftvolumenströme für Nutzungseinheit
     */
    public static void gesamtAvsNe(@NotNull Project project) {
        Building building = project.getGebaude();
        Aussenluftvolumenstrome aussenluftVs = building.getAussenluftVs();
        // Gelüftetes Volumen
        double geluftetesVolumen = geluftetesVolumen(building.getGeometrie());
        // Grundlüftung
        double neGrundluftung = gesamtAussenluftVs(building);
        aussenluftVs.setGesamtAvsNeLvsNennluftung(neGrundluftung);
        aussenluftVs.setGesamtAvsNeLwNennluftung(neGrundluftung / geluftetesVolumen);
        // Mindestlüftung
        double neMindestluftung = 0.7f * neGrundluftung;
        aussenluftVs.setGesamtAvsNeLvsMindestluftung(neMindestluftung);
        aussenluftVs.setGesamtAvsNeLwMindestluftung(neMindestluftung / geluftetesVolumen);
        // Intesivlüftung
        double neIntensivluftung = 1.3f * neGrundluftung;
        aussenluftVs.setGesamtAvsNeLvsIntesivluftung(neIntensivluftung);
        aussenluftVs.setGesamtAvsNeLwIntesivluftung(neIntensivluftung / geluftetesVolumen);
        // Feuchteschutz
        double wsFaktor = warmeschutzFaktor(building);
        double neFeuchteschutz = wsFaktor * neGrundluftung;
        aussenluftVs.setGesamtAvsNeLvsFeuchteschutz(neFeuchteschutz);
        aussenluftVs.setGesamtAvsNeLwFeuchteschutz(neFeuchteschutz / geluftetesVolumen);
    }

    /**
     * Gesamtabluftvolumenströme der Räume
     */
    public static void gesamtAvsRaum(@NotNull Project project) {
        Building building = project.getGebaude();
        Aussenluftvolumenstrome aussenluftVs = building.getAussenluftVs();
        // Gelüftetes Volumen
        double geluftetesVolumen = geluftetesVolumen(building.getGeometrie());
        //
        List<Room> abluftRaume = filterRaume(building.getRaum(), Luftart.AB, Luftart.ZUA);
        double raumGrundluftung = 0.0d;
        for (Room room : abluftRaume) {
            raumGrundluftung += room.getAbluftvolumenstrom();
        }
        aussenluftVs.setGesamtAvsRaumLvsNennluftung(raumGrundluftung);
        aussenluftVs.setGesamtAvsRaumLwNennluftung(raumGrundluftung / geluftetesVolumen);
        // Mindestlüftung
        double raumMindestluftung = 0.7f * raumGrundluftung;
        aussenluftVs.setGesamtAvsRaumLvsMindestluftung(raumMindestluftung);
        aussenluftVs.setGesamtAvsRaumLwMindestluftung(raumMindestluftung / geluftetesVolumen);
        // Intesivlüftung
        double raumIntensivluftung = 1.3f * raumGrundluftung;
        aussenluftVs.setGesamtAvsRaumLvsIntesivluftung(raumIntensivluftung);
        aussenluftVs.setGesamtAvsRaumLwIntesivluftung(raumIntensivluftung / geluftetesVolumen);
        // Feuchteschutz
        double wsFaktor = warmeschutzFaktor(building);
        double raumFeuchteschutz = wsFaktor * raumGrundluftung;
        aussenluftVs.setGesamtAvsRaumLvsFeuchteschutz(raumFeuchteschutz);
        aussenluftVs.setGesamtAvsRaumLwFeuchteschutz(raumFeuchteschutz / geluftetesVolumen);
    }

    /**
     * Personenbezogene Gesamtaußenluftvolumenströme
     */
    public static void gesamtAvsPerson(@NotNull Project project) {
        Building building = project.getGebaude();
        Aussenluftvolumenstrome aussenluftVs = building.getAussenluftVs();
        // Gelüftetes Volumen
        double geluftetesVolumen = geluftetesVolumen(building.getGeometrie());
        //
        assert building.getMindestaussenluftrate() > 0.0d;
        double personGrundluftung = building.getMindestaussenluftrate();
        aussenluftVs.setGesamtAvsPersonLvsNennluftung(personGrundluftung);
        aussenluftVs.setGesamtAvsPersonLwNennluftung(personGrundluftung / geluftetesVolumen);
        // Mindestlüftung
        double personMindestluftung = 0.7f * personGrundluftung;
        aussenluftVs.setGesamtAvsPersonLvsMindestluftung(personMindestluftung);
        aussenluftVs.setGesamtAvsPersonLwMindestluftung(personMindestluftung / geluftetesVolumen);
        // Intesivlüftung
        double personIntensivluftung = 1.3f * personGrundluftung;
        aussenluftVs.setGesamtAvsPersonLvsIntesivluftung(personIntensivluftung);
        aussenluftVs.setGesamtAvsPersonLwIntesivluftung(personIntensivluftung / geluftetesVolumen);
        // Feuchteschutz
        double wsFaktor = warmeschutzFaktor(building);
        double personFeuchteschutz = wsFaktor * personGrundluftung;
        aussenluftVs.setGesamtAvsPersonLvsFeuchteschutz(personFeuchteschutz);
        aussenluftVs.setGesamtAvsPersonLwFeuchteschutz(personFeuchteschutz / geluftetesVolumen);
    }

    /**
     * Berechne Aussenluftvolumenströme.
     */
    public static void aussenluftVs(@NotNull Project project, boolean infiltrationAbziehen) {
        Building building = project.getGebaude();
        // Gesamt-Außenluftvolumentstrom
        Aussenluftvolumenstrome aussenluftVs = building.getAussenluftVs();
        double gesamtAvs = gesamtAussenluftVs(building);
        double wsFaktor = warmeschutzFaktor(building);
        aussenluftVs.setGesamt(gesamtAvs * wsFaktor * building.getBesAnfFaktor());
        // Infiltration
        aussenluftVs.setInfiltration(infiltration(building, false));
        // Lüftungstechnische Maßnahmen erforderlich?
        // WAC-115: Hinweis LTM erforderlich -> Meldung ausblenden!
        if (ltmErforderlich(building)) {
            aussenluftVs.setMassnahme("Lüftungstechnische Maßnahmen erforderlich!");
        } else {
            aussenluftVs.setMassnahme("");
        }
        autoLuftmenge(project, infiltrationAbziehen);
        double geluftetesVolumen = geluftetesVolumen(building.getGeometrie());
        // Gesamt-Außenluftvolumenströme für Nutzungseinheit
        gesamtAvsNe(project);
        // Gesamtabluftvolumenströme der Räume
        gesamtAvsRaum(project);
        // Personenbezogene Gesamtaußenluftvolumenströme
        gesamtAvsPerson(project);
        // Ausgabe der Gesamt-Luftvolumenströme für LTM abzgl. Infiltration
        double infiltration = infiltrationAbziehen ? infiltration(building, true) : 0.0d;
        // WAC-209 Höchster Wert Nennlüftung abzgl. Infiltration
        aussenluftVs.setGesamtLvsLtmLvsNennluftung(
                Doubles.max(
                        aussenluftVs.getGesamtAvsNeLvsNennluftung(),
                        aussenluftVs.getGesamtAvsRaumLvsNennluftung(),
                        aussenluftVs.getGesamtAvsPersonLvsNennluftung()
                ) - infiltration
        );
        // WAC-209 Höchster Wert MindestLüftung abzgl. Infiltration
        aussenluftVs.setGesamtLvsLtmLvsMindestluftung(
                Doubles.max(
                        aussenluftVs.getGesamtAvsNeLvsMindestluftung(),
                        aussenluftVs.getGesamtAvsRaumLvsMindestluftung(),
                        aussenluftVs.getGesamtAvsPersonLvsMindestluftung()
                ) - infiltration
        );
        // WAC-209 Höchster Wert Intensivlüftung abzgl. Infiltration
        aussenluftVs.setGesamtLvsLtmLvsIntensivluftung(
                Doubles.max(
                        aussenluftVs.getGesamtAvsNeLvsIntesivluftung(),
                        aussenluftVs.getGesamtAvsRaumLvsIntesivluftung(),
                        aussenluftVs.getGesamtAvsPersonLvsIntesivluftung()
                ) - infiltration
        );
        // WAC-209 Höchster Wert Feuchteschutz abzgl. Infiltration
        aussenluftVs.setGesamtLvsLtmLvsFeuchteschutz(
                Doubles.max(
                        aussenluftVs.getGesamtAvsNeLvsFeuchteschutz(),
                        aussenluftVs.getGesamtAvsRaumLvsFeuchteschutz(),
                        aussenluftVs.getGesamtAvsPersonLvsFeuchteschutz()
                ) - infiltration
        );
        // WAC-209
        double ltmGrundluftung = Math.max(
                building.getRaumVs().getAbluftsumme(),
                building.getRaumVs().getZuluftsumme()
        );
        ltmGrundluftung = Math.max(gesamtAvs, ltmGrundluftung);
        ltmGrundluftung = Math.max(building.getMindestaussenluftrate(), ltmGrundluftung);
        aussenluftVs.setGesamtLvsLtmLwNennluftung((ltmGrundluftung - infiltration) / geluftetesVolumen);
        double ltmMindestluftung = 0.7f * ltmGrundluftung - infiltration;
        aussenluftVs.setGesamtLvsLtmLwMindestluftung(ltmMindestluftung / geluftetesVolumen);
        double ltmIntensivluftung = 1.3f * ltmGrundluftung - infiltration;
        aussenluftVs.setGesamtLvsLtmLwIntensivluftung(ltmIntensivluftung / geluftetesVolumen);
        // Lüftung zum Feuchteschutz = Nennlüftung / 1.3
        aussenluftVs.setGesamtLvsLtmLwFeuchteschutz(aussenluftVs.getGesamtLvsLtmLwNennluftung() / 1.3f);
        //
        // Raumvolumenströme
        //
        // Raumvolumenströme - Gesamtaussenluftvolumentrom mit Infiltration
        building.getAussenluftVs().setGesamtAbzglInfiltration(ltmGrundluftung);
        // Raumvolumenströme - Luftwechsel der Nutzungseinheit
        double luftwechselNE = ltmGrundluftung / building.getRaumVs().getGesamtVolumenNE();
        building.getRaumVs().setLuftwechselNE(luftwechselNE);
        //
        setzeAussenluftVsMindestwerte(project);
    }

    public static void setzeAussenluftVsMindestwerte(@NotNull Project project) {
        Aussenluftvolumenstrome aussenluftVs = project.getGebaude().getAussenluftVs();
        switch (project.getZentralgerat().getName()) {
            case WestaflexConstant.S_400WAC:
                aussenluftVs.setGesamtLvsLtmLvsMindestluftung(max(75.0d, aussenluftVs.getGesamtLvsLtmLvsMindestluftung()));
                aussenluftVs.setGesamtLvsLtmLvsFeuchteschutz(max(75.0d, aussenluftVs.getGesamtLvsLtmLvsFeuchteschutz()));
                break;
        }
    }

    /**
     * Bestimme alle Verteilebenen.
     */
    public static Map<Ventilebene, Room> groupByVerteilebenen(@NotNull List<Room> rooms) {
        checkArgument(null != rooms && !rooms.isEmpty(), "Keine Räume");
        //
        Function<Room, Ventilebene> fn = new Function<Room, Ventilebene>() {
            @NotNull
            @Override
            public Ventilebene apply(@NotNull Room input) {
                return input.getVentilebene();
            }
        };
        ImmutableListMultimap<Ventilebene, Room> index = Multimaps.index(rooms, fn);
        // Cast to java.util.Map: http://code.google.com/p/google-collections/issues/detail?id=118#c2
        //Map<Ventilebene, Room> map = (Map<Ventilebene, Room>) (Map<?, ?>) index.asMap();
        @SuppressWarnings({"unchecked"})
        Map<Ventilebene, Room> map = new TreeMap<>((Map<Ventilebene, Room>) (Map<?, ?>) index.asMap());
        return map;
    }

    public static Map<Ventilebene, Integer> countVentileProVerteilebene(List<Room> rooms, Luftart luftart) {
        checkArgument(null != rooms && !rooms.isEmpty(), "Keine Räume");
        return null;
    }

}

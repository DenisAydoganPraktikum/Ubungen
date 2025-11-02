package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import export.DateiSchreiberCreator;
import export.CsvDateiSchreiberCreator;
import export.TxtDateiSchreiberCreator;
 
public class BahnhofModel {
	
	private Bahnhof bahnhof;
	
	public BahnhofModel()
	{
	}
	
	public String getUeberschrift()
	{
		 return "Verwaltung von Bahnhöfen";
	} 
	
	// Implementierung für das Schreiben in CSV (Nutzt Factory Method)
	public void schreibeBahnhofInCsvDatei() throws IOException
	{
		if (this.bahnhof == null) {
            throw new IOException("Kein Bahnhof zum Speichern vorhanden.");
        }
		
		// Kreieren eines Creator-Objekts und Aufruf der Methode schreibeInDatei()
		DateiSchreiberCreator creator = new CsvDateiSchreiberCreator();
		creator.schreibeInDatei(this.bahnhof);
	}
	
	// Implementierung für das Schreiben in TXT (Nutzt Factory Method)
	public void schreibeBahnhofInTxtDatei() throws IOException
	{
		if (this.bahnhof == null) {
            throw new IOException("Kein Bahnhof zum Speichern vorhanden.");
        }
		
		// Kreieren des TxtCreators und Aufruf der Methode schreibeInDatei()
		DateiSchreiberCreator creator = new TxtDateiSchreiberCreator();
		creator.schreibeInDatei(this.bahnhof);
	}
	
	// Implementierung für das Lesen aus CSV (Unverändert)
	public void leseBahnhofAusCsvDatei() throws IOException, NumberFormatException {
		// Liest aus "Bahnhof.csv"
        BufferedReader ein = new BufferedReader(new FileReader("Bahnhof.csv"));
        String zeileStr = ein.readLine();
        if (zeileStr == null) {
            ein.close();
            throw new IOException("CSV-Datei ist leer.");
        }
        
        String[] zeile = zeileStr.split(";");
        if (zeile.length < 5) {
            ein.close();
            throw new IOException("CSV-Datei hat ein ungültiges Format.");
        }
        
        // Format erwartet: Name;Ort;AnzahlGleise;LetzteRenovierung;Zugarten(mit _)
        String name = zeile[0];
        String ort = zeile[1];
        int anzahlGleise = Integer.parseInt(zeile[2]); // Kann NumberFormatException werfen
        int letzteRenovierung = Integer.parseInt(zeile[3]); // Kann NumberFormatException werfen
        String[] zugarten = zeile[4].split("_"); // Trennt Zugarten am Unterstrich
        
        // Erstellt und setzt den neuen Bahnhof
        this.bahnhof = new Bahnhof(name, ort, anzahlGleise, letzteRenovierung, zugarten);
        ein.close();
    }
	
	// Implementierung für das Lesen aus TXT (NEU)
    public void leseBahnhofAusTxtDatei() throws IOException, NumberFormatException {
        // Liest aus der Export-Datei BahnhoefeAusgabe.txt
        BufferedReader ein = new BufferedReader(new FileReader("BahnhoefeAusgabe.txt")); 
        String line;
        
        // Datenfelder
        String name = null;
        String ort = null;
        String anzahlGleiseStr = null;
        String letzteRenovierungStr = null;
        String zugartenStr = null;
        
        // Lese Zeile für Zeile und extrahiere die Werte, die nach dem Doppelpunkt kommen
        while ((line = ein.readLine()) != null) {
        	if (line.startsWith("Name des Bahnhofs: ")) {
                name = line.substring("Name des Bahnhofs: ".length()).trim();
            } else if (line.startsWith("Ort des Bahnhofs: ")) {
                ort = line.substring("Ort des Bahnhofs: ".length()).trim();
            } else if (line.startsWith("Anzahl Gleise: ")) {
                anzahlGleiseStr = line.substring("Anzahl Gleise: ".length()).trim();
            } else if (line.startsWith("Letzte Renovierung: ")) {
                letzteRenovierungStr = line.substring("Letzte Renovierung: ".length()).trim();
            } else if (line.startsWith("Zugarten: ")) {
                // Trennung der Zugarten erfolgt durch Leerzeichen
                zugartenStr = line.substring("Zugarten: ".length()).trim();
            }
        }
        ein.close();
        
        if (name == null || ort == null || anzahlGleiseStr == null || letzteRenovierungStr == null || zugartenStr == null) {
             throw new IOException("TXT-Datei ist unvollständig oder ungültig formatiert.");
        }

        // Parsing
        int anzahlGleise = Integer.parseInt(anzahlGleiseStr);
        int letzteRenovierung = Integer.parseInt(letzteRenovierungStr);
        String[] zugarten = zugartenStr.split(" "); // Trennt Zugarten am Leerzeichen
        
        this.bahnhof = new Bahnhof(name, ort, anzahlGleise, letzteRenovierung, zugarten);
    }
	
	public Bahnhof getBahnhof()
	{
		return bahnhof;
	}
	
	public void setBahnhof(Bahnhof bahnhof)
	{
		this.bahnhof = bahnhof;
	}
}
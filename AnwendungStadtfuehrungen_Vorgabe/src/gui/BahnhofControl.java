package gui;

import java.io.IOException;
import business.Bahnhof;
import business.BahnhofModel;
import javafx.stage.Stage; 
import ownUtil.PlausiException;

public class BahnhofControl {

	private BahnhofView view;
	private BahnhofModel model;
	
    public BahnhofControl(Stage primaryStage)
	{
		this.model = new BahnhofModel();
		this.view = new BahnhofView(this, primaryStage, model);
	}
	
    // Übernimmt Strings von der View (unverändert)
    public void nehmeBahnhofAuf(String name, String ort, String anzahlGleiseStr, String letzteRenovierungStr, String zugartenStr)
    {
    	try {
    		// Start: Validierung ( Anforderung) 
    		if (name == null || name.isEmpty()) {
                throw new PlausiException(PlausiException.FORMAL, "Name");
            }
            if (ort == null || ort.isEmpty()) {
                throw new PlausiException(PlausiException.FORMAL, "Ort");
            }
            if (anzahlGleiseStr == null || anzahlGleiseStr.isEmpty()) {
                throw new PlausiException(PlausiException.FORMAL, "Anzahl Gleise");
            }
            if (letzteRenovierungStr == null || letzteRenovierungStr.isEmpty()) {
                throw new PlausiException(PlausiException.FORMAL, "Letzte Renovierung");
            }
            if (zugartenStr == null || zugartenStr.isEmpty()) {
                throw new PlausiException(PlausiException.FORMAL, "Zugarten");
            }
            //  Ende: Validierung 

            //  Start: Parsing (Umwandlung) 
            int anzahlGleise;
            try {
                anzahlGleise = Integer.parseInt(anzahlGleiseStr);
            } catch (NumberFormatException e) {
                throw new PlausiException(PlausiException.INHALTLICH, "Anzahl Gleise (muss eine Zahl sein)");
            }
            
            int letzteRenovierung;
            try {
                letzteRenovierung = Integer.parseInt(letzteRenovierungStr);
            } catch (NumberFormatException e) {
                throw new PlausiException(PlausiException.INHALTLICH, "Letzte Renovierung (muss eine Zahl sein)");
            }
            
            String[] zugarten = zugartenStr.split(";"); // Trennt am Semikolon (wie im UI-Label)
            //  Ende: Parsing 
    		
    		//  Start: Model aktualisieren 
			model.setBahnhof(new Bahnhof(name, ort, anzahlGleise, letzteRenovierung, zugarten));
			view.zeigeInformationsfensterAn("Der Bahnhof wurde aufgenommen!");
			// --- Ende: Model aktualisieren ---
			
    	} catch (PlausiException e) {
    		// Fängt die eigene Exception
    		view.zeigeFehlermeldungsfensterAn(e.getPlausiTyp() + "r Fehler", e.getMessage());
    	} catch (Exception e) {
    		// Fängt alle anderen Fehler
    		view.zeigeFehlermeldungsfensterAn("Allgemeiner Fehler", "Ein unerwarteter Fehler ist aufgetreten: " + e.getMessage());
    	}
    }
    
    // NEUE Version: unterstützt TXT-Export
    void schreibeBahnhofInDatei(String typ) throws IOException
    {
    	 try{
    		 // Prüfen, ob überhaupt ein Bahnhof da ist
    		 if (model.getBahnhof() == null) {
                 view.zeigeInformationsfensterAn("Es wurde noch kein Bahnhof aufgenommen, der gespeichert werden könnte.");
                 return;
             }
    		 
    		 if("csv".equals(typ))
    		 {
    			 model.schreibeBahnhofInCsvDatei();
    			 view.zeigeInformationsfensterAn("Der Bahnhof wurde erfolgreich in 'BahnhoefeAusgabe.csv' gespeichert.");
    		 }
    		 else if ("txt".equals(typ)) // NEU: TXT-Export
    	 	 {
    			 model.schreibeBahnhofInTxtDatei();
    			 view.zeigeInformationsfensterAn("Der Bahnhof wurde erfolgreich in 'BahnhoefeAusgabe.txt' gespeichert.");
    	 	 }
    		 else 
    	 	 {
    			 view.zeigeInformationsfensterAn(typ + "-Export ist nicht implementiert!");
    	 	 }
    	 }
    	 catch(IOException exc) {
             view.zeigeFehlermeldungsfensterAn("Speicherfehler", "Fehler beim Speichern der Datei: " + exc.getMessage());
         }
    	 catch(Exception exc)
    	 {
    		 view.zeigeFehlermeldungsfensterAn("Allgemeiner Fehler", "Unbekannter Fehler beim Speichern: " + exc.getMessage());
    	 }
    }
   
    // NEUE Version: unterstützt TXT-Import
    public void leseBahnhofAusDatei(String typ) {
        try {
            if ("csv".equals(typ)) {
                model.leseBahnhofAusCsvDatei();
                view.zeigeInformationsfensterAn("Bahnhof erfolgreich aus 'Bahnhof.csv' importiert.");
                // Nach dem Import die View aktualisieren
                view.zeigeBahnhofAn(); 
            } else if ("txt".equals(typ)) { // NEU: TXT-Import
            	model.leseBahnhofAusTxtDatei();
                view.zeigeInformationsfensterAn("Bahnhof erfolgreich aus 'BahnhoefeAusgabe.txt' importiert.");
                // Nach dem Import die View aktualisieren
                view.zeigeBahnhofAn();
            } else {
                view.zeigeInformationsfensterAn(typ + "-Import ist nicht implementiert!");
            }
        } catch (IOException exc) {
            view.zeigeFehlermeldungsfensterAn("Importfehler", "Fehler beim Lesen der Datei: " + exc.getMessage());
        } catch (NumberFormatException exc) {
             view.zeigeFehlermeldungsfensterAn("Importfehler", "Fehler im Dateiformat: Ungültige Zahl gefunden.");
        } catch (Exception exc) {
            view.zeigeFehlermeldungsfensterAn("Allgemeiner Fehler", "Unbekannter Fehler beim Importieren: " + exc.getMessage());
        }
    }
}
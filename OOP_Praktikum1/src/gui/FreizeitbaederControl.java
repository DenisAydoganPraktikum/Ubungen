package gui;

import java.io.IOException;

import business.Freizeitbad;
import business.FreizeitbaederModel;
import javafx.stage.Stage; 
import ownUtil.PlausiException;

public class FreizeitbaederControl 
{
	private FreizeitbaederView view;
	private FreizeitbaederModel model;
	// speichert temporaer ein Objekt vom Typ Freizeitbad
    //private Freizeitbad freizeitbad;
    
	public FreizeitbaederControl(Stage primaryStage)
	{
		this.model = new FreizeitbaederModel();
		this.view = new FreizeitbaederView(this, primaryStage, model);
	}
	
    public void nehmeFreizeitbadAuf(String name, String von, String bis, String laenge, String temperatur)
    {
    	try{
    		model.setFreizeitbad(new Freizeitbad(name, von, bis, laenge, temperatur));
    		view.zeigeInformationsfensterAn("Das Freizeitbad wurde aufgenommen!");
       	}
       	catch(PlausiException exc){
       		view.zeigeFehlermeldungsfensterAn(exc.getPlausiTyp() + "er ", exc.getMessage());
     	}
    }
    
   /* public Freizeitbad getFreizeitbad()
    {
    	return freizeitbad;
    }
    */
    void schreibeFreizeitbaederInDatei(String typ)
    {
    	 try{
    		 if("csv".equals(typ))
    		 {
    			 // Aufruf des Models zum Schreiben des
    			 // Freizeitbads in die Datei des vorgegebenen
    			 // Typs und Ausgabe der Meldung
    			 model.schreibeFreizeitbaederInCsvDatei();
    		 }
    		 else
    	 	{
    		 view.zeigeInformationsfensterAn("Noch nicht implementiert!");
    	 	}
    	 	}
    	
    	 catch(IOException exc)
    	 {
    		 view.zeigeFehlermeldungsfensterAn(exc.getCause() + "er ", "IOException beim Speichern!");
    	 }
    	catch(Exception exc)
    	 {
    		view.zeigeFehlermeldungsfensterAn(exc.getCause() + "er ", "Unbekannter Fehler beim Speichern!");
    	}
    }
}

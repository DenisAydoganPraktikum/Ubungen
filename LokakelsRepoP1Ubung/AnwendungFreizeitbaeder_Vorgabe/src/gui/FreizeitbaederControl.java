package gui;

import java.io.IOException;
import business.Freizeitbad;
import business.FreizeitbaederModel;
import javafx.stage.Stage;

public class FreizeitbaederControl {

	private FreizeitbaederModel model;
	private FreizeitbaederView view;
	
	
	
	public FreizeitbaederControl(Stage PrimaryStage) {
		this.model = new FreizeitbaederModel();
		this.view = new FreizeitbaederView(this, PrimaryStage, model);	
		
	}
	
	void schreibeFreizeitbaederInDatei (String typ){
		try{
			if("csv".contentEquals(typ)) {
		
			model.schreibeFreizeitbaederInCsvDatei();
			
		}else {
			view.zeigeInformationsfensterAn("Noch nicht implementiert!");
		}
		}catch (IOException exc) {
			view.zeigeFehlermeldungsfensterAn("IO Fehler", typ);
		}
	}
}

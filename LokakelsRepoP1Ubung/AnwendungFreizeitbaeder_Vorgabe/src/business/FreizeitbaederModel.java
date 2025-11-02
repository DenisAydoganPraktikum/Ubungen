package business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import gui.FreizeitbaederControl;
import gui.FreizeitbaederView;

public class FreizeitbaederModel {
	
	private FreizeitbaederControl control;
	private FreizeitbaederView view;
	
	private Freizeitbad Freizeitbad;
	
	public Freizeitbad getFreizeitbad() {
		return Freizeitbad;
	}
	public void setFreizeitbad(Freizeitbad Freizeitbad) {
		this.Freizeitbad = Freizeitbad;
	}
	
	public FreizeitbaederModel() {
		
	}
	
	public void schreibeFreizeitbaederInCsvDatei() throws IOException{
		
		BufferedWriter aus = new BufferedWriter(new FileWriter("Freizeitbaeder.csv", true));
		aus.write(this.getFreizeitbad().gibFreizeitbadZurueck(';'));
		aus.close();
	}
}

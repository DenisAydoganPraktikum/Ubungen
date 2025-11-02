package export;

import java.io.IOException;

public abstract class DateiSchreiberCreator {
	
	// Die abstrakte Fabrikmethode, die das Product erzeugt
	public abstract DateiSchreiber factoryMethod() throws IOException;
	
	// Eine Methode, die das Product (unabhängig vom Typ) benutzt
	public void schreibeInDatei(Object object) throws IOException {
		// 1. Erzeuge das Produkt (Factory Method wird aufgerufen)
		DateiSchreiber writer = factoryMethod(); 
		// 2. Benutze das Produkt
		writer.fuegelnDateiHinzu(object);
		// 3. Schließe das Produkt
		writer.schliesseDatei();
	}
}
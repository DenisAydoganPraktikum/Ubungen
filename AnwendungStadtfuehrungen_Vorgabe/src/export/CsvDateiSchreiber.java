package export;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import business.Bahnhof;

public class CsvDateiSchreiber extends DateiSchreiber {
	
	private BufferedWriter writer;
	private static final String DATEINAME = "BahnhoefeAusgabe.csv";

	public CsvDateiSchreiber() throws IOException {
		// BufferedWriter im Konstruktor initialisieren 
		this.writer = new BufferedWriter(new FileWriter(DATEINAME, true));
	}

	@Override
	public void fuegelnDateiHinzu(Object object) throws IOException {
		if (object instanceof Bahnhof) {
			Bahnhof bahnhof = (Bahnhof) object;
			// Nutzt die für CSV angepasste Methode aus Bahnhof.java
			this.writer.write(bahnhof.gibBahnhofZurueckFuerCsv());
			this.writer.newLine(); 
		} else {
			throw new IllegalArgumentException("Das Objekt ist kein Bahnhof.");
		}
	}

	@Override
	public void schliesseDatei() throws IOException {
		this.writer.close();
	}
}
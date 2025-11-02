package export;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import business.Bahnhof;

public class TxtDateiSchreiber extends DateiSchreiber {
	
	private BufferedWriter writer;
	private static final String DATEINAME = "BahnhoefeAusgabe.txt";

	public TxtDateiSchreiber() throws IOException {
		// Initialisierung des Writers Append-Modus wie bei CSV
		this.writer = new BufferedWriter(new FileWriter(DATEINAME, true));
	}

	@Override
	public void fuegelnDateiHinzu(Object object) throws IOException {
		if (object instanceof Bahnhof) {
			Bahnhof bahnhof = (Bahnhof) object;
			
			// Spezielle TXT-Formatierung mit Labels
			writer.write("--- Daten des Bahnhofs ---");
			writer.newLine();
			writer.write("Name des Bahnhofs: " + bahnhof.getName());
			writer.newLine();
			writer.write("Ort des Bahnhofs: " + bahnhof.getOrt());
			writer.newLine();
			writer.write("Anzahl Gleise: " + bahnhof.getAnzahlGleise());
			writer.newLine();
			writer.write("Letzte Renovierung: " + bahnhof.getLetzteRenovierung());
			writer.newLine();
			writer.write("Zugarten: " + bahnhof.getZugartenAlsString(' ')); 
			writer.newLine();
			writer.write("--------------------------");
			writer.newLine();
		} else {
			throw new IllegalArgumentException("Das Objekt ist kein Bahnhof.");
		}
	}

	@Override
	public void schliesseDatei() throws IOException {
		this.writer.close();
	}
}
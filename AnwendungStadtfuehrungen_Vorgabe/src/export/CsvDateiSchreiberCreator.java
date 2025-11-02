package export;

import java.io.IOException;

public class CsvDateiSchreiberCreator extends DateiSchreiberCreator {

	@Override
	public DateiSchreiber factoryMethod() throws IOException {
		// Gibt das konkrete Produkt für CSV zurück
		return new CsvDateiSchreiber();
	}
}
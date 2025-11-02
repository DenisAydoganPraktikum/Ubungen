package export;

import java.io.IOException;

public class TxtDateiSchreiberCreator extends DateiSchreiberCreator {

	@Override
	public DateiSchreiber factoryMethod() throws IOException {
		// Gibt das konkrete Produkt für TXT zurück
		return new TxtDateiSchreiber();
	}
}
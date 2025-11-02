package export;

import java.io.IOException;

public abstract class DateiSchreiber {
    
    // Die Methode, um das zu speichernde Objekt (hier: Bahnhof) zur Datei hinzuzufügen
    public abstract void fuegelnDateiHinzu(Object object) throws IOException;
    
    // Die Methode, um die Datei zu schließen
    public abstract void schliesseDatei() throws IOException;
}
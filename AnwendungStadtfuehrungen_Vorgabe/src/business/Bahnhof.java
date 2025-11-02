package business;

public class Bahnhof {
	
    private String name;
    private String ort;
    private int anzahlGleise;
    private int letzteRenovierung;
    private String[] zugarten;
    
    public Bahnhof(String name, String ort, int anzahlGleise,
       	int letzteRenovierung, String[] zugarten){
    	this.name = name;
      	this.ort = ort;
       	this.anzahlGleise = anzahlGleise;
       	this.letzteRenovierung = letzteRenovierung;
       	this.zugarten = zugarten;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public int getAnzahlGleise() {
		return anzahlGleise;
	}

	public void setAnzahlGleise(int anzahlGleise) {
		this.anzahlGleise = anzahlGleise;
	}

	public int getLetzteRenovierung() {
		return letzteRenovierung;
	}

	public void setLetzteRenovierung(int letzteRenovierung) {
		this.letzteRenovierung = letzteRenovierung;
	}

	public String[] getZugarten() {
		return zugarten;
	}

	public void setZugarten(String[] zugarten) {
		this.zugarten = zugarten;
	}
	
 	public String getZugartenAlsString(char trenner) {
 		if (zugarten == null || zugarten.length == 0) {
            return "";
        }
		String ergebnis = "";
		int i = 0;
		for(i = 0; i < this.getZugarten().length - 1; i++) {
			ergebnis = ergebnis + this.getZugarten()[i] + trenner; 
		}
		return ergebnis	+ this.getZugarten()[i];
	}
	
 	//  Start: Überarbeitete Methoden 
 	
 	/**
 	 * Erstellt einen String mit Zeilenumbrüchen für die Anzeige im Textfeld.
 	 * Der übergebene Trenner wird ignoriert.
 	 */
	public String gibBahnhofZurueck(char trenner){
  		return "Name: " + this.getName() + "\n" 
  			+ "Ort: " + this.getOrt() + "\n"
  			+ "Anzahl Gleise: " + this.getAnzahlGleise() + "\n"
  		    + "Letzte Renov.: " + this.getLetzteRenovierung() + "\n"
  		    + "Zugarten: " + this.getZugartenAlsString(' '); // Zugarten mit Leerzeichen trennen
  	}
	
	/**
 	 * Erstellt einen String in einer Zeile für den CSV-Export.
 	 * (Passend zum CSV-Importformat)
 	 */
	public String gibBahnhofZurueckFuerCsv(){
		// Verwendet ';' als Feldtrenner und '_' als Trenner für Zugarten
  		return this.getName() + ";" 
  			+ this.getOrt() + ";"
  			+ this.getAnzahlGleise() + ";"
  		    + this.getLetzteRenovierung() + ";"
  		    + this.getZugartenAlsString('_');
  	}
	// Ende: Überarbeitung
}
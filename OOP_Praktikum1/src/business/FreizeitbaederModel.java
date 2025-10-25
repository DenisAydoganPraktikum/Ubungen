package business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FreizeitbaederModel 
{
	//private FreizeitbaederControl control;
	private Freizeitbad freizeitbad;
	
	public FreizeitbaederModel()
	{
		//this.control = control;
	}
	
	public String getUeberschrift()
	{
		 return "Verwaltung von Freizeitbädern";
	} 
	
	public void schreibeFreizeitbaederInCsvDatei() throws IOException
	// Werfen einer IOException
	{
		BufferedWriter aus = new BufferedWriter(new FileWriter("Freizeitbaeder.csv", true));
		aus.write(this.getFreizeitbad().gibFreizeitbadZurueck(';'));
		aus.close();
	}
	
	public Freizeitbad getFreizeitbad()
	{
		return freizeitbad;
	}
	
	public void setFreizeitbad(Freizeitbad freizeitbad)
	{
		this.freizeitbad = freizeitbad;
	}
}
 

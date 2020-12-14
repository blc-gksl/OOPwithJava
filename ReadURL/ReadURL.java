package ReadURL;
/*
import java.net.*;
import java.io.*;
*/
import edu.duke.*;
public class ReadURL {
    public static void main(String[] args) throws Exception {
        URLResource url = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        /*
        BufferedReader read = new BufferedReader(
        new InputStreamReader(url.openStream()));
        String i;
        while ((i = read.readLine()) != null)
            System.out.println(i);
        read.close();
        */
        for ( String word : url.words() ) {
		    
		    String wLow = word.toLowerCase();
		    
			if ( wLow.contains( "youtube.com" )) {
			    
			    int startQuote = wLow.indexOf("\"");
			    int endQuote = wLow.lastIndexOf("\"");
			    String ytLink = word.substring( startQuote+1, endQuote );
			    System.out.println( ytLink );
       
            }
			
		}
    }
}
package TagFinder;
/**
 * Finds a protein within a strand of DNA represented as a string of c,g,t,a letters.
 * A protein is a part of the DNA strand marked by start and stop codons (DNA triples)
 * that is a multiple of 3 letters long.
 *
 * @author Duke Software Team 
 */

import edu.duke.*;
import java.io.*;
//import java.lang.reflect.Array;

public class TagFinder {
	public class Part1 {
		public String findSimpleGene(final String dna){
		final int startIndex = dna.indexOf("ATG");
		if (startIndex == -1) {
			return "No ATG here";
		}
		final int stopIndex = dna.indexOf("TAA", startIndex + 3);
		if (stopIndex == -1) {
			return "No TAA here";
		}
		if ((stopIndex - startIndex) % 3 == 0) {
			return dna.substring(startIndex, stopIndex  + 3);
		} else {
			return "No logical gene here.";
		}
	}
		public void testSimpleGene() {
			String[] DNA = new String[5];
			DNA[0] = "ATTSGTAAGTS";
			DNA[1] = "GGATGSGTSAGTS";
			DNA[2] = "ATTSGTASGTS";
			DNA[3] = "ATGSGGTAAGTS";
			DNA[4] = "ATGSGTAAGTS";
			for(int i = 1; i < 6; i++){
				System.out.println("The DNA" + i + " is " + DNA[i-1]);
				System.out.println("The gene if there is: " + findSimpleGene(DNA[i-1]));
			}
		}
	}
	
	public class Part2 {
		public String findSimpleGene(final String dna){
		final int startIndex = dna.toUpperCase().indexOf("ATG");
		if (startIndex == -1) {
			return "No ATG here";
		}
		final int stopIndex = dna.toUpperCase().indexOf("TAA", startIndex + 3);
		if (stopIndex == -1) {
			return "No TAA here";
		}
		if ((stopIndex - startIndex) % 3 == 0) {
			return dna.substring(startIndex, stopIndex  + 3);
		} else {
			return "No logical gene here.";
		}
	}
		public void testSimpleGene() {
			String[] DNA = new String[6];
			DNA[0] = "ATTSGTAAGTS";
			DNA[1] = "GGATGSGTSAGTS";
			DNA[2] = "ATTSGTASGTS";
			DNA[3] = "ATGSGGTAAGTS";
			DNA[4] = "ATGSGTAAGTS";
			DNA[5] = "attatggtatsttaasgt";
			for(int i = 1; i < 7; i++){
				System.out.println("The DNA" + i + " is " + DNA[i-1]);
				System.out.println("The gene if there is: " + findSimpleGene(DNA[i-1]));
			}
		}
	}
	public class Part3{
		public String  twoOccurrences(String stringa, String stringb){
			int a,b,lengtha;
			lengtha = stringa.length();
			a = stringb.indexOf(stringa);
			b = stringb.indexOf(stringa,a+lengtha);
			if(b==-1){
				//System.out.println("false");
				return "false";
			}
			else{
				//System.out.println("true");
				return "true";
			}
		}
		public void testingZero(){
			String testString1 = "aba";
			String testString2 = "hbababafg";
			String testString3 = "dfgabadccdfabadcvdf";
			String testString4 = "ababababab";
			String testString5 = "advybavr";
			System.out.println(twoOccurrences(testString1, testString2));
			System.out.println(twoOccurrences(testString1, testString3));
			System.out.println(twoOccurrences(testString1, testString4));
			System.out.println(twoOccurrences(testString1, testString5));
		}
		public String lastPart(String stringa, String stringb){
			int lengtha = stringa.length();
			int index = stringb.indexOf(stringa);
			if(index == -1){
				//System.out.println(stringb);
				return stringb;
			}
			else{
				//System.out.println(stringb.substring(index+lengtha));
				return stringb.substring(index+lengtha);
			}
		}
		public void testing(){
			String testString1 = "whas";
			String testString2 = "dfwhascerfc";
			String testString3 = "whacerfcs";
			String testString4 = "whcerfceaswhasd";
			String testString5 = "whasllllll";
			String testString6 = "an";
			String testString7 = "forest";
			System.out.print("The part of the string after " + testString1 + " in " + testString2 + " is: ");
			System.out.println(lastPart(testString1,testString2));
			System.out.print("The part of the string after " + testString1 + " in " + testString3 + " is: ");
			System.out.println(lastPart(testString1,testString3));
			System.out.print("The part of the string after " + testString1 + " in " + testString4 + " is: ");
			System.out.println(lastPart(testString1,testString4));
			System.out.print("The part of the string after " + testString1 + " in " + testString5 + " is: ");
			System.out.println(lastPart(testString1,testString5));
			System.out.print("The part of the string after " + testString6 + " in " + testString7 + " is: ");
			System.out.println(lastPart(testString6,testString7));
		}
	}
	public class Part4{
		public void checkYoutube(){
		URLResource theWEb = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
		for ( String word : theWEb.words() ) {
		    
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

	public void testing() {
		final String a = "cccatggggtttaaataataataggagagagagagagagttt";
		final String ap = "atggggtttaaataataatag";
		// String a = "atgcctag";
		// String ap = "";
		// String a = "ATGCCCTAG";
		// String ap = "ATGCCCTAG";
		Part1 x = new Part1();
		final String result = x.findSimpleGene(a);
		if (ap.equals(result)) {
			System.out.println("success for " + ap + " length " + ap.length());
		} else {
			System.out.println("mistake for input: " + a);
			System.out.println("got: " + result);
			System.out.println("not: " + ap);
		}
	}

	public void realTesting() {
		Part1 x = new Part1();
		final DirectoryResource dr = new DirectoryResource();
		for (final File f : dr.selectedFiles()) {
			final FileResource fr = new FileResource(f);
			final String s = fr.asString();
			System.out.println("read " + s.length() + " characters");
			final String result = x.findSimpleGene(s);
			System.out.println("found " + result);
		}
	}
	public static void main(String[] args) {
		TagFinder tg = new TagFinder();
		TagFinder.Part4 testCase = tg.new Part4();
		testCase.checkYoutube();		
	}
}

package StartLearnCSV;

import edu.duke.*;
import org.apache.commons.csv.*;
public class Main {

    public void readFood(CSVParser parser1){
        /*
        FileResource fr1 = new FileResource();
        CSVParser parser1 = fr1.getCSVParser();
        */
        for(CSVRecord record1 : parser1){
            System.out.println(record1.get("Name"));

        }
    }
    public void listExporters(CSVParser parser2, String exportOfInterest2){
        String exports2 = "";
        for(CSVRecord record2 : parser2){
            exports2 = record2.get("Exports");
            if(exports2.contains(exportOfInterest2)){
                System.out.println(record2.get("Country"));
            }
        }
    }
    public String countyInfo(CSVParser parser3, String country3){
        String myCountry = "NOT FOUND";
        for(CSVRecord record3 : parser3){
            myCountry = record3.get("Country");
            if(myCountry.equals(country3)){
                return (myCountry + ": " + record3.get("Exports") + ": " + record3.get("Value (dollars)") + "\n");
            }
        }
        return myCountry;
    }
    public void listExportersTwoProducts(CSVParser parser4, String exportOfInterest41, String exportOfInterest42){
        String exports4 = "";
        for(CSVRecord record4 : parser4){
            exports4 = record4.get("Exports");
            if(exports4.contains(exportOfInterest41) && exports4.contains(exportOfInterest42)){
                System.out.println(record4.get("Country"));
            }
        }
    }
    public void numberOfExporters(CSVParser parser5, String exportItem5){
        String theExport = "";
        int countExportItem5 = 0;
        for(CSVRecord record5 : parser5){
            theExport = record5.get("Exports");
            if(theExport.contains(exportItem5)){
                countExportItem5++;
            }
        }
        System.out.println(countExportItem5 + "\n");
    }
    public void bigExporters(CSVParser parser6, String money6){
        long theMoney = Long.parseLong(money6.substring(1).replaceAll(",", ""));
        //System.out.println(theMoney);
        long otherMoney = 0;
        for(CSVRecord record6 : parser6){
            otherMoney = Long.parseLong(record6.get("Value (dollars)").substring(1).replaceAll(",", ""));
            if(otherMoney >= theMoney){
                System.out.println(record6.get("Country") + "  " + record6.get("Value (dollars)"));
            }
        }
    }
        
    public static void main(String[] args) {
        Main mine1 = new Main();
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        /*
        //mine1.readFood(parser);
        String exportOfInterest = "coffee";
        mine1.listExporters(parser, exportOfInterest);
        */
        /*
        String country = "Turkey";
        System.out.println(mine1.countyInfo(parser, country));
        */
        /*
        String exportOfInterest41 = "cotton";
        String exportOfInterest42 = "flowers";
        mine1.listExportersTwoProducts(parser, exportOfInterest41, exportOfInterest42);
        */
        /*
        String exportItem = "cocoa";
        mine1.numberOfExporters(parser, exportItem);
        */
        
        String money6 = "$999,999,999,999";
        mine1.bigExporters(parser, money6);
        

    }
}
package TemperatureHumidity;

import edu.duke.*;

import java.io.File;

import org.apache.commons.csv.*;

public class Main {

    public CSVRecord largestSoFar0(CSVRecord largestSoFar, CSVRecord currentRow, String desiredParameter0){
        if(largestSoFar == null){
            largestSoFar = currentRow;
        }
        else{
            double largestC = Double.parseDouble(currentRow.get(desiredParameter0));
            double largestS = Double.parseDouble(largestSoFar.get(desiredParameter0));
            if (largestC > largestS){
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }

    public CSVRecord smallestSoFar0(CSVRecord smallestSoFar, CSVRecord currentRow, String desiredParameter){
        if(smallestSoFar == null){
            smallestSoFar = currentRow;
        }
        else{
            double sizeC = Double.parseDouble(currentRow.get(desiredParameter));
            double sizeK = Double.parseDouble(smallestSoFar.get(desiredParameter));
            if (sizeC < sizeK){
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }

    public CSVRecord hottestHourInDayLine2(CSVParser parser1){
        CSVRecord largestSoFar1 = null;
        for(CSVRecord currentRow1 : parser1){
            largestSoFar1 = largestSoFar0(largestSoFar1, currentRow1, "TemperatureF");
        }
        return largestSoFar1;
    }

    public void testHotestInDay3(FileResource fr2){
        CSVRecord record2 = hottestHourInDayLine2(fr2.getCSVParser());
        System.out.println("Hottest temperature was " + Double.parseDouble(record2.get("TemperatureF")) +
                            " at " + record2.get("TimeEST") + "\n");
    }

    public CSVRecord hottestInManyDays4(DirectoryResource dr3){
        CSVRecord largestTempSofar3 = null;
        CSVRecord currentTemp = null;      
        for(File myFile3 : dr3.selectedFiles()){
            FileResource fr3 = new FileResource(myFile3);
            CSVParser parser3 = fr3.getCSVParser();
            currentTemp = hottestHourInDayLine2(parser3);
            largestTempSofar3 = largestSoFar0(largestTempSofar3, currentTemp, "TemperatureF");
        }
        
        return largestTempSofar3;
    }

    public void testhottestInManyDays5(DirectoryResource dr4){
        CSVRecord record4 = hottestInManyDays4(dr4);
        System.out.println("Hottest temperature was " + Double.parseDouble(record4.get("TemperatureF")) +
                            " at " + record4.get("TimeEST") + "\n");
    }

    public CSVRecord coldestHourInFile6(CSVParser parser6){
        CSVRecord coldestSoFar = null;
        for(CSVRecord currentRow1 : parser6){
            coldestSoFar = smallestSoFar0(coldestSoFar, currentRow1, "TemperatureF");
        }
        return coldestSoFar;
    }

    public void testColdestHourInFile7(FileResource fr7){
        CSVRecord record7 = coldestHourInFile6(fr7.getCSVParser());
        System.out.println("The temperature at coldest time was " + Double.parseDouble(record7.get("TemperatureF")) +
                            " at " + record7.get("DateUTC") + "\n");

    }

    public File fileWithColdestTemperature8(DirectoryResource dr8){
        File fileName = null;
        CSVRecord smallestTempSoFar8 = null;
        CSVRecord currentTemp8 = null; 
        Double tempTemperature8 = 10000.0D;     
        for(File myFile8 : dr8.selectedFiles()){
            FileResource fr8 = new FileResource(myFile8);
            CSVParser parser8 = fr8.getCSVParser();
            currentTemp8 = coldestHourInFile6(parser8);
            smallestTempSoFar8 = smallestSoFar0(smallestTempSoFar8, currentTemp8, "TemperatureF");
            double smallTemp8 = Double.parseDouble(smallestTempSoFar8.get("TemperatureF"));
            if(smallTemp8 < tempTemperature8){
                fileName = myFile8;
                tempTemperature8 = smallTemp8;
            }
        }
        return fileName;
    }

    public void testFileWithColdestTemperature(DirectoryResource dr9){
        File file9 = fileWithColdestTemperature8(dr9);
        String coldestDayFileName = file9.getName();
        System.out.println("Coldest day was in file " + coldestDayFileName);
        String coldestDayFileNamePath = file9.getAbsolutePath();
        FileResource fr9 = new FileResource(coldestDayFileNamePath);
        CSVRecord coldestHour = coldestHourInFile6(fr9.getCSVParser());
        System.out.println("Coldest temperature on that day was " + Double.parseDouble(coldestHour.get("TemperatureF")));
        System.out.println("All the Temperatures on the coldest day were:");
        for(CSVRecord record99 : fr9.getCSVParser()){
            System.out.println(record99.get("DateUTC") + " " + record99.get("TemperatureF"));
        }

    }

    public CSVRecord lowestHumidityInFile(CSVParser parser10){
        CSVRecord lowestHumidity = null;
        for(CSVRecord currentRow10 : parser10){
            if(currentRow10.get("Humidity").equals("N/A")){
                continue;
            }
            else{
                lowestHumidity = smallestSoFar0(lowestHumidity, currentRow10, "Humidity");
            }
        }
        return lowestHumidity;
    }
    
    public void testlowestHumidityInFile(FileResource fr11){
        CSVParser parser = fr11.getCSVParser();
        CSVRecord myHumidity11 = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + Double.parseDouble(myHumidity11.get("Humidity")) + " at " + myHumidity11.get("DateUTC"));
    }

    public CSVRecord lowestHumidityInManyFiles(DirectoryResource dr12){
        CSVRecord lowestHumidityInManyFiles = null;
        CSVRecord currentHumidity = null;
        for(File myFile12 : dr12.selectedFiles()){
            FileResource fr12 = new FileResource(myFile12);
            CSVParser parser12 = fr12.getCSVParser();
            currentHumidity = lowestHumidityInFile(parser12);
            lowestHumidityInManyFiles = smallestSoFar0(lowestHumidityInManyFiles, currentHumidity, "Humidity");
        }
        return lowestHumidityInManyFiles;
    }

    public void testlowestHumidityInManyFiles(DirectoryResource dr13){
        CSVRecord myHumidity13 = lowestHumidityInManyFiles(dr13);
        System.out.println("Lowest Humidity was " + Double.parseDouble(myHumidity13.get("Humidity")) + " at " + myHumidity13.get("DateUTC"));
    }

    public double averageTemperatureInFile(CSVParser parser14){
        double averageTemp = 0;
        int calculatedTimesNumber = 0;
        for(CSVRecord record14 : parser14){
            averageTemp += Double.parseDouble(record14.get("TemperatureF"));
            calculatedTimesNumber++;
        }
        averageTemp = averageTemp / calculatedTimesNumber;
        return averageTemp;
    }

    public void testAverageTemperatureInFile(FileResource fr15){
        System.out.println("Average temperature in file is " + averageTemperatureInFile(fr15.getCSVParser()));
    }

    public double averageTemperatureWithHighHumidityInFile(FileResource fr16, int value){
        CSVParser parser16 = fr16.getCSVParser();
        double specialAverage16 = 0;
        int calculatedTimesNumber16 = 0;
        for(CSVRecord record16 : parser16){
            if(record16.get("Humidity").equals("N/A")){
                continue;
            }
            else{
            double indicator16 = Double.parseDouble(record16.get("Humidity"));
                if(indicator16 >= (double)value){
                    specialAverage16 += Double.parseDouble(record16.get("TemperatureF"));
                    calculatedTimesNumber16++;
                }
            }
        }
        if(calculatedTimesNumber16 == 0){
            return -333;
        }
        specialAverage16 = specialAverage16 / calculatedTimesNumber16;
        return specialAverage16;
    }

    public void testAverageTemperatureWithHighHumidityInFile(FileResource fr17, int value17){
        double indicator17 = averageTemperatureWithHighHumidityInFile(fr17, value17);
        if(indicator17 == -333){
            System.out.println("No temperatures with that humidity");
        }
        else{
            System.out.println("Average Temp when high Humidity is " + indicator17);
        }

    }
    public static void main(String[] args) {
        Main mine = new Main();
        DirectoryResource dr = new DirectoryResource();
        //FileResource fr = new FileResource();
        //FileResource fr = new FileResource("nc_weather/2015/weather-2015-01-01.csv");
        //mine.testHotestInDay3(fr);
        //mine.testhottestInManyDays5(dr);
        //mine.testColdestHourInFile7(fr);
        mine.testFileWithColdestTemperature(dr);
        //mine.testlowestHumidityInFile(fr);
        //mine.testlowestHumidityInManyFiles(dr);
        //mine.testAverageTemperatureInFile(fr);
        //mine.testAverageTemperatureWithHighHumidityInFile(fr, 80);      
    }
}
package BabyNames;

import org.apache.commons.csv.CSVRecord;

import edu.duke.*;

import java.io.File;


public class Main {

    public void totalBirths(FileResource fr1){
        int totalYoungs = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        for(CSVRecord record1 : fr1.getCSVParser(false)){
            if(record1.get(1).equals("F")){
                totalGirls++;
            }
            else{
                totalBoys++;
            }
            totalYoungs++;
        }
        System.out.println("There are in total " + totalYoungs+ " people in thet file: "
                                                 + totalGirls + " girls and "
                                                 + totalBoys + " boys.");
    }

    public int getRankDirectory(DirectoryResource dr2, int year2, String name2, String gender2){
        int rank2 = -1;
        int count = 0;
        String yearInString2 = Integer.toString(year2);
        for(File myFile2 : dr2.selectedFiles()){
            if(myFile2.getName().contains(yearInString2)){
                FileResource fr2 = new FileResource(myFile2);
                for(CSVRecord record2 : fr2.getCSVParser(false)){
                    if(record2.get(1).equals(gender2)){
                        count++;
                        if(record2.get(0).equals(name2)){
                            rank2 = count;
                            break;
                        }
                    }
                }
                break;
            }
        }
        return rank2;
    }

    public int getRankFile(FileResource fr22, String name22, String gender22){
        int rank22 = -1;
        int count22 = 0;
        for(CSVRecord record22 : fr22.getCSVParser(false)){
            if(record22.get(1).equals(gender22)){
                count22++;
                if(record22.get(0).equals(name22)){
                    rank22 = count22;
                    break;
                }
            }
        }
        return rank22;
    }

    public String getName(DirectoryResource dr3, int year3, int rank3, String gender3){
        String name3 = null;
        int count = 0;
        String yearInString3 = Integer.toString(year3);
        for(File myFile3 : dr3.selectedFiles()){
            if(myFile3.getName().contains(yearInString3)){
                FileResource fr2 = new FileResource(myFile3);
                for(CSVRecord record2 : fr2.getCSVParser(false)){
                    if(record2.get(1).equals(gender3)){
                        count++;
                        if(rank3 == count){
                            name3 = record2.get(0);
                            break;
                        }
                    }
                }
                break;
            }
        }
        return name3;
    }

    public void whatIsNameInYear(DirectoryResource dr4, String name4, int year4, int newYear4, String gender4){
        int rankName4 = getRankDirectory(dr4, year4, name4, gender4);
        if(rankName4 == -1){
            System.out.println("The name that is given as input does not exist, which is: " + name4);
        }
        else{
            String newName = getName(dr4, newYear4, rankName4, gender4);
            if(newName == null){
                System.out.println("There is no girl in that rank.");
            }
            else{
                System.out.println(name4 + " born in " + year4 + " would be " + newName + " if he/she was born in " + newYear4 + ".");
            }
        }
    }

    public int yearOfHighestRank(DirectoryResource dr5, String name5, String gender5){
        int highestRank = 0;
        int currentRank = 0;
        int prevCurrentRank = 0;
        for(File myFile5 : dr5.selectedFiles()){
            FileResource fr5 = new FileResource(myFile5);
            currentRank = getRankFile(fr5, name5, gender5);
            if(currentRank != -1 && (currentRank < prevCurrentRank || prevCurrentRank == 0)){
                highestRank = Integer.parseInt(myFile5.getName().replaceAll("\\D",""));
                prevCurrentRank = currentRank;
            }
        }
        return highestRank;
    }

    public double getAverageRank(DirectoryResource dr6, String name6, String gender6){
        double averageRank = 0;
        double eachFileRank = 0;
        int fileCount = 0;
        for(File myFile6 : dr6.selectedFiles()){
            FileResource fr6 = new FileResource(myFile6);
            eachFileRank = getRankFile(fr6, name6, gender6);
            if(eachFileRank == -1){
                averageRank = -1;
                break;
            }
            else{
                averageRank += eachFileRank;
            }
            fileCount++;
        }
        if(averageRank != -1){
            averageRank = averageRank / fileCount;
        }
        return averageRank;
    }

    public int getTotalBirthsRankedHigher(DirectoryResource dr7, int year7, String name7, String gender7){
        int totalBabyBeforeName = 0;
        String yearInString = Integer.toString(year7);
        for(File myFile7 : dr7.selectedFiles()){
            if(myFile7.getName().contains(yearInString)){
                FileResource fr7 = new FileResource(myFile7);
                for(CSVRecord record7 : fr7.getCSVParser(false)){
                    if(record7.get(1).equals(gender7)){
                        if(record7.get(0).equals(name7)){
                            break;
                        }
                        else{
                            totalBabyBeforeName += Integer.parseInt(record7.get(2));
                        }
                    }
                }
                break;
            }
        }
        return totalBabyBeforeName;
    }
    public static void main(String[] args) {
        Main mine = new Main();
        DirectoryResource dr = new DirectoryResource();
        //FileResource fr = new FileResource();
        // totalBirths
        //mine.totalBirths(fr);
        // getRank
        // int theRank = mine.getRankDirectory(dr, 1971, "Frank", "M");
        // System.out.println("The name Frank has rank " + theRank + " in 1971.");
        // getName
        // String theName = mine.getName(dr, 1982, 450, "M");
        // System.out.println("The 450th popular male name in 1982 was " + theName + ".");
        // whatIsNameInYear
        // mine.whatIsNameInYear(dr, "Owen", 1974, 2014, "M");
        // yearOfHighestRank
        // int theYear = mine.yearOfHighestRank(dr, "Mich",  "M");
        // System.out.println("The name Mich is the most popular name in the year: " + theYear);
        // getAverageRank
        // double theAverage  =mine.getAverageRank(dr, "Robert", "M");
        // System.out.println("The average popularity of Robert is: " + theAverage);
        // getTotalBirthsRankedHigher
        int theTotalBabyBeforeTheName = mine.getTotalBirthsRankedHigher(dr, 1990, "Emily", "F");
        System.out.println("The total number of babies populer than Emily based on their name is: " + theTotalBabyBeforeTheName);

    }
}
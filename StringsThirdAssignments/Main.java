package StringsThirdAssignments;

import edu.duke.FileResource;
import edu.duke.StorageResource;
/*
import edu.duke.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
*/

public class Main {

    public class Part1{
        public int findStopCodon(final String dna, final int startIndex, final String stopCodon){
            int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
            int currentIndex = startIndex;
            while(true){
                if(stopIndex != -1 && (stopIndex - startIndex) % 3 == 0){
                        return stopIndex;
                 }
                else if(stopIndex != -1 && (stopIndex - startIndex) % 3 != 0){
                    currentIndex = stopIndex;
                    stopIndex = dna.indexOf(stopCodon, currentIndex + 1);
                }
                else{ break;}
            }
            return -1;
        }
        public void testFindStopCodon(){
            System.out.println(findStopCodon("ATGGTAGTTAGTTAAGTASS", 0, "TAA"));
            System.out.println(findStopCodon("ATGGTAGTTAGTTAATAGSS", 0, "TAG"));
            System.out.println(findStopCodon("ATGGTATGATTAAGTAAGSS", 0, "TGA"));
            System.out.println(findStopCodon("ATGGTAGTTAGTTAAGTASS", 0, "TGA"));
        }
        //---> a or b could be 0 maybe
        public int findBestFitting(final int a, final int b, int c){
            int minIndex = 0;
            int temp = 0;
                if((a != -1 && a<b) || (a != -1 && b == -1)){
                    temp = a;
                }
                else{
                    temp = b;
                }
                if((temp != -1 && temp < c) || (temp != -1 && c == -1)){
                    minIndex = temp;
                }
                else{
                    minIndex = c;
                }
            return minIndex;
        }
        public String findGene(final String dna){
            final int startIndexATG = dna.indexOf("ATG");
                if(startIndexATG == -1){
                    return "";
                }
            final int indexTAA = findStopCodon(dna, startIndexATG, "TAA");
            final int indexTAG = findStopCodon(dna, startIndexATG, "TAG");
            final int indexTGA = findStopCodon(dna, startIndexATG, "TGA");
            int minIndex = findBestFitting(indexTAA, indexTAG, indexTGA);
            if(minIndex != -1){
                return dna.substring(startIndexATG, minIndex+3);
            }            
            return "";
        }
        public void testFindGene(){
            System.out.println(findGene("ASGGTAGTTAGTTAAGTASS"));
            System.out.println(findGene("ATGGTAGTTAGTTAAGTASS"));
            System.out.println(findGene("ATGGTAGTTAGTTAGTAASS"));
            System.out.println(findGene("ATGGTAGTTAGTTSAGTASS"));
        }
        public void printAllGenes(final String dna){
            int startIndex = -3;
            int minIndex = -3;
            int indexTAA, indexTAG, indexTGA;
            int i = 1;
            int j = 0;
            while(true){
                startIndex = dna.indexOf("ATG",minIndex + 3);
                //System.out.println(startIndex);
                if (startIndex == -1){
                    System.out.println(i + ". independent ATG" + " doesn't exist.");
                    break;
                }
                else{
                    indexTAA = findStopCodon(dna, startIndex, "TAA");
                    indexTAG = findStopCodon(dna, startIndex, "TAG");
                    indexTGA = findStopCodon(dna, startIndex, "TGA");
                    minIndex = findBestFitting(indexTAA, indexTAG, indexTGA);
                    if(minIndex == -1){
                        System.out.print(startIndex + "   ");
                        System.out.println(i + ". independent ATG" + " has no stopping codon.");
                        minIndex = startIndex;
                    }
                    else{
                        j++;
                        System.out.print(startIndex + "   ");
                        System.out.println(i + ". independent ATG" + " has " + j + "th gene"+ ": " + dna.substring(startIndex, minIndex+3));
                    }
                    i++;
                }
            }
        }
        public StorageResource getAllgenes(final String dna){
            StorageResource geneList = new StorageResource();
            int startIndex = -3;
            int minIndex = -3;
            int indexTAA, indexTAG, indexTGA;
            while(true){
                startIndex = dna.indexOf("ATG",minIndex + 3);
                if (startIndex == -1){
                    break;
                }
                else{
                    indexTAA = findStopCodon(dna, startIndex, "TAA");
                    indexTAG = findStopCodon(dna, startIndex, "TAG");
                    indexTGA = findStopCodon(dna, startIndex, "TGA");
                    minIndex = findBestFitting(indexTAA, indexTAG, indexTGA);
                    if(minIndex == -1){
                        minIndex = startIndex;
                    }
                    else{
                        geneList.add(dna.substring(startIndex, minIndex+3));
                    }
                }
            }
            return geneList;
        }
        public void testGetAllGenes(final String dna){
            StorageResource genesData = new StorageResource();
            genesData = getAllgenes(dna);
            for(final String genes : genesData.data()){
                System.out.println(genes);
            }
        }
        public float cgRatio(final String dna){
            int cgNumber = 0;
            float ratio = 0;
            cgNumber = 2*dna.length() - dna.replaceAll("G","").length() - dna.replaceAll("C","").length();
            ratio = (float)cgNumber / dna.length();
            return ratio;
        }
        public int countCTG(final String dna){
            int count = 0;
            int myIndex = dna.indexOf("CTG");
            while(myIndex != -1){
                count++;
                myIndex = dna.indexOf("CTG", myIndex+3);
            }
            return count;
        }
        public void processGenes(final StorageResource sr){
            int geneNumber = 0;
            int countBiggerNine = 0;
            int countCGRatio = 0;
            String temp = "";
            for(String gene : sr.data()){
                if(gene.length() > 60){
                    //System.out.print(gene.length() + "   ");
                    //System.out.println(gene);
                    countBiggerNine++;
                }
                if(cgRatio(gene) > 0.35){
                    //System.out.print(cgRatio(gene) + "   ");
                    //System.out.println(gene);
                    countCGRatio++;
                }
                if(gene.length() >= temp.length()){
                    temp = gene;
                }
                geneNumber++;
            }
            System.out.println("Number of genes longer than 60 characters: " + countBiggerNine + "\n");
            System.out.println("Total gene number: " + geneNumber + "\n");
            System.out.println("Number of genes has larger C-G ratio than 0.35: " + countCGRatio + "\n");
            System.out.println("Longest gene is: " + temp.length() + " " + temp);
        }
    }

    public static void main(final String[] args) {
        //String DNA1 = "CGATGCCCTGAAGGTAGTATGGTATGAATTGTCTGCTATGTCTGTCTAGTCCCATGCTAACCTGAGGGATGCCATGCCCTAAATGCCCATGTGACATGA";
        //final String DNA2 = "CTGCTGATGGGGCCCTTTATGATTCAGGATATCTCTCAAAAAATAAACTTGATTCTGGTATTGAGCCAGTATTGAAGAATGTTGAAGATCAAAAAAACACTAGTTTTTCCAAAGTAA";
        Main test1 = new Main();
        Main.Part1 pr1 = test1.new Part1();
        //System.out.println(pr1.countCTG(DNA1));
        //System.out.println(pr1.countCTG(DNA2));
        
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase();
        StorageResource example1 = pr1.getAllgenes(dna);
        pr1.processGenes(example1);
        System.out.println("\n" + "CTG number: " + pr1.countCTG(dna) + "\n\n");
        /*
        StorageResource example2 = pr1.getAllgenes(DNA1);
        System.out.println("\n" + "CTG number: " + pr1.countCTG(dna) + "\n\n");
        pr1.processGenes(example2);
        pr1.printAllGenes(DNA2);
        //pr1.printAllGenes(dna);
        */
    }
}
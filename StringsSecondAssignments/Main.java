package StringsSecondAssignments;

public class Main {
    public class Part1{
        public int findStopCodon(String dna, int startIndex, String stopCodon){
            int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
            int currentIndex = startIndex;
            while(true){
                if(stopIndex != -1 &&
                    (stopIndex - startIndex) % 3 == 0){
                        return stopIndex;
                 }
                else if(stopIndex != -1 &&
                (stopIndex - currentIndex) % 3 != 0){
                    currentIndex = stopIndex;
                    stopIndex = dna.indexOf(stopCodon, currentIndex + 3);
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
        public int findBestFitting(int a, int b){
            int minIndex = 0;
                if(a != -1 && b != -1){
                    minIndex = Math.min(a, b);
                }
                else if(a == -1 || b == -1){
                    minIndex = Math.max(a, b);
                }
                else{
                    minIndex = -1;
                }
            return minIndex;
        }
        public String findGene(String dna){
            int startIndexATG = dna.indexOf("ATG");
                if(startIndexATG == -1){
                    return "";
                }
            int indexTAA = findStopCodon(dna, startIndexATG, "TAA");
            int indexTAG = findStopCodon(dna, startIndexATG, "TAG");
            int indexTGA = findStopCodon(dna, startIndexATG, "TGA");
            int minIndex = findBestFitting(indexTAA, indexTAG);
            minIndex = findBestFitting(indexTGA, minIndex);
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
        public void printAllGenes(String dna){
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
                    minIndex = findBestFitting(indexTAA, indexTAG);
                    minIndex = findBestFitting(indexTGA, minIndex);
                    if(minIndex == -1){
                        System.out.println(i + ". independent ATG" + " has no stopping codon.");
                        minIndex = startIndex;
                    }
                    else{
                        j++;
                        System.out.println(i + ". independent ATG" + " has " + j + "th gene"+ ": " + dna.substring(startIndex, minIndex+3));
                    }
                    i++;
                }
            }
        }
    }

    public class Part2{
        public int howMany(String stringA, String stringB){
            int count = 0;
            int lengthStringA = stringA.length();
            int index = stringB.indexOf(stringA);
            while(index != -1){
                count++;
                index = stringB.indexOf(stringA,index + lengthStringA);
            }
            return count;
        }
        public void testHowMany(){
            String string1 = "BAC";
            String string2 = "AAA";
            String string3 = "AAAAAAAAAAA";
            String string4 = "BADLHKJNBAC";
            String string5 = "BBBACLKŞIHPŞBAC SSJBAC";
            String string6 = "BASDFWCRSGVDH";
            System.out.println(howMany(string2, string3));
            System.out.println(howMany(string1, string3));
            System.out.println(howMany(string1, string4));
            System.out.println(howMany(string1, string5));
            System.out.println(howMany(string1, string6));
        }
    }
    public class Part3{

        public int findStopCodon(String dna, int startIndex, String stopCodon){
            int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
            int currentIndex = startIndex;
            while(true){
                if(stopIndex != -1 &&
                    (stopIndex - startIndex) % 3 == 0){
                        return stopIndex;
                 }
                else if(stopIndex != -1 &&
                (stopIndex - currentIndex) % 3 != 0){
                    currentIndex = stopIndex;
                    stopIndex = dna.indexOf(stopCodon, currentIndex + 3);
                }
                else{ break;}
            }
            return -1;
        }
        public int findBestFitting(int a, int b){
            int minIndex = 0;
                if(a != -1 && b != -1){
                    minIndex = Math.min(a, b);
                }
                else if(a == -1 || b == -1){
                    minIndex = Math.max(a, b);
                }
                else{
                    minIndex = -1;
                }
            return minIndex;
        }
        public int countAllGenes(String dna){
            int startIndex = -3;
            int minIndex = -3;
            int indexTAA, indexTAG, indexTGA;
            int j = 0;
            while(true){
                startIndex = dna.indexOf("ATG",minIndex + 3);
                //System.out.println(startIndex);
                if (startIndex == -1){
                    //System.out.println(i + ". independent ATG" + " doesn't exist.");
                    break;
                }
                else{
                    indexTAA = findStopCodon(dna, startIndex, "TAA");
                    indexTAG = findStopCodon(dna, startIndex, "TAG");
                    indexTGA = findStopCodon(dna, startIndex, "TGA");
                    minIndex = findBestFitting(indexTAA, indexTAG);
                    minIndex = findBestFitting(indexTGA, minIndex);
                    if(minIndex == -1){
                        //System.out.println(i + ". independent ATG" + " has no stopping codon.");
                        minIndex = startIndex;
                    }
                    else{
                        j++;
                        //System.out.println(i + ". independent ATG" + " has " + j + "th gene"+ ": " + dna.substring(startIndex, minIndex+3));
                    }
                }
            }
            return j;
        }
    }

    
    public static void main(String[] args) {
        String DNA1 = "AGGTAGTATGGTATGAATTGTSTGSTATGTSTGTSTAGTSSSATGSTAASSTGAGGGATGSSATGSSSTAA";
        //String DNA2 = "AGTSSGATGS";
        Main mn = new Main();
        Main.Part1 pr1 = mn.new Part1();
        Main.Part3 pr3 = mn.new Part3();
        pr1.printAllGenes(DNA1);
        System.out.print("Number of genes in it: ");
        System.out.println(pr3.countAllGenes(DNA1));

    }
}
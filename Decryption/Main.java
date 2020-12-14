package Decryption;

import java.util.*;
//import java.lang.*;
//import edu.duke.*;

public class Main {

    public String encryption1(String data1, int key1){
        StringBuilder encrypted1 = new StringBuilder(data1);
        String alphabet1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String temp = alphabet1.toString().substring(key1);
        String shiftedAlphabet = temp + alphabet1.toString().substring(0, key1);
        //System.out.println(shiftedAlphabet);
        for(int i = 0; i < data1.length(); i++){
            int index1 = alphabet1.indexOf(data1.toUpperCase(Locale.forLanguageTag("en")).charAt(i));
            if(index1 != -1){
                char newChar = '_';
                if(Character.isLowerCase(data1.charAt(i))){
                    newChar = shiftedAlphabet.toLowerCase(Locale.forLanguageTag("en")).charAt(index1);
                }
                else{
                    newChar = shiftedAlphabet.toUpperCase(Locale.forLanguageTag("en")).charAt(index1);
                }
                encrypted1.setCharAt(i, newChar);;
            }
        }
        return encrypted1.toString();
    }

    public String decryption1(String data1, int key1){
        String decriptedString1 = "";
        String alphabet1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i = 0; i < data1.length(); i++){
            int index1 = alphabet1.indexOf(data1.toUpperCase(Locale.forLanguageTag("en")).charAt(i));
            if(index1 != -1){
                if(index1 - key1 > -1){
                    if(Character.isLowerCase(data1.charAt(i))){
                        decriptedString1 += alphabet1.toLowerCase(Locale.forLanguageTag("en")).substring(index1 - key1, index1 - key1 +1);
                    }
                    else{
                        decriptedString1 += alphabet1.toUpperCase(Locale.forLanguageTag("en")).substring(index1 - key1, index1 - key1 +1);
                    } 
                }
                else{
                    if(Character.isLowerCase(data1.charAt(i))){
                        decriptedString1 += alphabet1.toLowerCase(Locale.forLanguageTag("en")).substring(index1 - key1 + 26, index1 - key1 + 27);
                    }
                    else{
                        decriptedString1 += alphabet1.toUpperCase(Locale.forLanguageTag("en")).substring(index1 - key1 + 26, index1 - key1 + 27);
                    }     
                }
            }
            else{
                decriptedString1 += data1.substring(i,i+1);
            }
        }
        return decriptedString1;
    }

    public boolean isVowel(char ch2){
        boolean bl2 = false;
        ch2 = Character.toLowerCase(ch2);
        if(ch2 == 'a' || ch2 == 'e' || ch2 == 'i' || ch2 == 'o' || ch2 == 'u'){
            bl2 = true;
        }
        return bl2;
    }

    public String replaceVowels(String phrase3, char ch3){
        String thePhrase3 = "";
        for(int i=0; i < phrase3.length(); i++){
            if(isVowel(phrase3.charAt(i))){
                thePhrase3 += ch3;
            }
            else{
                thePhrase3 += phrase3.charAt(i);
            }
        }
        return thePhrase3;
    } 
    
    public String emphasize(String phrase4, char ch4){
        String thePhrase4 = "";
        for(int i = 0; i < phrase4.length(); i++){
            if((phrase4.charAt(i) == ch4) && (i % 2 == 0)){
                thePhrase4 += '*';
            }
            else if((phrase4.charAt(i) == ch4) && (i % 2 == 1)){
                thePhrase4 += '+';
            }
            else{
                thePhrase4 += phrase4.charAt(i);
            }
        }
        return thePhrase4;
    }

    public String encryptTwoKeys(String data5, int key15, int key25){
        StringBuilder firstPart = new StringBuilder(encryption1(data5, key15));
        StringBuilder secondPart = new StringBuilder(encryption1(data5, key25));
        String result5  = "";
        for(int i = 0; i < data5.length(); i++){
            if(i%2 == 0){
                result5 += firstPart.charAt(i);
            }
            else{
                result5 += secondPart.charAt(i);
            }
        }
        return result5;
    }
        public static void main(String[] args) {
        Main mine = new Main();
        

        System.out.println(mine.encryption1("FIRST LEGION ATTACKEAST FLANK!", 23));
        System.out.println(mine.encryption1("First Legion.", 23));
        System.out.println(mine.encryption1("First Legion.", 17));
        System.out.println(mine.encryption1("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
        System.out.println(mine.encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));

        System.out.println(mine.decryption1("a bat", 7));
        /*
        FileResource fr = new FileResource();
        System.out.println(mine.encryption1(fr.asString(),17));
        System.out.println(mine.decryption1(fr.asString(),9));
        */
        /*
        System.out.println(mine.isVowel('E'));
        System.out.println(mine.isVowel('b'));
        */
        /*
        System.out.println(mine.replaceVowels("Hello World!", '*'));
        
        System.out.println(mine.emphasize("Mary Bella Abracadabra", 'a'));
        */
        
    }
}
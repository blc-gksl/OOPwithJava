package Get_And_Write_File_Name;

import edu.duke.*;
import java.io.*;

public class Main {

    public void theFunction(){
        DirectoryResource dr1 = new DirectoryResource();
        File newFile = new File("Get_And_Write_File_Name//file_names.txt");
        boolean result;
        try   
        {  
        result = newFile.createNewFile();  //creates a new file  
        if(result)      // test if successfully created a new file  
        {  
        System.out.println("file created "+newFile.getCanonicalPath()); //returns the path string
        String theFileName = ""; 
            for(File myFile1 : dr1.selectedFiles()){
                String tempString = myFile1.getName();
                int tempInt = tempString.indexOf(".");
                theFileName += tempString.substring(0,tempInt)+ "\n";
                // geliştirme lazım .pdf falan çıkmamalı -> done
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
                    writer.write(theFileName);
                    
                    writer.close();
        }  
        else  
        {  
        System.out.println("File already exist at location: "+newFile.getCanonicalPath());  
        }  
        }   
        catch (IOException e)   
        {  
        e.printStackTrace();    //prints exception if any  
        }         
    }
    public static void main(String[] args) {
        Main mine  = new Main();
        mine.theFunction();
    }
}
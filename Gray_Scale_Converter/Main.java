package Gray_Scale_Converter;

import edu.duke.*;
import java.io.*;

public class Main {

    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel pixel1 : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel1.getX(), pixel1.getY());
            int averageColor = (inPixel.getRed()  + inPixel.getBlue() + inPixel.getGreen()) / 3;
            pixel1.setRed(averageColor);
            pixel1.setBlue(averageColor);
            pixel1.setGreen(averageColor);
        }
        return outImage;
    }


    public void selectConvertGrayAndSave(){
        DirectoryResource dr2 = new DirectoryResource();
        for(File myFile2 : dr2.selectedFiles()){
            ImageResource inputImage2 = new ImageResource(myFile2);
            String imageName2 = inputImage2.getFileName();
            String newImageName2 = "copies_of_images/" + "gray-" + imageName2;
            inputImage2 = makeGray(inputImage2);
            inputImage2.setFileName(newImageName2);
            //inputImage2.draw();
            inputImage2.save();
        }
    }

    public ImageResource makeInverse(ImageResource inImage3){
        ImageResource outImage3 = new ImageResource(inImage3.getWidth(), inImage3.getHeight());
        for(Pixel pixel3 : outImage3.pixels()){
            Pixel inPixel3 = inImage3.getPixel(pixel3.getX(), pixel3.getY());
            int inverseColor =  255 - inPixel3.getRed();
            pixel3.setRed(inverseColor);
            inverseColor = 255 - inPixel3.getBlue();
            pixel3.setBlue(inverseColor);
            inverseColor = 255 - inPixel3.getGreen();
            pixel3.setGreen(inverseColor);
            
        }
        return outImage3;
    }

    public void selectInverseAndSave(){
        DirectoryResource dr4 = new DirectoryResource();
        for(File myFile4 : dr4.selectedFiles()){
            ImageResource inputImage4 = new ImageResource(myFile4);
            String imageName4 = inputImage4.getFileName();
            String newImageName4 = "copies_of_images/" + "inverse-" + imageName4;
            inputImage4 = makeInverse(inputImage4);
            inputImage4.setFileName(newImageName4);
            //inputImage2.draw();
            inputImage4.save();
        }
    }
/*
    public ImageResource picturePart(ImageResource inputImage5, int width5, int heigth5){
        ImageResource outImage5 = new ImageResource(width5, heigth5);
        for(Pixel pixel5 : outImage5.pixels()){
            pixel5 = inputImage5.getPixel(pixel5.getX(), pixel5.getY());            
        }
        return outImage5;
    }

    public void selectCutSave(){
        ImageResource inputImage6 = new ImageResource();
        int width6 = inputImage6.getWidth();
        int heigth6 = inputImage6.getHeight();
        width6 = width6 / 3;
        heigth6 = heigth6 / 3;
        ImageResource outputImage6 = picturePart(inputImage6, width6, heigth6);
        outputImage6.draw();
    }
*/
    public static void main(String[] args) {
        Main mine = new Main();
        // test makeGrey
        //ImageResource ir = new ImageResource();
        //ImageResource withGray = mine.makeGray(ir);
        //withGray.draw();
        // test selectConvertGrayAndSave
        //mine.selectConvertGrayAndSave();
        // test makeInverse
        //ImageResource ir = new ImageResource();
        //ImageResource withInversion = mine.makeInverse(ir);
        //withInversion.draw();
        // test selectInverseAndSave
        mine.selectInverseAndSave();
        //mine.selectCutSave();
    }
}
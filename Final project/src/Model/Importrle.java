/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


public class Importrle {

    private static  String URLinTextFromat="";

    /**
     * Method for importing a RLE file from the computer.
     * The contents of the file will be converted into a string value that will be added into the global String text
     * which will be used later on in the decoder method.
     *
     * @return returns a string that contains the content of the imported RLE file in a text format.
     */
    public static String filechooser(){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Import Pattern");
        chooser.getExtensionFilters().addAll(
         new ExtensionFilter("RLE Files", "*.rle"));



        File file = chooser.showOpenDialog(new Stage());


        if(file!=null){


         try (
               FileReader read = new FileReader(file);
               BufferedReader reader= new BufferedReader(read);){

               String line =reader.readLine();
                while(line !=null){
                   URLinTextFromat+=" "+line;
                   line=reader.readLine();
               }

//               System.out.println(text);

        }catch(Exception e){

                Popupwindow.display("Error", "Invalid file, please choose a rle file.");
                System.err.println();
                }
        }
        return URLinTextFromat;
    }

    /**
     * Method for importing a RLE file from a URL.
     * The contents of the URL page will be converted into a string value that will be added into the global String text
     * which will be used later on in the decoder method.
     *
     * @param url String value containing the URL address
     * @return returns a string that contains the contents imported RLE URL in a text format.
     */
    public static String ImportfromURL(String url){


        // http://www.conwaylife.com/patterns/gosperglidergun.rle
        try{
            URL destination=new URL(url);
            URLConnection conn=destination.openConnection();
            Reader input = new InputStreamReader(conn.getInputStream());
            BufferedReader reader2=new BufferedReader(input);

            String line2=reader2.readLine();
            while (line2 !=null){
                URLinTextFromat+=" "+line2;
                line2=reader2.readLine();
            }
//            System.out.println(URLinTextFromat);

        }catch (Exception e){
           Popupwindow.display("Error", "Invalid URL, please use a rle URL.");
        }return URLinTextFromat;

    }

    /**
     * Method for decoding the RLE file and turning it into a new cell pattern.
     * This methods takes the string value from the filechooser and ImportFromURL method and converts the string into new data that makes a new Cell Pattern.
     *
     * @return returns a byte array that contains a new Cell pattern taken from the RLE file.
     */
    public static byte[][] decoder(){

 // looks for the size of the pattern in the text and forwards it on to a temporary array called RLEPattern

        Pattern size = Pattern.compile( "x = (\\d+), y = (\\d+)");
        Matcher matchSize = size.matcher(URLinTextFromat);
        int x=0, y=0;
        if (matchSize.find()){
             x = Integer.parseInt(matchSize.group(1));
             y = Integer.parseInt(matchSize.group(2));


        }


        byte[][] RLEPattern = new byte[y][x];

// Code below will try to find and decode the RLE pattern and set it in the RLEPattern array

        try{
        Pattern FindStringForCells = Pattern.compile("( ?\\d? ?b? ?\\d? ?o? ?)+[$!]");
        Pattern FindAllCells = Pattern.compile("\\d*b|\\d*o");

        Matcher matchFindStringForCells = FindStringForCells.matcher(URLinTextFromat);

        int row = 0, multiplechar, column;
// Finds all Pattern in the text file
        while (matchFindStringForCells.find()) {
            String replaceall = matchFindStringForCells.group().replaceAll("\\s ",".");


            Matcher matchFindAllCells = FindAllCells.matcher(replaceall);

            column = 0;
            while (matchFindAllCells.find()) {
// Code of decoding the pattern if there is a number infront of the characters "o" (alive cell) and "b" (dead cell).
// this code will generate an amount of "o" and "b" based on the number infront of them -1.
                if (matchFindAllCells.group().length() != 1) {
                    multiplechar = Integer.parseInt(matchFindAllCells.group().substring(0, matchFindAllCells.group().length() - 1));


                    if (matchFindAllCells.group().charAt(matchFindAllCells.group().length() - 1) == 'o') {
                        for (int ColumnIndex = column; ColumnIndex < column + multiplechar; ColumnIndex++) {
                            RLEPattern[row][ColumnIndex] = 1;

                        }
                    } else {
                        for (int ColumnIndex = column; ColumnIndex < column + multiplechar; ColumnIndex++) {
                            RLEPattern[row][ColumnIndex] = 0;

                        }
                    }
                     column += multiplechar;
                } else {
//Code for decoding just the characters "o" and "b" regardless if theres is a number infront of them
                    if (matchFindAllCells.group().charAt(0) == 'o') {
                        RLEPattern[row][column] = 1;

                    } else {
                        RLEPattern[row][column] = 0;

                    }

                    column++;
                }
            }

         row++;
        }



        }catch(Exception error){
        Popupwindow.display("Error", "The rle file is corrupted.");}

            return RLEPattern;

    }


    /**
     * Method for emptying the string.
     */
    public static void emptystring(){
    URLinTextFromat=" ";
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.BoardType;
import static Model.Cell.griddimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;


public class DynamicCell extends BoardType {


    static List<List<Integer>> dynamicboard= new ArrayList<>();

        //----------------------------------dynamic board-----------------------

    /**
     * Method for creating the Array list for the dynamic board
     *
     * @param i sets the size of the Arraylist(this will normally get the size of the canvas height)
     * @param j sets the size of the Arraylist(this will normally get the size of the canvas width)
     *
     */
    public static void setdynamicboard(double i, double j){
        for(int y=0;y<i;y++){
            ArrayList<Integer>boardcolumn= new ArrayList<>();
            for(int x=0;x<j;x++){
            boardcolumn.add(0);
            }
            dynamicboard.add(boardcolumn);
        }
    }

    /**
     * Method for generating another ArrayList based on the size of the dynamicboard ArrayList
     * This will generate the size of the next generation ArrayList
     *
     * @param dynamicnextmove
     */
     public static void createnextboardmove(List<List<Integer>> dynamicnextmove){


     for(int y=0;y<dynamicboard.size();y++){
            ArrayList<Integer>nextboardcolumn= new ArrayList<>();
            for(int x=0;x<dynamicboard.get(y).size();x++){
            nextboardcolumn.add(0);
            }
            dynamicnextmove.add(nextboardcolumn);
        }

    }
    /**
     * Method for transferring the new array list containing alive and dead cell in the into the array list used to draw the cells.
     * This method will provide the new cells to be drawn on the next generation for a dynamic board
     *
     */
     @Override
    public void rungame(){

    List<List<Integer>> dynamicnextmove= new ArrayList<>();
    createnextboardmove(dynamicnextmove);

      try{
      for(int i=0;i<dynamicboard.size();i++){
        for(int j=0;j<dynamicboard.get(0).size();j++){
                dynamicnextmove.get(i).set(j,Rules.dynamicdecideneighbor(i,j));
            }
        }
       for(int i=0;i<dynamicboard.size();i++){
        for(int j=0;j<dynamicboard.get(i).size();j++){

                dynamicboard.get(i).set(j, dynamicnextmove.get(i).get(j));
            }
        }

      }catch(Exception e){

      }

    }



   /**
     * Method for drawing the active cell in the canvas for a dynamic board.
     *
     *
     * @param g This specifies which graphics context will be used.
     * @param c This specifies which canvas the cell will be drawn in.
     * @param cp Gives a color value that changes the color of the cell.
     * @param shape This decides what shape type the cells will be drawn as(Circle or Square).
     */
    @Override
    public void drawCell(GraphicsContext g, Canvas c,ColorPicker cp,boolean shape){

        for(int y=0;y<dynamicboard.size();y++){
            for(int x=0;x<dynamicboard.get(y).size();x++){
                if(dynamicboard.get(y).get(x)==1){
                       Color cellc = cp.getValue();
                       g.setFill(cellc);

                       int n =(int) (y*c.getHeight()/griddimension);
                       int m=(int) (x*c.getWidth()/griddimension);

                       if(shape==false){
                           g.fillRect(m, n, c.getWidth()/griddimension, c.getHeight()/griddimension);
                       }else{
                           g.fillOval(m, n, c.getWidth()/griddimension, c.getHeight()/griddimension);}
                }
            }
        }

    }


    /**
    * Method for activating and deactivating a cell in the canvas in a specific coordinate that has been mouse clicked on.
    * This method is for a dynamic board
    *
    * @param y Coordinates of the y-axis  where the mouse clicked on.
    * @param x Coordinates of the y-axis  where the mouse clicked on.
    * @param c This specifies which canvas is being used and click on.
    */
    @Override
   public void clickactivatecell(int y, int x,Canvas c){
       try{
        int i=(int) (griddimension*y/c.getHeight());
        int j=(int) (griddimension*x/c.getWidth());

        if(dynamicboard.get(i).get(j)==0){dynamicboard.get(i).set(j,1);} else {
            dynamicboard.get(i).set(j,0);
        }
       }catch(Exception e){

       }

    }

   /**
    * Method for activating and deactivating a cell in the canvas in a specific coordinate that has been mouse clicked and drag on.
    * This method is for a dynamic board
    * @param y Coordinates of the y-axis  where the mouse clicked and drag on.
    * @param x Coordinates of the y-axis  where the mouse clicked and drag on.
    * @param c This specifies which canvas is being used and click on.
    */
   @Override
    public void dragactivatecell(int y, int x,Canvas c){
        try{
        int i=(int) (griddimension*y/c.getHeight());
        int j=(int) (griddimension*x/c.getWidth());

        if(dynamicboard.get(i).get(j)==1){dynamicboard.get(i).set(j,0);}
        if(dynamicboard.get(i).get(j)==0){dynamicboard.get(i).set(j,1);}

        }catch(Exception e){

        }

    }

    /**
     * Method for generating random Cells in a dynamic board
     */
    @Override
     public void randomcell(){
        Random r = new Random();
           for(int y=0;y<griddimension;y++){
            for (int x=0;x<griddimension;x++){
                int z = r.nextInt(2);
                dynamicboard.get(y).set(x, z);
            }

        }
    }

    /**
     * Method for increasing the row size of the dynamicboard ArrayList
     */
    public static void increaseboardrow(){
        for(int y=0;y<5;y++){
            for(int x=0;x<dynamicboard.size();x++){
                dynamicboard.get(x).add(0);
            }

        }

    }

    /**
     * Method for increasing the column size of the dynamicboard ArrayList
     */
    public static void increaseboardcolumn(){
        for(int x=0;x<5;x++){
            ArrayList<Integer> column= new ArrayList<>();
                for(int y=0;y<dynamicboard.get(0).size();y++){
                    column.add(0);
                }
                dynamicboard.add(column);
        }

    }



     /**
      * Method for resizing the game board when an active cell is near the edge
      */
    public static void dynamicboard(){

        for(int a=0;a<griddimension;a++){
            for(int b=0;b<4;b++){


// Checks the right side border if any live cells are near it and if there are it will increase the size of the board
        if(dynamicboard.get(a).get(griddimension-b )==1){
            if(dynamicboard.size()<1000){
               griddimension+=5;

               increaseboardrow();
           }

        }

// Checks the under side border if any live cells are near it and if there are it will increase the size of the board
        if(dynamicboard.get(griddimension-b).get(a)==1){
            if(dynamicboard.size()<1000){
                griddimension+=5;

                increaseboardrow();

                }
            }

// Checks the top side border if any live cells are near it and if there are it will increase the size of the board
        if(dynamicboard.get(0+b).get(a)==1){
            if(dynamicboard.size()<1000){

                for(int i=griddimension;i>=0;i--){
                    for(int j=griddimension;j>=0;j--){
                        if(dynamicboard.get(i).get(j)==1){

                        dynamicboard.get(i+5).set(j, dynamicboard.get(i).get(j));

                        dynamicboard.get(i).set(j,0);

                        increaseboardrow();

                                    }
                                }
                            }
                        griddimension+=5;
                }
        }

// Checks the left side border if any live cells are near it and if there are it will increase the size of the board
       if(dynamicboard.get(a).get(0+b)==1){
            if(dynamicboard.size()<1000){

                for(int i=griddimension;i>=0;i--){
                    for(int j=griddimension;j>=0;j--){
                         if(dynamicboard.get(i).get(j)==1){

                        dynamicboard.get(i).set(j+5, dynamicboard.get(i).get(j));

                        dynamicboard.get(i).set(j,0);

                        increaseboardrow();


                                    }
                                }
                             }
                        griddimension+=5;
                }
        }



            }
        }
    }


     /**
    * Method for reseting the canvas. Deactivating all the active cells.
    */
    @Override
   public void resetGrid(){
        for(int y=0;y<dynamicboard.size();y++){
            for(int x=0;x< dynamicboard.get(y).size();x++){

                if(dynamicboard.get(y).get(x)==1){
                        dynamicboard.get(y).set(x,0);
                }
            }
        }
   }


       /**
     * Method for transferring the array from a pattern into the array used to draw the cells.
     * This method takes a byte array from the Pattern imported via file or URL, or from the item chosen in the choicebox.
     *
     * @param newmove Array containing the new coordinates of the cells that are alive and dead.
     * @param a Contains the Integer value to move the pattern on the Y-axis.
     * @param b Contains the Integer value to move the pattern on the X-axis.
     */
   @Override
    public void loadnewmove(byte[][] newmove,int a,int b,boolean dynamic){

        for (int y = 0; y < newmove.length; y++){
            for (int x = 0 ; x < newmove[y].length; x++){

                    dynamicboard.get(y+a).set(x+b,(int) newmove[y][x]);



            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.BoardType;
import static Model.DynamicCell.dynamicboard;
import java.util.Random;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;


public class Cell extends BoardType{



    static int wid=500;
    static int hei=500;
    static byte currentmove[][]=new byte[hei][wid];
    static byte nextmove[][]=new byte[hei][wid];





    
    static int griddimension = 50;

    /**
     * Method for drawing the grid of the game in the canvas.
     * Two grid types can be drawn depending on the boolean shape value, either square or circle grid shape type.
     *
     *
     * @param g This specifies which graphics context will be used.
     * @param c This specifies which canvas the grid will be drawn in.
     * @param shape This decides what shape type the grid will be drawn as(Circle or Square).
     */
    public static void creategrid(GraphicsContext g,Canvas c,boolean shape){

    g= c.getGraphicsContext2D();
    g.strokeRect(0,0,c.getWidth(),c.getHeight());
    g.setFill(Color.BLACK);


    if(shape==false){

// Code  for generating square shape grid
    //Horizontal line
    for(int i =1; i<griddimension;i++){
            int y = (int) (i * c.getHeight()/griddimension);
           g.strokeLine(0,y,c.getWidth(),y);

        }
    //vertical line
        for(int j=1; j<griddimension;j++){
         int x = (int) (j * c.getWidth()/griddimension);
           g.strokeLine(x,0,x,c.getHeight());

        }
    }else{

 // code for generation cirlce shape grid
       for(int y=0;y<griddimension;y++){
            for(int x=0;x<griddimension;x++){

                       int n =(int) (y*c.getHeight()/griddimension);
                       int m=(int) (x*c.getWidth()/griddimension);

                       g.strokeOval(m, n, c.getWidth()/griddimension, c.getHeight()/griddimension);

            }
        }
    }
    }

    /**
     * Method for generating random alive and dead cell.
     *
     */
    @Override
    public void randomcell(){
        Random r = new Random();
           for(int y=0;y<griddimension;y++){
            for (int x=0;x<griddimension;x++){
                int z = r.nextInt(2);
                currentmove[y][x]=(byte) z;
            }

        }
    }

    /**
     * Method for drawing the active cell in the canvas.
     *
     *
     * @param g This specifies which graphics context will be used.
     * @param c This specifies which canvas the cell will be drawn in.
     * @param cp Gives a color value that changes the color of the cell.
     * @param shape This decides what shape type the cells will be drawn as(Circle or Square).
     */
    @Override
    public void drawCell(GraphicsContext g, Canvas c,ColorPicker cp,boolean shape){
        for(int y=0;y<griddimension;y++){
            for(int x=0;x<griddimension;x++){
                if(currentmove [y][x]==1){
                       Color cellc = cp.getValue();
                       g.setFill(cellc);

                       int n =(int) (y*c.getHeight()/griddimension);
                       int m=(int) (x*c.getWidth()/griddimension);

                       if(shape==false){
               // code for generating square shape cell if the grid shape is also square shape
                           g.fillRect(m, n, c.getWidth()/griddimension, c.getHeight()/griddimension);
                       }else{
               // code for generating circle shape cell if the grid shape is also cirlce shape
                           g.fillOval(m, n, c.getWidth()/griddimension, c.getHeight()/griddimension);}
                }
            }
        }

    }
   /**
    * Method for reseting the canvas. Deactivating all the active cells.
    */
   @Override
   public void resetGrid(){
        for(int y=0;y<hei;y++){
            for(int x=0;x< wid;x++){
                if(currentmove[y][x]==1){
                    currentmove[y][x]=0;

            }
        }
    }
   }


   /**
    * Method for activating and deactivating a cell in the canvas in a specific coordinate that has been mouse clicked on.
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

        if(currentmove[i][j]==0){currentmove[i][j]=1;} else {
            currentmove[i][j]=0;
        }
       }catch(Exception e){
           System.out.println("Click outside of canvas");
        }

    }

   /**
    * Method for activating and deactivating a cell in the canvas in a specific coordinate that has been mouse clicked and drag on.
    *
    * @param y Coordinates of the y-axis  where the mouse clicked and drag on.
    * @param x Coordinates of the y-axis  where the mouse clicked and drag on.
    * @param c This specifies which canvas is being used and click on.
    */
   @Override
    public  void dragactivatecell(int y, int x,Canvas c){
        try{
        int i=(int) (griddimension*y/c.getHeight());
        int j=(int) (griddimension*x/c.getWidth());

        if(currentmove[i][j]==1){currentmove[i][j]=0;}
        if(currentmove[i][j]==0){currentmove[i][j]=1;}

    }catch(Exception e){
        System.out.println("Click and dragged outside of canvas");
    }

    }

    /**
     * Method for transferring the new array containing alive and dead cell in the into the array used to draw the cells.
     * This method will provide the new cells to be drawn on the next generation.
     *
     */
    @Override
    public void rungame(){
       for(int i=0;i<griddimension;i++){
        for(int j=0;j<griddimension;j++){
                nextmove[i][j]=Rules.decideneighbor(i,j);
            }
        }
       for(int i=0;i<griddimension;i++){
        for(int j=0;j<griddimension;j++){
                currentmove[i][j]=nextmove[i][j];
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

                currentmove[y+a][x+b] = newmove[y][x];

            }
        }
    }
    /**
     * Method for changing the size of the grid
     * @param newgriddimension Contains the new value for the size of the grid to be drawn.
     */
    public static void changegriddimension(int newgriddimension){
    griddimension=newgriddimension;

    }


}

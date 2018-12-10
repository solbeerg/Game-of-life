/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


public class CellPattern {


   /**
    * Method that provides coordinates for a pattern (Glider)
    *
    * @return returns an array containing a new pattern.
    */
   public static byte[][] drawGlider(){

    byte[][] newmove = {{0,1,0},
                        {0,0,1},
                        {1,1,1}};
    return newmove;
   }

   /**
    * Method that provides coordinates for a pattern (Exploder)
    *
    * @return returns an array containing a new pattern.
    */
   public static byte[][] drawExploder(){

    byte[][] newmove = {{1,0,1,0,1},
                        {1,0,0,0,1},
                        {1,0,0,0,1},
                        {1,0,0,0,1},
                        {1,0,1,0,1}};
    return newmove;
   }

   /**
    * Method that provides coordinates for a pattern (Tumbler)
    *
    * @return returns an array containing a new pattern.
    */
   public static byte[][] drawTumbler(){

    byte[][] newmove = {{0,1,1,0,1,1,0},
                        {0,1,1,0,1,1,0},
                        {0,0,1,0,1,0,0},
                        {1,0,1,0,1,0,1},
                        {1,0,1,0,1,0,1},
                        {1,1,0,0,0,1,1}};
    return newmove;
   }

   /**
    * Method that provides coordinates for a pattern (10 cell row)
    *
    * @return returns an array containing a new pattern.
    */
   public static byte[][] draw10cellrow(){

    byte[][] newmove = {{1,1,1,1,1,1,1,1,1,1}};
    return newmove;
   }
}

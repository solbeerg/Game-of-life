/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.Cell.currentmove;
import static Model.DynamicCell.dynamicboard;
import static Model.Cell.hei;
import static Model.Cell.wid;



public class Rules {

       /**
        * Method for counting the neighbors of an active cell and deciding if a cell will die or live.
        *
        *
        * @param i coordinates of the y axis of a cell
        * @param j coordinates of the x axis of a cell
        * @return returns a byte array that contains new coordinates for all the cells that are alive and dead.
        */
       public static byte decideneighbor(int i, int j){
       int neighbor=0;
//Checks the number of neighbors on the left side

/*
side where the x are
    x  0  0
    x  1  0
    x  0  0

*/

//directly left of the active cell
       if(j>0){
           if(currentmove[i][j-1]==1){
               neighbor++;
           }

 //Upper-left side
           if(i>0){
               if(currentmove[i-1][j-1]==1){
                   neighbor++;
               }
           }

//Lower left side
           if(i<hei-1){
               if(currentmove[i+1][j-1]==1){
                   neighbor++;
               }
           }
       }

//Checks the number of neighbors on the right side

/*
side where the x are
    0  0  x
    0  1  x
    0  0  x

*/

//directly right of the active cell
       if(j<wid-1){
           if(currentmove[i][j+1]==1){
               neighbor++;
           }

//Upper-right side
           if(i>0){if(currentmove[i-1][j+1]==1){
               neighbor++;
           }
           }

//Lower-right side
           if(i<hei-1){if(currentmove[i+1][j+1]==1){
               neighbor++;
           }
           }
       }


//Checks if there's an active cell directly above the cell

/*
side where the x are
    0  x  0
    0  1  0
    0  0  0

*/
       if(i>0){
           if(currentmove[i-1][j]==1){
               neighbor++;
           }
       }



//Checks if there's an active cell directly below the cell

/*
side where the x are
    0  0  0
    0  1  0
    0  x  0

*/
       if(i<hei-1){
           if(currentmove[i+1][j]==1){
               neighbor++;
           }
       }


       if(neighbor==3)return 1;
       else if(currentmove[i][j]==1 && neighbor==2)return 1;
       else {return 0;}
      }


     /**
      * Method for counting the neighbors of an active cell in a dynamic board and deciding if a cell will die or live.
      *
      *
      * coordinates of the y axis of a cell
      * @param j coordinates of the x axis of a cell
      * @return returns a integer array that contains new coordinates for all the cells that are alive and dead.
      */
       public static int dynamicdecideneighbor(int i, int j){
       int neighbor=0;

 try{

//Checks the number of neighbors on the left side

/*
side where the x are
    x  0  0
    x  1  0
    x  0  0

*/

   if(j>0){


//directly left of the active cell
           if(dynamicboard.get(i).get(j-1)==1){
               neighbor++;
           }

 //Upper-left side
           if(i>0){
               if(dynamicboard.get(i-1).get(j-1)==1){
                   neighbor++;
               }
           }
//Lower-left side
           if(i<dynamicboard.size()-1){
               if(dynamicboard.get(i+1).get(j-1)==1){
                   neighbor++;
               }
           }
       }



//Checks the number of neighbors on the right side

/*
side where the x are
    0  0  x
    0  1  x
    0  0  x

*/
    if(j<dynamicboard.size()-1){

//directly right of the active cell
           if(dynamicboard.get(i).get(j+1)==1){
               neighbor++;
           }

//Upper-right side
           if(i>0){
               if(dynamicboard.get(i-1).get(j+1)==1){
                   neighbor++;
               }
           }


//Lower-right side
           if(i<dynamicboard.size()-1){
               if(dynamicboard.get(i+1).get(j+1)==1){
                   neighbor++;
               }
           }
       }


//Checks if there's an active cell directly above the cell

/*
side where the x are
    0  x  0
    0  1  0
    0  0  0

*/
       if(i>0){
           if(dynamicboard.get(i-1).get(j)==1){
               neighbor++;
           }
       }



//Checks if there's an active cell directly below the cell

/*
side where the x are
    0  0  0
    0  1  0
    0  x  0

*/
       if(i<dynamicboard.size()-1){
           if(dynamicboard.get(i+1).get(j)==1){
               neighbor++;
           }
       }

        }catch(ArrayIndexOutOfBoundsException e){

        }

//       if(dynamicboard.get(i).get(j)==1)System.out.println(i + " " + j + " " + neighbor);

       if(neighbor==3)return 1;
       else if(dynamicboard.get(i).get(j)==1 && neighbor==2)return 1;
       else {return 0;}
      }
}

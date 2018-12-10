/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.Cell.currentmove;
import static Model.Cell.nextmove;
import static Model.DynamicCell.dynamicboard;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jeremiah
 */
public class RulesTest { 
   
   
    
    public RulesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of decideneighbor method, of class Rules.
     */
    @Test
    public void testDecideneighbor() {
        System.out.println("decideneighbor");
//           
//         byte [][] testmove ={{0,0,0}
//                            ,{0,1,0}
//                            ,{0,0,0}};
         
         currentmove[1][1]=1;       currentmove[2][0]=0;
         currentmove[1][0]=0;       currentmove[2][1]=0;
         currentmove[1][2]=0;       currentmove[2][2]=0;
         currentmove[0][0]=0;
         currentmove[0][1]=0;
         currentmove[0][2]=0;
      
        
        int i = 1;
        int j = 1;
            
        byte expResult =0;
        byte result = Rules.decideneighbor(i, j);
        assertEquals(expResult, result);

    }
     /**
     * Test of decideneighbor method, of class Rules.
     */
    @Test
    public void testDecideneighbor2() {
        System.out.println("decideneighbor");
       
//         byte [][] testmove2 ={{0,0,0}
//                              ,{1,1,1}
//                              ,{0,0,0}};
         
         currentmove[1][0]=1; currentmove[1][1]=1; currentmove[1][2]=1;
        
        int i = 1;
        int j = 1;
        

            
        byte expResult =1;
        byte result = Rules.decideneighbor(i, j);
        assertEquals(expResult, result);

    }
     /**
     * Test of decideneighbor method, of class Rules.
     */
    @Test
    public void testDecideneighbor3() {
        System.out.println("decideneighbor");
//       
//        byte [][] testmove3 ={{0,0,1}
//                            ,{1,1,0}
//                            ,{0,0,0}};     

        currentmove[1][0]=1; currentmove[1][1]=1; currentmove[0][2]=1;
        
        int i = 1;
        int j = 1;
            
        byte expResult =1;
        byte result = Rules.decideneighbor(i, j);
        assertEquals(expResult, result);

    }

    /**
     * Test of dynamicdecideneighbor method, of class Rules.
     */
    @Test
    public void testDynamicdecideneighbor() {
        System.out.println("dynamicdecideneighbor");
        
        DynamicCell.setdynamicboard(5,5);
        
        dynamicboard.get(2).set(0,0);   dynamicboard.get(3).set(0,0);   dynamicboard.get(1).set(0,0);
        dynamicboard.get(2).set(1,1);   dynamicboard.get(3).set(1,0);   dynamicboard.get(1).set(1,0);
        dynamicboard.get(2).set(2,1);   dynamicboard.get(3).set(2,0);   dynamicboard.get(1).set(2,0);
        dynamicboard.get(2).set(3,1);   dynamicboard.get(3).set(3,0);   dynamicboard.get(1).set(3,0);
        dynamicboard.get(2).set(4,0);   dynamicboard.get(3).set(4,0);   dynamicboard.get(1).set(4,0);
        
        
        
        int i = 2;
        int j = 2;
        int expResult = 1;
        int result = Rules.dynamicdecideneighbor(i, j);
        assertEquals(expResult, result);
    }
    
        /**
     * Test of dynamicdecideneighbor method, of class Rules.
     */
    @Test
    public void testDynamicdecideneighbor2() {
        System.out.println("dynamicdecideneighbor");
        
        DynamicCell.setdynamicboard(5,5);
        
        dynamicboard.get(2).set(0,0);   dynamicboard.get(3).set(0,0);   dynamicboard.get(1).set(0,0);
        dynamicboard.get(2).set(1,0);   dynamicboard.get(3).set(1,0);   dynamicboard.get(1).set(1,0);
        dynamicboard.get(2).set(2,1);   dynamicboard.get(3).set(2,0);   dynamicboard.get(1).set(2,0);
        dynamicboard.get(2).set(3,0);   dynamicboard.get(3).set(3,0);   dynamicboard.get(1).set(3,0);
        dynamicboard.get(2).set(4,0);   dynamicboard.get(3).set(4,0);   dynamicboard.get(1).set(4,0);
        
        
        
        int i = 2;
        int j = 2;
        int expResult = 0;
        int result = Rules.dynamicdecideneighbor(i, j);
        assertEquals(expResult, result);
    }
    
        /**
     * Test of dynamicdecideneighbor method, of class Rules.
     */
    @Test
    public void testDynamicdecideneighbor3() {
        System.out.println("dynamicdecideneighbor");
        
        DynamicCell.setdynamicboard(5,5);
        
        dynamicboard.get(2).set(0,0);   dynamicboard.get(3).set(0,0);   dynamicboard.get(1).set(0,0);
        dynamicboard.get(2).set(1,1);   dynamicboard.get(3).set(1,0);   dynamicboard.get(1).set(1,0);
        dynamicboard.get(2).set(2,1);   dynamicboard.get(3).set(2,0);   dynamicboard.get(1).set(2,0);
        dynamicboard.get(2).set(3,0);   dynamicboard.get(3).set(3,0);   dynamicboard.get(1).set(3,1);
        dynamicboard.get(2).set(4,0);   dynamicboard.get(3).set(4,0);   dynamicboard.get(1).set(4,0);
        
        
        
        int i = 2;
        int j = 2;
        int expResult = 1;
        int result = Rules.dynamicdecideneighbor(i, j);
        assertEquals(expResult, result);
    }
    
    

    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.Cell.currentmove;
import static Model.Cell.nextmove;
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
public class CellTest {
    
    public CellTest() {
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
     * Test of rungame method, of class Cell.
     */
    @Test
    public void testRungame() {
        System.out.println("rungame");
        Cell instance = new Cell();
        int heig = 5;
        int widt = 5;
        //--------board view----------------
        /*
        byte [][] testmove ={{0,0,0,0,0}
                            ,{0,0,0,0,0}
                            ,{0,1,1,1,0}
                            ,{0,0,0,0,0}
                            ,{0,0,0,0,0}};
        
        byte [][] expectedresult = {{0,0,0,0,0}
                                   ,{0,0,1,0,0}
                                   ,{0,0,1,0,0}
                                   ,{0,0,1,0,0}
                                   ,{0,0,0,0,0}};
       */ 
        
      // coordinates in the game board  
        currentmove[2][1]=1;
        currentmove[2][2]=1;
        currentmove[2][3]=1;
        
       // expected result 
        nextmove[1][2]=1;
        nextmove[2][2]=1;
        nextmove[3][2]=1;
                
    
        instance.rungame();
        
        assertArrayEquals(currentmove,nextmove);
        
        
//        assertEquals("[[0, 0, 0, 0, 0], [0, 0, 1, 0, 0], [0, 0, 1, 0, 0], [0, 0, 1, 0, 0], [0, 0, 0, 0, 0]]", Arrays.deepToString(currentmove));
    }
    
    
    
    /**
     * Test of rungame method, of class Cell.
     */
    @Test
    public void testRungame2() {
        System.out.println("rungame");
        Cell instance = new Cell();
        int heig = 5;
        int widt = 5;
        //--------board view----------------
        /*
        byte [][] testmove ={{0,0,0,0,0}
                            ,{0,1,0,0,1}
                            ,{0,0,1,1,0}
                            ,{0,0,1,1,0}
                            ,{0,1,0,0,1}};
        
        byte [][] expectedresult = {{0,0,0,0,0}
                                   ,{0,0,1,1,0}
                                   ,{0,1,0,0,1}
                                   ,{0,1,0,0,1}
                                   ,{0,0,1,1,0}};
       */ 
        
        // coordinates in the game board  
        
        currentmove[1][1]=1; currentmove[1][4]=1;
        currentmove[2][2]=1; currentmove[2][3]=1;
        currentmove[3][2]=1; currentmove[3][3]=1;
        currentmove[4][1]=1; currentmove[4][4]=1;
        
        // expected result
        nextmove[1][2]=1; nextmove[1][3]=1;
        nextmove[2][1]=1; nextmove[2][4]=1;
        nextmove[3][1]=1; nextmove[3][4]=1;
        nextmove[4][2]=1; nextmove[4][3]=1;
                
       
       instance.rungame();
        
        assertArrayEquals(currentmove,nextmove);
        
        
//        assertEquals("[[0, 0, 0, 0, 0], [0, 0, 1, 0, 0], [0, 0, 1, 0, 0], [0, 0, 1, 0, 0], [0, 0, 0, 0, 0]]", Arrays.deepToString(currentmove));
    }
    
    
      /**
     * Test of rungame method, of class Cell.
     */
    @Test
    public void testRungame3() {
        System.out.println("rungame");
        Cell instance = new Cell();
        int heig = 5;
        int widt = 5;
        //--------board view----------------
        /*
        byte [][] testmove ={{0,0,0,0,0}
                            ,{0,1,1,1,0}
                            ,{0,1,1,1,0}
                            ,{0,1,1,1,0}
                            ,{0,0,0,0,0}};
        
        byte [][] expectedresult = {{0,0,1,0,0}
                                   ,{0,1,0,1,0}
                                   ,{1,0,0,0,1}
                                   ,{0,1,0,1,0}
                                   ,{0,0,1,0,0}};
       */ 
        
        // coordinates in the game board  
        
        currentmove[1][1]=1; currentmove[1][2]=1;  currentmove[1][3]=1;
        currentmove[2][1]=1; currentmove[2][2]=1;  currentmove[2][3]=1;
        currentmove[3][1]=1; currentmove[3][2]=1;  currentmove[3][3]=1;
        
        // expected result
        nextmove[0][2]=1;
        nextmove[1][1]=1; nextmove[1][3]=1;
        nextmove[2][0]=1; nextmove[2][4]=1;
        nextmove[3][1]=1; nextmove[3][3]=1;
        nextmove[4][2]=1;
                
       
        instance.rungame();
        
        assertArrayEquals(currentmove,nextmove);
        
//        assertEquals("[[0, 0, 0, 0, 0], [0, 0, 1, 0, 0], [0, 0, 1, 0, 0], [0, 0, 1, 0, 0], [0, 0, 0, 0, 0]]", Arrays.deepToString(currentmove));
    }
    
}

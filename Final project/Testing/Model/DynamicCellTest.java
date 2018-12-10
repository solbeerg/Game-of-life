/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.DynamicCell.dynamicboard;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
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
public class DynamicCellTest {
    
    public DynamicCellTest() {
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
     * Test of rungame method, of class DynamicCell.
     */
    @Test
    public void testRungame() {
        System.out.println("rungame");
        DynamicCell instance = new DynamicCell();
        DynamicCell.setdynamicboard(5,5);
        
        
        dynamicboard.get(2).set(1,1);
        dynamicboard.get(2).set(2,1);
        dynamicboard.get(2).set(3,1);
        
        //expected result
        
       List<List<Integer>> expectedresult = new ArrayList<>();
        
        for(int y=0;y<dynamicboard.size();y++){
            ArrayList<Integer>testboardcolumn= new ArrayList<>();
            for(int x=0;x<dynamicboard.get(y).size();x++){
            testboardcolumn.add(0);
            }
            expectedresult.add(testboardcolumn);
        }
        expectedresult.get(1).set(2,1);
        expectedresult.get(2).set(2,1);
        expectedresult.get(3).set(2,1);
        
        
        instance.rungame();
        assertThat(dynamicboard, is(expectedresult));
        instance.resetGrid();
    }
        /**
     * Test of rungame method, of class DynamicCell.
     */
    @Test
    public void testRungame2() {
        System.out.println("rungame");
        DynamicCell instance2 = new DynamicCell();
        DynamicCell.setdynamicboard(5,5);
       
 
        dynamicboard.get(2).set(2,1);   dynamicboard.get(2).set(3,1);
        dynamicboard.get(3).set(2,1);   dynamicboard.get(3).set(3,1);

        
        //expected result
        
       List<List<Integer>> expectedresult2 = new ArrayList<>();
        
        for(int y=0;y<dynamicboard.size();y++){
            ArrayList<Integer>testboardcolumn2= new ArrayList<>();
            for(int x=0;x<dynamicboard.get(y).size();x++){
            testboardcolumn2.add(0);
            }
            expectedresult2.add(testboardcolumn2);
        }
        
        expectedresult2.get(2).set(2,1);     expectedresult2.get(2).set(3,1);
        expectedresult2.get(3).set(2,1);     expectedresult2.get(3).set(3,1);

        
        
        instance2.rungame();
        
        assertThat(dynamicboard, is(expectedresult2));
        instance2.resetGrid();
    }
    
    
}

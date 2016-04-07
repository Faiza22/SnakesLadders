/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ELEGANT
 */
public class SnakesLaddersTest {
    
    public SnakesLaddersTest() {
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
     * Test of wins method, of class SnakesLadders.
     */
    @Test
    public void testWins() {
        System.out.println("wins");
        int winner = 1;
        SnakesLadders instance = new SnakesLadders();
        String expResult = "Red Won!";
        String result = instance.wins(winner);
        assertEquals(expResult, result);
    }

    /**
     * Test of play method, of class SnakesLadders.
     */
    @Test
    public void testPlay() {
        System.out.println("play");
        int roll = 1;
        SnakesLadders instance = new SnakesLadders();
        boolean expResult = true;
        boolean result = instance.play(roll);
        assertEquals(expResult, result);
    }
    
}

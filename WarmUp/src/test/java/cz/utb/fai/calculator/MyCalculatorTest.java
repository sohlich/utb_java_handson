/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.utb.fai.calculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Tests if calculator works properly.
 *
 * @author Radomir Sohlich
 */
public class MyCalculatorTest {

    public MyCalculatorTest() {
    }

    /**
     * Test of reset method, of class MyCalculator.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        MyCalculator instance = new MyCalculator();
        instance.plus(5);
        instance.reset();
        float result = instance.plus(5);
        assertEquals(5, result, 0.0);

    }

    /**
     * Test of minus method, of class MyCalculator.
     */
    @Test
    public void testMinus() {
        System.out.println("minus");
        int b = 2;
        MyCalculator instance = new MyCalculator();
        float expResult = -2.0F;
        float result = instance.minus(b);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of plus method, of class MyCalculator.
     */
    @Test
    public void testPlus() {
        System.out.println("plus");
        int b = 2;
        MyCalculator instance = new MyCalculator();
        float expResult = 2.0F;
        float result = instance.plus(b);
        assertEquals(expResult, result, 0.0);
    }
//

    /**
     * Test of divideBy method, of class MyCalculator.
     */
    @Test
    public void testDivideBy() {
        System.out.println("divideBy");
        int b = 2;
        MyCalculator instance = new MyCalculator();
        instance.plus(b);
        float expResult = 0.5f;
        float result = instance.divideBy(4);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testDivideByFail() {
        System.out.println("divideBy");
        int b = 0;
        MyCalculator instance = new MyCalculator();
        instance.reset();
        float expResult = Float.NaN;
        float result = instance.divideBy(b);
        assertEquals(expResult, result, 0.0);
    }

//
    /**
     * Test of multiplyBy method, of class MyCalculator.
     */
    @Test
    public void testMultiplyBy() {
        System.out.println("multiplyBy");
        int b = 2;
        MyCalculator instance = new MyCalculator();
        instance.plus(2);
        float expResult = 4.0F;
        float result = instance.multiplyBy(b);
        assertEquals(expResult, result, 0.0);
    }
//

    /**
     * Test of square method, of class MyCalculator.
     */
    @Test
    public void testSquare() {
        System.out.println("square");
        MyCalculator instance = new MyCalculator();
        instance.plus(2);
        float expResult = 4.0F;
        float result = instance.square();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of square method, of class MyCalculator.
     */
    @Test
    public void testGetOperations() {
        System.out.println("square");
        MyCalculator instance = new MyCalculator();
        instance.plus(2);
        instance.minus(3);
        instance.multiplyBy(5);

        String expected = "((2)-3)*5";
        String result = instance.getOperations();
        assertEquals(expected, result);

    }

}

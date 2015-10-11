/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.utb.fai.dataprocesor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Tests the implementation of MyProcessor.
 *
 * @author Radomir Sohlich
 */
public class MyProcessorTest {

    public MyProcessorTest() {
    }

    /**
     * Test of filterBy method, of class MyProcessor.
     */
    @Test
    public void testFilterBy() {
        System.out.println("filterBy");
        int[] array = new int[]{0, 2, 3, 4, 0};
        int num = 0;
        MyProcessor instance = new MyProcessor();
        int[] expResult = new int[]{0, 0};
        int[] result = instance.filterBy(array, num, Operator.EQUAL);
        assertArrayEquals(expResult, result);
    }

//    /**
//     * Test of indexFirst method, of class MyProcessor.
//     */
    @Test
    public void testIndexFirst() {
        System.out.println("testIndexFirst");
        int[] array = new int[]{0, 2, 3, 4, 0};
        int num = 2;
        MyProcessor instance = new MyProcessor();
        int result = instance.indexFirst(array, num, Operator.GREATER);
        assertEquals(2, result);
    }
//

    /**
     * Test of printAll method, of class MyProcessor.
     */
    @Test
    public void testPrintAll() {
        System.out.println("printAll");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream outStream = new PrintStream(baos);
        MyProcessor instance = new MyProcessor();
        instance.printAll(outStream, 1, 1, 1);
        String output = new String(baos.toByteArray());
        String breakLine = System.getProperty("line.separator");
        String expected = String.format("1%s1%s1%s",
                breakLine, breakLine, breakLine);
        assertEquals(expected, output);
    }
//

    /**
     * Test of factorial method, of class MyProcessor.
     */
    @Test
    public void testFactorial() {
        System.out.println("factorial");
        int number = 5;
        MyProcessor instance = new MyProcessor();
        int expResult = 120;
        int result = instance.factorial(number);
        assertEquals(expResult, result);
    }
//
//    /**
//     * Test of sortAsc method, of class MyProcessor.
//     */

    @Test
    public void testSortAsc() {
        System.out.println("testSortAsc");
        int[] array = new int[]{6, 2, 3, 4, 0};
        MyProcessor instance = new MyProcessor();
        int[] expResult = new int[]{0, 2, 3, 4, 6};
        int[] result = instance.sortAsc(array);
        assertArrayEquals(expResult, result);
    }
}

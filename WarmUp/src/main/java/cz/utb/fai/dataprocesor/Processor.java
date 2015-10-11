/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.utb.fai.dataprocesor;

import java.io.PrintStream;

/**
 * Warmup class for loop exercising.
 *
 * @author Radomir Sohlich
 */
public interface Processor {

    /**
     * Filter array by given operator and criteria.
     *
     * @param array input array
     * @param num criteria number
     * @param op operators
     * @return filtered array
     */
    public int[] filterBy(int[] array, int num, Operator op);

    /**
     * Returns the first index for element with given criteria.
     *
     * @param array input array
     * @param num criteria number
     * @param op operators
     * @return index of first element with given criteria
     */
    public int indexFirst(int[] array, int num, Operator op);

    /**
     * Prints all objects to given outputStream.
     *
     * @param outStream output stream to print the objects
     * @param o object array
     */
    public void printAll(PrintStream outStream, Object... o);

    /**
     * Return factorial for given number.
     *
     * @param number n for n!
     * @return factorial of number
     */
    public int factorial(int number);

    /**
     * Sort ascendant given sequence.
     *
     * @param o input array
     * @return sorted array
     */
    public int[] sortAsc(int... o);

}

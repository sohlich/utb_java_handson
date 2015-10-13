/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.utb.fai.dataprocesor;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Radomir Sohlich
 */
public class MyProcessor implements Processor {

    @Override
    public int[] filterBy(int[] array, int num, Operator op) {
        return filter(array, num, Operator.LESS);
    }

    @Override
    public int indexFirst(int[] array, int num, Operator op) {
        for (int index = 0; index < array.length; index++) {
            switch (op) {
                case EQUAL:
                    if (array[index] == num) {
                        return index;
                    }
                    break;
                case GREATER:
                    if (array[index] > num) {
                        return index;
                    }
                    break;
                case LESS:
                    if (array[index] < num) {
                        return index;
                    }
                    break;
                case LESSOREQUAL:
                    if (array[index] <= num) {
                        return index;
                    }
                    break;
                case GREATEROREQUAL:
                    if (array[index] >= num) {
                        return index;
                    }
                    break;
            }
        }
        return -1;
    }

    @Override
    public void printAll(PrintStream outStream, Object... objectArray) {
        for (Object o : objectArray) {
            outStream.println(o);
        }
    }

    @Override
    public int factorial(int number) {
        int result = 1;
        for (int i = number; i > 0; i--) {
            result = result * number;
            number--;
        }
        return result;
    }

    @Override
    public int[] sortAsc(int... o) {
        Arrays.sort(o);
        return o;
    }

    private int[] filter(int[] array, int i, final Operator op) {
        List<Integer> result = new ArrayList<>();
        for (int b : array) {
            switch (op) {
                case EQUAL:
                    if (b < i) {
                        result.add(b);
                    }
                    break;
                case GREATER:
                    if (b > i) {
                        result.add(b);
                    }
                    break;
                case LESS:
                    if (b < i) {
                        result.add(b);
                    }
                    break;
                case LESSOREQUAL:
                    if (b <= i) {
                        result.add(b);
                    }
                    break;
                case GREATEROREQUAL:
                    if (b >= i) {
                        result.add(b);
                    }
                    break;
            }
            if (b <= i) {
                result.add(b);
            }
        }
        return toPrimitive(result);
    }

    private int[] toPrimitive(List<Integer> integerArray) {
        int[] result = new int[integerArray.size()];
        for (int i = 0; i < integerArray.size(); i++) {
            result[i] = integerArray.get(i);
        }
        return result;
    }

}

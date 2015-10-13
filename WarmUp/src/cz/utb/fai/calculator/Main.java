/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.utb.fai.calculator;

/**
 *
 * @author Radomir Sohlich
 */
public class Main {

    public static void main(String[] args) {
        Calculator calculator = new MyCalculator();
        calculator.plus(1);
        calculator.minus(2);
        float result = calculator.calcEqual();
        System.out.println(String.format("Vysledek je %.2f", result));
    }
}

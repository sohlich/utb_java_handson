package cz.utb.fai.calculator;

/**
 * Simple calculator with memory. Default value in memory is zero.
 *
 * @author radek
 */
public interface Calculator {

    public void reset();

    public float minus(int b);

    public float plus(int b);

    public float divideBy(int b);

    public float multiplyBy(int b);

    public float square();

    public float calcEqual();

    public String getOperations();

}

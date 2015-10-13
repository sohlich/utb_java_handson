package cz.utb.fai.calculator;

/**
 * Example implementation of calculator interface.
 *
 * @author Radomir Sohlich
 */
public class MyCalculator implements Calculator {

    private String operations = "";
    private float memory = 0;

    @Override
    public void reset() {
        operations = "";
        memory = 0;
    }

    @Override
    public float minus(int b) {
        appendOperation(b, "-");
        return memory -= b;
    }

    @Override
    public float plus(int b) {
        appendOperation(b, "+");
        return memory += b;
    }

    @Override
    public float divideBy(int b) {
        appendOperation(b, "/");
        return memory = memory / b;
    }

    @Override
    public float multiplyBy(int b) {
        appendOperation(b, "*");
        return memory = memory * b;
    }

    @Override
    public float square() {
        return memory = memory * memory;
    }

    @Override
    public String getOperations() {
        return operations;
    }

    private void appendOperation(int b, String operator) {
        if (operations.isEmpty()) {
            operations = String.valueOf(b);
        } else {
            operations
                    = String.format("(%s)%s%s",
                            operations, operator, String.valueOf(b));
        }

    }

    @Override
    public float calcEqual() {
        return memory;
    }

}

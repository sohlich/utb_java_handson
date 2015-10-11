package cz.utb.fai.dataprocesor;

/**
 *
 * @author Radomir Sohlich
 */
public enum Operator {
    //Binary codes

    GREATER(1), LESS(2), EQUAL(3), LESSOREQUAL(4), GREATEROREQUAL(5);

    public final int code;

    Operator(int code) {
        this.code = code;
    }
}

/**
 * Created by tania on 8/18/16.
 */

import java.util.ArrayList;

/**
 * The interface describes basic methods, that required for
 * implementation of operations of calculator.
 */
public interface CalculationOp {

    public String getSignOfOperation();

    /**
     *
     * @param args massive, that store values of operands
     * @return result of operation
     */
    public double calculate(ArrayList<Double> args);

    public boolean isFunction();

}

import java.util.ArrayList;

/**
 * Created by tania on 7/19/16.
 */
public class Subtraction implements CalculationOp {
    private String signOfOperation = "-";
    @Override
    public String getSignOfOperation() {
        return signOfOperation;
    }

    @Override
    public double calculate(ArrayList<Double> args) {
        double result;
        result = args.get(0) - args.get(1);
        return result;
    }

    @Override
    public boolean isFunction() {
        return false;
    }
}

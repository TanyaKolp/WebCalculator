import java.util.ArrayList;

/**
 * Created by tania on 7/18/16.
 */
public class Add implements CalculationOp {
    private String signOfOperation = "+";
    @Override
    public String getSignOfOperation() {
        return signOfOperation;
    }

    @Override
    public double calculate(ArrayList<Double> args) {
        double result;
        result = args.get(0) + args.get(1);
        return result;
    }

    @Override
    public boolean isFunction() {
        return false;
    }

}

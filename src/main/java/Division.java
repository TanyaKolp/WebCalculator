import java.util.ArrayList;

/**
 * Created by tania on 7/19/16.
 */
public class Division implements CalculationOp {
    private String signOfOperation = "/";

    @Override
    public String getSignOfOperation() {
        return signOfOperation;
    }

    @Override
    public double calculate(ArrayList<Double> args) {
        double result;
        if (args.get(1) != 0) {
            result = args.get(0) / args.get(1);
        } else {
            System.out.println("Attempt to divide by zero");
            return 0;
        }
        return result;
    }

    @Override
    public boolean isFunction() {
        return false;
    }
}

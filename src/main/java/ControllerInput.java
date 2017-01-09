import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tania on 7/19/16.
 */

/**
 * The class {@code ControllerInput} tells user, what input
 * is expected from him, then read entry and checks its correctness.
 */
public class ControllerInput {
    private Operations allOperations = Operations.getInstance();
    CalculationOp operation;
    private String firstNumber;
    private String secondNumber;
    private String sign;
    private double result;
    private final Configuration configuration = new Configuration();
    ArrayList<Double> operands = new ArrayList<>();

    public void init() {
        Map<String, Object> helloMap = new HashMap<String, Object>();
        configuration.setClassForTemplateLoading(Main.class, "/");
        Spark.get("/", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                StringWriter writer = new StringWriter();
                try {
                    Template helloTEMP = configuration.getTemplate("calc.ftl");
                    helloMap.put("result", " lkl");
                    helloTEMP.process(helloMap, writer);
                    System.out.println(writer);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return writer;
            }
        });
        Spark.post("/formCalc", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                try {
                    firstNumber = request.queryParams("firstNumber");
                    secondNumber = request.queryParams("secondNumber");
                    sign = request.queryParams("operation");
                    System.out.println("==c  " + firstNumber + sign + secondNumber);
                } catch (Exception e) {
                }

                if (setOperands() && setOperation()) {
                    result = operation.calculate(operands);
                    operands.clear();
                    response.redirect("/result");
                } else {
                    response.redirect("/incorrect");
                }
                return null;
            }
        });
        Spark.get("/result", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                StringWriter writer = new StringWriter();
                try {
                    Template helloTEMP = configuration.getTemplate("calc.ftl");
                    helloMap.put("result", result);
                    helloTEMP.process(helloMap, writer);
                    System.out.println(writer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return writer;
            }
        });
        Spark.get("/incorrect", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                StringWriter writer = new StringWriter();
                try {
                    Template helloTEMP = configuration.getTemplate("calc.ftl");
                    helloMap.put("result", "Incorrect input! Try again");
                    helloTEMP.process(helloMap, writer);
                    System.out.println(writer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return writer;
            }
        });
    }

    private boolean setOperands() {
        try {
            operands.add(new Double(firstNumber));
            operands.add(new Double(secondNumber));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean setOperation() {
        boolean flag = true;
        while (flag) {
            for (String key : allOperations.operations.keySet()) {
                if (sign.equals(key)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println("SETSETSTE -false");

                return false;
            } else {
                break;
            }
        }
        operation = allOperations.operations.get(sign);
        return true;
    }
}
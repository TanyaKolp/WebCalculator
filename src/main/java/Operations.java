import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tania on 7/18/16.
 */

/**
 * The class {@code Operations} is used to store available operations
 * as HashMap, where key is a sign of operation (as a string), that users will enter, and
 * value is an object of a class, that contains methods for performing
 * this numeric operation or function.
 * <p>
 * Class allows you to create only one instance.
 */
public class Operations {
    public static Operations instance = null;

    private Operations() {
    }

    public static Operations getInstance() {
        if (instance == null) {
            instance = new Operations();
            instance.fillMap();
        }
        return instance;
    }

    public Map<String, CalculationOp> operations = new HashMap<String , CalculationOp>();

    /**
     * Method put standard operations in the field "operations" and
     * read the file "NewOperations.txt". If the file is not empty, method
     * read full class names line by line and create objects of these classes.
     * Using method "getSignOfOperation", method put these objects in the field "operations"
     * as previously described.
     */
    public void fillMap() {
        operations.put("+", new Add());
        operations.put("-", new Subtraction());
        operations.put("*", new Mult());
        operations.put("/", new Division());
        try {
            File file = new File("NewOperations.txt");
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = br.readLine()) != null) {
                Class<?> clazz;
                try {
                    clazz = Class.forName(line);
                    Constructor<?> constructor = clazz.getConstructor();
                    Object newOperation = constructor.newInstance();
                    Method method = clazz.getMethod("getSignOfOperation", new Class<?>[0]);
                    String op = (String) method.invoke(newOperation, new Object[0]);
                    operations.put(op, (CalculationOp) newOperation);

                } catch (ClassCastException e) {
                    System.out.println(e.toString());
                } catch (ClassNotFoundException e) {
                    System.out.println("Class was not found");
                } catch (NoSuchMethodException e) {
                    System.out.println("Method or constructor was not found");
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("File was not found");
        }

    }

}

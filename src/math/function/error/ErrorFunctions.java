package math.function.error;

import java.util.ArrayList;

public class ErrorFunctions {

    public static ErrorFunction Square = new SquareError();
    public static ErrorFunction Difference = new DifferenceError();
    public static ErrorFunction SquareDifference = new SquareDifferenceError();
    public static ErrorFunction Root = new RootError();

    private static final ArrayList<ErrorFunction> functions = new ArrayList<>();

    public static void register(ErrorFunction func) {
        if(!functions.contains(func)) {
            functions.add(func);
        }
    }

    public static ErrorFunction get(String name) {
        for(ErrorFunction func: functions) {
            if(name.equals(func.getName())) {
                return func;
            }
        }
        return null;
    }

}

package math.function.error;

import java.util.ArrayList;
import java.util.Arrays;

public class ErrorFunctions {

    public static ErrorFunction Square = new SquareError();
    public static ErrorFunction Difference = new DifferenceError();
    public static ErrorFunction SquareDifference = new SquareDifferenceError();

    private static final ArrayList<ErrorFunction> functions = new ArrayList<>(Arrays.asList(
            Square,
            Difference,
            SquareDifference
    ));

    public static ErrorFunction get(String name) {
        for(ErrorFunction func: functions) {
            if(name.equals(func.getName())) {
                return func;
            }
        }
        return null;
    }

}

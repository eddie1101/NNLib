package math.function.error;

import math.function.TwoParameterFunction;

import java.util.ArrayList;
import java.util.Arrays;

public class ErrorFunctions {

    public static ErrorFunction Square = new SquareError();
    public static ErrorFunction Difference = new DifferenceError();

    private static final ArrayList<ErrorFunction> functions = new ArrayList<>(Arrays.asList(
            Square,
            Difference
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

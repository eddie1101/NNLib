package math.function.error;

import math.function.TwoParameterFunction;

public class SquareError implements TwoParameterFunction {

    @Override
    public double compute(double in1, double in2) {
        return Math.sqrt((in1 * in1) - (in2 * in2));
    }

}

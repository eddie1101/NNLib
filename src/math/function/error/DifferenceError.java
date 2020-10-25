package math.function.error;

import math.function.TwoParameterFunction;

public class DifferenceError implements TwoParameterFunction {
    @Override
    public double compute(double in1, double in2) {
        return in1 - in2;
    }
}

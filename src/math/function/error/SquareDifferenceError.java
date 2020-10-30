package math.function.error;

public class SquareDifferenceError extends ErrorFunction {

    public SquareDifferenceError() {
        super(
                (in1, in2) -> Math.pow(in1 - in2, 2),
                "SquareDifference"
        );
    }

}

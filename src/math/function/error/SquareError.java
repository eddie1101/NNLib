package math.function.error;

public class SquareError extends ErrorFunction {

    public SquareError() {
        super(
                (in1, in2) -> Math.sqrt((in1 * in1) - (in2 * in2)),
                "Square"
            );
    }

}

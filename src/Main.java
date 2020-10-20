import function.activation.IActivationFunction;
import function.error.IErrorFunction;
import math.Matrix;
import network.Network;
import network.Perceptron;

public class Main {
    public static void main(String[] args) {

        Matrix m = new Matrix(2, 2);
        m.randomInitialization(-10, 10);

        Matrix o = new Matrix(2, 2);
        o.randomInitialization(0, 2);

        System.out.println("m:\n" + m);
        System.out.println("o:\n" + o);

        m.add(o);

        System.out.println("----------------------\n" + m);


        Double[][] mData = {
                {5d, -3d},
                {-1d, 7d},
                {-6d, 3d}
        };

        Double[][] oData = {
                {-2d, 2d, 1d},
                {1d, 3d, 2d}
        };

        m = new Matrix(2, 3, mData);
        o = new Matrix(3, 2, oData);

        System.out.println("\n\n\n");
        System.out.println("m:\n" + m);
        System.out.println("o:\n" + o);

        m.mult(o);

        System.out.println("----------------------\n" + m);


    }

}

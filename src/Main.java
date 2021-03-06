import math.function.activation.ActivationFunctions;
import math.function.error.ErrorFunctions;
import math.matrix.Matrix;
import neural_network.NeuralNetwork;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        neuralNetworkDebug();
//        matrixDebug();
    }

    public static void neuralNetworkDebug() {
        NeuralNetwork neuralNetwork = new NeuralNetwork(
                2,
                1,
                2,
                1)
                .setWeightInitBounds(0, 1)
                .setLearningRate(0.1)
                .setActivation(ActivationFunctions.ReLU)
                .setError(ErrorFunctions.Difference);

        Double[] inputs = new Double[2];
        for(int i = 0; i < inputs.length; i++) {
            inputs[i] = ThreadLocalRandom.current().nextGaussian();
        }

        Double[] targets = new Double[1];
        for(int i = 0; i < targets.length; i++) {
            targets[i] = ThreadLocalRandom.current().nextGaussian();
        }

        System.out.println(neuralNetwork);
        neuralNetwork.train(inputs, targets);

    }

    public static void matrixDebug() {
        Matrix m = new Matrix(2, 2);
        m.randomInitialization(-10, 10);

        Matrix o = new Matrix(2, 2);
        o.randomInitialization(0, 2);

        System.out.println("m:\n" + m);
        System.out.println("o:\n" + o);

        m.add(o);

        System.out.println("-------Addition-------\n" + m);


        Double[][] mData = {
                {5d, -3d, 4d},
                {-1d, 7d, 3d},
                {-6d, 3d, -2d},
                {6d, -2d, -3d}
        };

        Double[][] oData = {
                {-2d, 2d, 1d, 9d},
                {1d, 3d, 2d, -6d},
                {2d, -5d, -2d, 10d},
                {-4d, 4d, 2d, -5d}
        };

        m = new Matrix(3, 4, mData);
        o = new Matrix(4, 4, oData);

        System.out.println("\n\n\n");
        System.out.println("m:\n" + m);
        System.out.println("o:\n" + o);

        m.mult(o);

        System.out.println("----Multiplication----\n" + m);

        m.transpose();

        System.out.println("----Transposition----\n" + m);

        m.map(in -> in / 2);

        System.out.println("----Mapping----\n" + m);

        Double[] arr = m.toArray();

        System.out.println("----ToArray----\n");
        for (Double aDouble : arr) System.out.println(aDouble);
    }


}

import math.function.activation.ActivationFunctions;
import math.function.error.ErrorFunction;
import math.function.error.ErrorFunctions;
import math.matrix.Matrix;
import neural_network.NeuralNetwork;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        neuralNetworkTestWeightInitialization();
//        neuralNetworkDebug();
//        matrixDebug();
    }

    public static void neuralNetworkTestWeightInitialization() {
        NeuralNetwork nnSmallInputs = new NeuralNetwork(
                5,
                5,
                10,
                3
                )
                .initializeWeights()
                .setLearningRate(0.1)
                .setActivation(ActivationFunctions.Sigmoid)
                .setError(ErrorFunctions.SquareDifference);

        NeuralNetwork nnBigInputs = new NeuralNetwork(
                500,
                500,
                100,
                3
                )
                .initializeWeights()
                .setLearningRate(0.1)
                .setActivation(ActivationFunctions.Sigmoid)
                .setError(ErrorFunctions.SquareDifference);

        Double[][] bigData = new Double[10000][500];
        Double[][] smallData = new Double[10000][5];

        Double[] dataTarget = new Double[] {1d, 1d, 1d};

        for(int i = 0; i < bigData.length; i++) {
            for(int n = 0; n < bigData[0].length; n++) {
                bigData[i][n] = ThreadLocalRandom.current().nextGaussian();
            }
        }

        for(int i = 0; i < smallData.length; i++) {
            for(int n = 0; n < smallData[0].length; n++) {
                smallData[i][n] = ThreadLocalRandom.current().nextGaussian();
            }
        }

        System.out.println(Arrays.toString(nnBigInputs.forwardPropagation(bigData[0])));
//        System.out.println(Arrays.toString(nnSmallInputs.forwardPropagation(smallData[0])));


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

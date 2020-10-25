package demo.xor;

import math.function.activation.ActivationFunction;
import math.function.activation.ActivationFunctions;
import math.function.error.ErrorFunction;
import math.function.error.ErrorFunctions;
import neural_network.NeuralNetwork;

import java.util.concurrent.ThreadLocalRandom;

public class XORDemo {

    public static String modelPath = "./data/models/XORDemoExample.json";
    public static String testPath1 = "./data/models/XORDemoTest1.json";
    public static String testPath2 = "./data/models/XORDemoTest2.json";

    private static LabledBoolean[] data;
    private static NeuralNetwork neuralNetwork;

    private static ActivationFunction activate = ActivationFunctions.Sigmoid;
    private static ActivationFunction outputActivate = ActivationFunctions.Sigmoid;
    private static ErrorFunction error = ErrorFunctions.Difference;

    private static final double learningRate = 0.1;

    private static final int trainingIterations = 10000;

    public static void main(String[] args) {

        initDemo();

        System.out.println("Before training: \nEvaluation | Label");
        evaluate();

        neuralNetwork.saveTo(testPath1);

        System.out.println("Training...\n");
        train();

        neuralNetwork.saveTo(testPath2);

        System.out.println("After training: \nEvaluation | Label");
        evaluate();

    }

    private static void initDemo() {
        neuralNetwork = new NeuralNetwork(2, 1, 2, 1)
                .setActivation(activate)
                .setOutputActivation(outputActivate)
                .setError(error)
                .setLearningRate(learningRate)
                .setWeightInitBounds(0, 1);

        data = new LabledBoolean[4];
        data[0] = new LabledBoolean(0, 0);
        data[1] = new LabledBoolean(1, 1);
        data[2] = new LabledBoolean(0, 1);
        data[3] = new LabledBoolean(1, 0);

    }

    private static void train() {
        for(int i = 0; i < trainingIterations; i++) {
            LabledBoolean bool = chooseRandom();
            Double[] inputs = {bool.x, bool.y};
            Double[] target = bool.label;
            neuralNetwork.train(inputs, target);
        }
    }

    private static void evaluate() {

        for(int i = 0; i < data.length; i++) {
            Double[] input = {data[i].x, data[i].y};
            double target = data[i].label[0];
            double evaluation = neuralNetwork.forwardPropagation(input)[0];

            System.out.println(String.format("%10.8f | %4.1f", evaluation, target));
        }
        System.out.println();
    }

    private static LabledBoolean chooseRandom() {
        int pick = ThreadLocalRandom.current().nextInt(0, 4);
        return data[pick];
    }

}

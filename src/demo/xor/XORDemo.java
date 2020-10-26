package demo.xor;

import math.function.activation.ActivationFunction;
import math.function.activation.ActivationFunctions;
import math.function.error.ErrorFunction;
import math.function.error.ErrorFunctions;
import neural_network.NeuralNetwork;

import java.util.concurrent.ThreadLocalRandom;

public class XORDemo {

    private static LabledBoolean[] data;
    private static NeuralNetwork neuralNetwork;

    private static ActivationFunction activate = ActivationFunctions.Sigmoid;
    private static ActivationFunction outputActivate = ActivationFunctions.Sigmoid;
    private static ErrorFunction error = ErrorFunctions.Square;

    private static final double learningRate = 0.1;

    private static final int trainingIterations = 1000000;

    private static final String path = "./data/models/XORDemo";
    private static final String sigmoidModelPath = path + "Sigmoid.json";
    private static final String ReLUModelPath = path + "ReLU.json";
    private static final String sigmoidBooleanCoercionModelPath = path + "SigmoidBooleanCoercion.json";

    public static void main(String[] args) {

        initDemo();

        System.out.println("NEW Neural Net with random weight initializations");
        System.out.println("Before training:");
        evaluate();

        System.out.println("Training...\n");
        train();

        System.out.println("After training:");
        evaluate();

        System.out.println("\nDemonstrating pre-trained models:\n");
        demoTrainedModels();

    }

    private static void initDemo() {
        neuralNetwork = new NeuralNetwork(2, 1, 4, 1)
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

        demoModel(neuralNetwork);
        System.out.println();
    }

    private static LabledBoolean chooseRandom() {
        int pick = ThreadLocalRandom.current().nextInt(0, 4);
        return data[pick];
    }

    private static void demoTrainedModels() {

        NeuralNetwork nnSigmoid = NeuralNetwork.loadFrom(sigmoidModelPath);
        System.out.println("Sigmoid model:");
        demoModel(nnSigmoid);

        System.out.println();

        NeuralNetwork nnReLU = NeuralNetwork.loadFrom(ReLUModelPath);
        System.out.println("ReLU model:");
        demoModel(nnReLU);

        System.out.println();

        NeuralNetwork nnSigmoidBooleanCoercion = NeuralNetwork.loadFrom(sigmoidBooleanCoercionModelPath);
        System.out.println("SigmoidBooleanCoercion model:");
        demoModel(nnSigmoidBooleanCoercion);


    }

    private static void demoModel(NeuralNetwork nn) {
        System.out.println("Evaluation | Label");

        for (LabledBoolean datum : data) {
            Double[] input = {datum.x, datum.y};
            double target = datum.label[0];
            double evaluation = nn.forwardPropagation(input)[0];

            System.out.println(String.format("%10.8f | %4.1f", evaluation, target));
        }
    }

}

package demo.xor;

import math.function.activation.ActivationFunction;
import math.function.activation.ActivationFunctions;
import math.function.error.ErrorFunction;
import math.function.error.ErrorFunctions;
import neural_network.NeuralNetwork;

public class XORDemo {

    public static String modelPath = "./data/models/XORDemoExample.json";

    private static LabledBoolean[] trainingData;
    private static LabledBoolean[] testData;
    private static NeuralNetwork neuralNetwork;

    private static ActivationFunction activate = ActivationFunctions.BoolCoerce;
    private static ErrorFunction error = ErrorFunctions.Difference;

    private static final double learningRate = 0.0001;

    private static final int numTrainingPoints = 10000;
    private static final int numTestPoints = 1000;
    private static final int numPointsToShow = 20;

    public static void main(String[] args) {

        initDemo();

        System.out.println("Before training: \nEvaluation | Label");
        evaluate();

        System.out.println("Training...\n");
        train();

        System.out.println("After training: \nEvaluation | Label");
        evaluate();

    }

    private static void initDemo() {
        neuralNetwork = new NeuralNetwork(2, 1, 2, 1)
                .setActivation(activate)
                .setError(error)
                .setLearningRate(learningRate)
                .setWeightInitBounds(0, 1);

        trainingData = new LabledBoolean[numTrainingPoints];
        testData = new LabledBoolean[numTestPoints];

        for (int i = 0; i < trainingData.length; i++) {
            trainingData[i] = new LabledBoolean();
        }

        for (int i = 0; i < testData.length; i++) {
            testData[i] = new LabledBoolean();
        }
    }

    private static void train() {
        for(LabledBoolean data: trainingData) {
            Double[] inputs = {data.x, data.y};
            Double[] targets = data.label;
            neuralNetwork.train(inputs, targets);
        }
    }

    private static void evaluate() {
        int correct = 0;
        boolean displayLimit = false;
        for (int i = 0; i < numTestPoints; i++) {
            LabledBoolean data = testData[i];
            Double[] inputs = {data.x, data.y};

            Double[] output = neuralNetwork.forwardPropagation(inputs);
            double evaluation = output[0]; //Rigged since this demo only has one output
            double target = data.label[0];

            if (evaluation == target) {
                correct++;
            }

            if((i <= numPointsToShow / 2 || i >= numTestPoints - numPointsToShow / 2)) {
                System.out.println(String.format("%10.1f | %4.1f", evaluation, target));
            }else if(!displayLimit){
                System.out.println(String.format("%10s...", ""));
                displayLimit = true;
            }
        }
        System.out.println(correct + "/" + numTestPoints + String.format(": %.2f%%\n", ((float)correct / (float)numTestPoints) * 100));
    }

}

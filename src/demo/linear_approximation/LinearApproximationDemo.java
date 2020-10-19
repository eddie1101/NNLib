package demo.linear_approximation;

import network.Perceptron;

public class LinearApproximationDemo {

    private static LabledPoint[] trainingData;
    private static LabledPoint[] testData;
    private static Perceptron perceptron;

    private static final double bias = 0.1;
    private static final double learningRate = 0.01;

    private static final int numTrainingPoints = 1000;
    private static final int numTestPoints = 10000;
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
        perceptron = new Perceptron(2,
                ((double in) -> in < 0 ? -1 : 1),
                ((double target, double mark) -> target - mark), bias, learningRate);

        trainingData = new LabledPoint[numTrainingPoints];
        testData = new LabledPoint[numTestPoints];

        for (int i = 0; i < numTrainingPoints; i++) {
            trainingData[i] = new LabledPoint();
        }

        for(int i = 0; i < numTestPoints; i++) {
            testData[i] = new LabledPoint();
        }
    }

    private static void evaluate() {
        int correct = 0;
        boolean displayLimit = false;
        for (int i = 0; i < numTestPoints; i++) {
            LabledPoint point = testData[i];
            Double[] inputs = new Double[2];
            inputs[0] = point.x;
            inputs[1] = point.y;

            double evaluation = perceptron.evaluate(inputs);

            if (evaluation == point.label) {
                correct++;
            }

            if((i <= numPointsToShow / 2 || i >= numTestPoints - numPointsToShow / 2)) {
                System.out.println(String.format("%10.1f | %4.1f", evaluation, point.label));
            }else if(!displayLimit){
                System.out.println(String.format("%10s...", ""));
                displayLimit = true;
            }
        }
        System.out.println(correct + "/" + numTestPoints + String.format(": %.2f%%\n", ((float)correct / (float)numTestPoints) * 100));
    }

    private static void train() {
        for (LabledPoint point : trainingData) {
            Double[] inputs = new Double[2];
            inputs[0] = point.x;
            inputs[1] = point.y;

            perceptron.train(inputs, point.label);
        }
    }

}

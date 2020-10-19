package network;

import java.util.ArrayList;
import java.util.Arrays;

public class Layer {

    ArrayList<Node> nodes = new ArrayList<>();

    String evaluationString = "Not yet evaluated";

    public Layer(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public Layer() {}

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public void setNodes(Node... nodes) {
        this.nodes = new ArrayList<>(Arrays.asList(nodes));
    }

    public void addNode(Node... node) {
        this.nodes.addAll(Arrays.asList(node));
    }


    public ArrayList<Double> evaluate(ArrayList<Double> inputs) {
        ArrayList<Double> outputs = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        int i = 0;
        for(Node node: nodes) {
            double nodeOutput = node.output(inputs);
            outputs.add(nodeOutput);
            builder.append("network.Node " + i++ + ": " + nodeOutput + "\n");
        }

        evaluationString = builder.toString();

        return outputs;
    }

    @Override
    public String toString() {
        return evaluationString;
    }

}

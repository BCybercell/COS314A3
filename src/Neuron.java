public class Neuron {
    public Neuron(int numInputs, int numOutputs){
        input = new double [1][numInputs];
        output = new double[1][numOutputs];
        System.out.println(output[0][0]);
    }
    public void setInputs(double [][] inputs){
        input =inputs;
    }
    public void process(UtilsA utils, double [][] weights){
        output = utils.multiplyMatrices(input, weights, input.length, input[0].length, weights[0].length);
    }
    public double getOutput(UtilsA utils, double[][] w){
        return utils.reLU(utils.negativeLogLikelihood(w, output));
    }
    private double [][] input;
    private double [][] output;

}

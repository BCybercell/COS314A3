public class Neuron {
    public Neuron(double aInput){
        input = aInput;
    }
    public void setInputs(double inputs){
        input =inputs;
    }
//    public void process(UtilsA utils, double [][] weights){
////        output = utils.multiplyMatrices(input, weights, input.length, input[0].length, weights[0].length);
//    }


    public double getInput() {
        return input;
    }

    public double getOutput(UtilsA utils, double[][] w){
        return output;
    }
    private double input;
    private double output;

}

public class Image {
    public Image(double[] aInput, double aOutput){
        input = new double[1][aInput.length];
        input[0] =aInput;
        output =aOutput;
    }

    public double getOutput() {
        return output;
    }

    public double[][] getInput() {
        return input;
    }

    private double [][] input;
    private double output;
}

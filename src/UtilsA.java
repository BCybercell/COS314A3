import java.lang.Math;

public class UtilsA {

    public UtilsA(){}

    public double reLU(double x){
        if (x > 0)
            return x;
        else return 0;

    }

    //TODO
    public double negativeLogLikelyhood ()
    {
        return 0.0;
    }

    public double sigmoid(double x) {
        return (1/( 1 + Math.exp(-1*x)));
    }

    /*
     *   probabilities for a vector (array of doubles)
     *   @params
     *       netOk: double (The net value of Ok)
     *   return:
     *       Array of probabilities for each element
     * */
    public double [] softMax(double[] netOk){
        double eValues [] = new double[netOk.length];
        double totalE = 0.0;
        for (int i = 0; i<netOk.length;i++){
            eValues[i]= Math.exp(netOk[i]);
            totalE += eValues[i];
        }
        for (int i = 0; i < eValues.length; i++)
        {
            eValues[i] = eValues[i]/totalE;
        }
        return eValues;
    }

    /*
     *   probability for a individual element given a vector (array of doubles)
     *   @params
     *       netOk: double (The net value of Ok)
     *       index: int (the index mathematically [starts at 1])
     *   return:
     *       individual probability of element at index
     * */
    public double softMax(double[] netOk,int index){
        double [] temp = softMax(netOk);
        if ((index > 1) &&(index <= temp.length))
            return temp[index-1];
        else return -1; //TODO temp error for now, might throw exception later
    }


    public double mbGradientDecent(int input,int output){
        return mbGradientDecent(input, output, 50, 0.0001);
    }

    public double mbGradientDecent(int input,int output,int batchSize){
        return mbGradientDecent(input, output, batchSize, 0.0001);
    }

    public double mbGradientDecent(int input,int output,double learningRate){
        return mbGradientDecent(input, output, 50, learningRate);
    }

    //TODO
    public double mbGradientDecent(int input,int output,int batchSize,double learningRate){




        return 0.0;

    }
}

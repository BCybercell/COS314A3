import java.lang.Math;

public class UtilsA {

    public UtilsA(){}

    public double reLU(double x){
        if (x > 0)
            return x;
        else return 0;

    }

    public double negativeLogLikelihood (double x)
    {
        return -1*(Math.log(x));
    }

    public double negativeLogLikelihood(double [][] y, double[][] o) {
        int d;
        int k;
        int D;
        int K;
        double total = 0.0;
        if (o.length < y.length) { //num colomns
            K = o.length;
        } else {
            K = y.length;
        }
        if (o[0].length < y[0].length) { //num rows
            D = o[0].length;
        } else {
            D = y[0].length;
        }
        for (d = 0; d < D; d++) {
            for (k = 0; k < K; k++) {
                total += y[k][d] * Math.log(o[k][d]);
            }
        }
        return total * -1;
    }

    public double sigmoid(double x) {
        return (1/( 1 + Math.exp(-1*x)));
    }


    public double sigmoidDer(double x){
        return x * (1-x);
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



    public double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix, int r1, int c1, int c2) {
        double[][] product = new double[r1][c2];
        for(int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < c1; k++) {
                    product[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }

        return product;
    }

    public void displayProduct(double[][] product) {
        for(double[] row : product) {
            for (double column : row) {
                System.out.print(column + "    ");
            }
            System.out.println();
        }
    }
}

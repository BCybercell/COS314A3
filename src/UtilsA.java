import java.lang.Math;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class UtilsA {

    public UtilsA(){}

    public double reLU(double x){
        if (x > 0)
            return x;
        else return 0;

    }

    public double negativeLogLikelihood (double x)
    {
        if (x<0) return Math.log(-x);   //TODO temp
        return -1*(Math.log(x));
    }

    public double negativeLogLikelihood(double [][] y, double[][] o) { // y = expected output, o = output
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
        double [] eValues  = new double[netOk.length];
        double totalE = 0.0;
        for (int i = 0; i<netOk.length;i++){
            eValues[i]= Math.exp(netOk[i]/1000); //temp added
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


//    public double mbGradientDecent(int input,int output){
//        return mbGradientDecent(input, output, 50, 0.0001);
//    }
//
//    public double mbGradientDecent(int input,int output,int batchSize){
//        return mbGradientDecent(input, output, batchSize, 0.0001);
//    }
//
//    public double mbGradientDecent(int input,int output,double learningRate){
//        return mbGradientDecent(input, output, 50, learningRate);
//    }

    //TODO
    public void mbGradientDecent(Layer inputLayer, Layer hiddenLayer1, Layer hiddenLayer2,
                                   Layer outputLayer, List<Image> images, UtilsA utils,
                                   int batchSize, double learningRate){
        double [][] outputs = new double[1][10];
        double [][] expected = new double[1][10];
        int count = 0;
        int stopping =0;
        for (Image image:images
             ) {
            inputLayer.assignInputsToNeurons(image.getInput());
            inputLayer.processLayer(utils, hiddenLayer1,0);
            hiddenLayer1.processLayer(utils, hiddenLayer2, 0);
            hiddenLayer2.processLayer(utils, outputLayer, 1);
            double [][] tempOut = outputLayer.getInputs();
            for (int i = 0; i <10 ; i++) {
                outputs[0][i] += tempOut[0][i];
            }
            Long l = Math.round(image.getOutput());
            expected[0][l.intValue()]++;
            count ++;

            if (count==batchSize){
                count =0;
//                double [][] temp = new double[1][10];
//                for (int i = 0; i <10 ; i++) {
//                    temp[0][i] = utils.negativeLogLikelihood(expected[0][i]- outputs[0][i]);
//                }
//                utils.displayProduct(temp);
                double gradientOut = utils.negativeLogLikelihood(expected,outputs);
                System.out.println(gradientOut);
                outputLayer.updateWeights(hiddenLayer2, learningRate, gradientOut);

                double gradient = 0.0;
                double [][] weights = hiddenLayer2.getWeights();
                for (int i = 0; i < weights.length; i++) {
                    for (int j = 0; j < weights[0].length; j++) {
                        gradient += weights[i][j] * gradientOut;
                    }
                }
                gradientOut = utils.negativeLogLikelihood(gradient);
               // hiddenLayer2.updateWeights(hiddenLayer1, learningRate, gradientOut);


                gradient = 0.0;
                weights = hiddenLayer1.getWeights();
                for (int i = 0; i < weights.length; i++) {
                    for (int j = 0; j < weights[0].length; j++) {
                        gradient += weights[i][j] * gradientOut;
                    }
                }
                gradientOut = utils.negativeLogLikelihood(gradient);
               // hiddenLayer1.updateWeights(inputLayer, learningRate, gradientOut);

                utils.displayProduct(outputs);
                utils.displayProduct(expected);
//                utils.displayProduct(outputLayer.getWeights());
                outputs = new double[1][10];
                expected = new double[1][10];

                System.out.println("================================");
                stopping++;
                if (stopping==10){
                    return;
                }
            }
        }



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

    public List<Image> readCVS(String fileName){
        List<Image> images = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            // read the first line from the text file
            String line = br.readLine();
            line = br.readLine();
            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(",");
                int len = attributes.length;
                double [] input = new double[len];
                double output =  Double.parseDouble(attributes[0]);
                for (int i = 1; i <len ; i++) {
                    input[i-1] = Double.parseDouble(attributes[i]);
                }

                Image image = new Image(input, output);

                // adding book into ArrayList
                images.add(image);

                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return images;
    }

    public void assignInputsToLayer(Layer layer, Image image){
        layer.assignInputsToNeurons(image.getInput());
    }

    public void assignRandomWeights(double[][] weights, int rows, int col){
        for (int i = 0; i <rows ; i++) {
            for (int j = 0; j <col ; j++) {
                weights[i][j] = Math.random();
            }
        }
    }
}

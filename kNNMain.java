import java.util.List;
import java.io.FileNotFoundException;
import java.util.Arrays;



public class kNNMain{

    public static void main(String... args) throws FileNotFoundException{

        // TASK 1: Use command line arguments to point DataSet.readDataSet method to
        // the desired file. Choose a given DataPoint, and print its features and label
        if (args.length < 1) {
            System.out.println("Enter a filename for a dataset");
            System.exit(1);
        }
        
        List<DataPoint> dataset = DataSet.readDataSet(args[0]);
        DataPoint myPoint = dataset.get(0);
        double[] features = myPoint.getX();
        String label = myPoint.getLabel();
        System.out.print("Features: ");
        System.out.println(Arrays.toString(features));
        System.out.println("Label: " + label);
            
        //TASK 2:Use the DataSet class to split the fullDataSet into Training and Held Out Test Dataset
        List<DataPoint> testSet = DataSet.getTestSet(dataset, 0.5);
        List<DataPoint> trainingSet = DataSet.getTrainingSet(dataset, 0.5);

        // TASK 3: Use the DataSet class methods to plot the 2D data (binary and multi-class)


        // TASK 4: write a new method in DataSet.java which takes as arguments to DataPoint objects,
        // and returns the Euclidean distance between those two points (as a double)
        
        // TASK 5: Use the KNNClassifier class to determine the k nearest neighbors to a given DataPoint,
        // and make it print a predicted target label
        KNNClassifier classifier = new KNNClassifier(5);
        String predictedLabel = classifier.predict(dataset, myPoint);
        System.out.println("Predicted Label: " + predictedLabel);

        // TASK 6: loop over the datapoints in the held out test set, and make predictions for Each
        // point based on nearest neighbors in training set. Calculate accuracy of model.
        int correctLabels = 0;
        for(DataPoint p : testSet){
            String prediction = classifier.predict(dataset, p);
            if(prediction.equals(p.getLabel())) {
                correctLabels++;
            }
        }
        System.out.println("Accuracy: " + ((double)correctLabels / testSet.size()) * 100);

	double expectedMalignantPercentage = ((double)getLabelCount(dataset, "malignant") / dataset.size()) * 100;

	System.out.println("Expected malignant percentage: " + expectedMalignantPercentage);
	runClassification1000(dataset, 3);
	runClassification1000(dataset, 4);
	runClassification1000(dataset, 5);
	runClassification1000(dataset, 10);
	runClassification1000(dataset, 30);
    }

    public static int getLabelCount(List<DataPoint> dataset, String label) {
    	int num = 0;
    	for(int i = 0; i < dataset.size(); i++) {
    	    if(dataset.get(i).getLabel().equals(label)) {
    		num++;
    	    }
    	}
    	return num;
    }

    public static void runClassification1000(List<DataPoint> dataset, int k) {
	double[][] results = new double[1000][2];
	for(int i = 0; i < 1000; i++) {
	    results[i] = runClassification(dataset, k);
	}

	double[] malignants = new double[1000];
	for(int i = 0; i < 1000; i++) {
	    malignants[i] = results[i][1];
	}
	double[] recalls = new double[1000];
	for(int i = 0; i < 1000; i++) {
	    recalls[i] = results[i][2];
	}
	double[] precisions = new double[1000];
	for(int i = 0; i < 1000; i++) {
	    precisions[i] = results[i][3];
	}

	System.out.println("=== " + k + " ===");
	System.out.println("Mean malignant percentage: " + mean(malignants));
	System.out.println("Stddev malignant percentage: " + standardDeviation(malignants));
	System.out.println("Recall: " + mean(recalls));
	System.out.println("Precision: " + mean(precisions));
    }

    public static double[] runClassification(List<DataPoint> dataset, int k) {
	double[] result = new double[4];
	List<DataPoint> testSet = DataSet.getTestSet(dataset, 0.3);
        List<DataPoint> trainingSet = DataSet.getTrainingSet(dataset, 0.7);

        KNNClassifier classifier = new KNNClassifier(k);
	int numTruePositive = 0;
	int numTrueNegative = 0;
        int correctLabels = 0;
	int numMalignant = 0;
	int numFalsePositives = 0;
	int numFalseNegatives = 0;
        for(DataPoint p : testSet){
            String prediction = classifier.predict(dataset, p);
            if(prediction.equals(p.getLabel())) {
		if(prediction.equals("malignant")) {
		    numTruePositive++;
		} else {
		    numTrueNegative++;
		}
                correctLabels++;
            }
	    if(prediction.equals("malignant")) {
		numMalignant++;
	    }
	    if(p.getLabel().equals("benign") && prediction.equals("malignant")) {
		numFalsePositives++;
	    }
	    if(p.getLabel().equals("malignant") && prediction.equals("benign")) {
		numFalseNegatives++;
	    }
        }
	double recall = ((double)numTruePositive / (numTruePositive + numFalseNegatives)) * 100;
	double precision = ((double)numTruePositive / (numTruePositive + numFalsePositives)) * 100;
	result[0] = ((double)correctLabels / testSet.size()) * 100;
	result[1] = ((double)numMalignant / testSet.size()) * 100;
	result[2] = recall;
	result[3] = precision;
	return result;
    }

    public static double mean(double[] arr){
        /*
          Method that takes as an argument an array of doubles
          Returns: average of the elements of array, as a double
        */
        double sum = 0.0;

        for (double a : arr){
            sum += a;
        }
        return (double)sum/arr.length;
    }

    public static double standardDeviation(double[] arr){
        /*
          Method that takes as an argument an array of doubles
          Returns: standard deviation of the elements of array, as a double
          Dependencies: requires the *mean* method written above
        */
        double avg = mean(arr);
        double sum = 0.0;
        for (double a : arr){
            sum += Math.pow(a-avg,2);
        }
        return (double)sum/arr.length;
    }
}

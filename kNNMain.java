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
    }
}

# Error Analysis Report
## Dawson College 360-420-DW
### Jessica Pizzuco
### Soirlita Lam

### Confidence in the model
Although the accuracy doesn't vary by more than 2-3%, there is a difference in
accuracy because of the lines 140-150 in `DataSet.java`. Here, there is 
a shuffling that occurs between all the values in the test set with each run.
Therefore, depending on which data has been shuffled into the test set, the
accuracy will change.

### Types of errors
Classification errors cause a loss in accuracy and credibility of a model.
A false positive is when something is said to be true but it is actually false. 
In the context of medical diagnosis and `breastCancer.csv`, it would mean determining
that a tumor is malignant when it is actually benign.  
In the same way, a false negative is when something is said to be false but it is actually true.
In terms of `breastCancer.csv`, this would mean predicting that a tumor is benign when it is actually malignant.

**Recall** and **Precision** are different because **Recall** determines "how many relevant items are selected"
and **Precision** determines "how many selected elements are relevant." They are therefore calculated in different ways as well.

**Recall** is the evaluation of how many true positives there were over the number of true positives and false negatives that
were found. This means that it represents the ratio of true malignant tumors in the dataset over the number of 
tumors that were predicted to be malignant in the dataset by the classifier. 

**Precision** is the number of true positives over the number of true positives and false positives.
This means that it is the ratio of tumors that were predicted by the classifier to be malignant that were *actually* malignant. 

A sensible baseline for these would be around 100% in both cases because ideally, 
the recall would show that the number of malignant tumors that were found through the classifier is exactly,
or within reasonale range, of the true number of malignant tumors in the dataset. 
Similarly, it would be ideal to have the precision show that the number of malignant tumors found with the 
classifier were indeed malginant, and not false positives.
 

By increasing **hyperparameter** *k*, the K-Nearest Neighbour algorithm has a smaller chance of getting
the right answer because it has more information to compare itself to, meaning that there are more options available and it is unlikely that
the correct answer (malignant vs. benign) will be chosen. On the other hand, if *k* is small,
it is likely that the result of the algorithm will be correct. It will be basing its decision off of fewer 
points around it, therefore likely going toward the correct conclusion or result. Fewer points in the circle means that there
is a lower chance of having outliers nearby, which might lead to a bad prediction. 

For example, if *k* is small and there is only one or two points surrounding it, both being benign, the result is likely
to be benign as well and therefore the correct result will be predicted. However, if *k* is large, it is likely that there will be 
several malignants and several benigns surrounding it, meaning that it won't know which is correct/which to choose. 
This can be shown by running the K-Nearest Neighbour algorithm with both large and small values of *k*,
and printing the result of each 1000 run, showing the precision and recall as *k* increases. 
It appears that both precision and recall decrease as values of *k* increase.  

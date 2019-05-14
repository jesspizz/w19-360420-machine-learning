# Error Analysis Report
## Dawson College 360-420-DW
### Jessica Pizzuco
### Soirlita Lam

Although the accuracy doesn't varry by more than 2-3%, there is a difference in
accuracy because of the lines 140-150 in `DataSet.java`. Here, there is 
a shuffling that occurs between all the values in the test set with each run.
Therefore, depending on which data has been shuffled into the test set, the
accuracy will change.

Classification errors cause a loss in accuracy and credibility of a model.
A false positive is when something is said to be true but it is actually false. 
In the context of medical diagnosis and `breastCancer.csv`, it would mean determining
that a tumor is malignant when it is actually benign.  
In the same way, a false negative is when something is said to be false but it is actually true.
In terms of `breastCancer.csv`, this would mean finding a benign tumor instead of a malignant one.

**Recall** and **Precision** are different because **Recall** determines "how many relevant items are selected"
and **Precision** determines "how many selected elements are relevant." They are therefore calculated in different ways as well.


By changing **hyperparameter** *k*, the Nearest Neighbour algorithm has a greater chance of going toward 
the right answer because it has more information to base itself off of. On the other hand, if *k* is small,
it is likely that the result of the algorithm will not be correct. It will be basing its decision off of fewer 
points around it, therefore likely going toward the wrong conclusion or result. 

# Scientiﬁc Data Management SS 2017 
### Programming Assignment 1: K-means  
    Tokareva Axinya  
    Ibrahim Mahmoud Abdelmottaleb  
    Cherches Nicole  
    Hunner Markus  
## Task 1.1  
Implement the K-means algorithm in Java. Your solution should containthe following functionalities:  
  
__(20 points)__: A data generator for gaussian clusters.  
The generator should take as input the count of clustersK, the count of data  points N and the dimensionality D.  
  
__(20 points)__ Visualization for the depiction of the clustering results in 2D.  
The cluster assignment of thepoints should be depicted in different colours. It is not necessary to show the cluster boarders.  
For thevisualisation you could also use a third party tool (e.g. JFreeChart).  
    
Strategies for the initialisation:  
* Random partition of the data points in a cluster 
* Random points as initial cluster centers  
  
Strategies for the update:  
* Update each round (Lloyd)
* Immediately after assignment of each point (Mac Queen)

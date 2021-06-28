/*
KEYWORDS : Percolation, Monte Carlo Simulation, Union-Find
*/

---	PERCOLATION	---

We model the system as an n-by-n grid of sites. 
Each site is either blocked or open. 
Open sites are initially empty. 
A full site is an open site that can be connected to an open site 
in the top row via a chain of neighboring (left, right, up, down) open sites. 
If there is a full site in the bottom row, then we say that the system percolates.

---	The API and Data Structures	---

There are two classes in this project: Percolation, PercolationStats

Below API belongs to Percolation class:

public class Percolation {
    public Percolation(int N)            // create an N-by-N grid, all cells blocked
    public void open(int i, int j)       // open site (row i, column j), if not already open
    public boolean isOpen(int i, int j)  // check if site (row i, column j) is open
    public boolean isFull(int i, int j)  // check if site (row i, column j) is full
    public boolean percolates()          // check if does the system percolate
}

The API of Percolation class is very simple. All we need to do is to create the grid
and check if the system percolates. To implement it easily we create methods like
open(), isOpen(), isFull() they test if neighboring cells are open or full and 
open it if it's not open.

We use union-find data structure to keep track of connected cells. To achieve some
efficency I utilized a method which uses additional cell as virtual top cell. It 
allowed me quickly find out whether system percolates or not and it simplified
to write percolates() method

Then PercolationStats class:

public class PercolationStats {
    public PercolationStats(int N, int T)  // perform T independent experiments on an N-by-N grid
    public double mean()                   // sample mean of percolation threadshold
    public double stddev()                 // sample standard deviation of percolation threshold
    public double confidenceLo()           // low endpoint of 95% confidence interval
    public double confidenceHi()           // high endpoint of 95% confidence interval
}


This process of running multiple instances T amount of times and using statistics over 
the variety of solutions is known as doing a monte carlo simulation.
Each process goes until the system percolates. 
For the better perfomance we create another API.
This class will use the percolation model as the object of each instance. 
Then it will do statistical calculations


---	HOW TO USE IT	---

Download the project and just run it in your pc. Make sure you have Java installed
in your machine
and Intelij IDEA (though it can run in other IDEAs, but I reccomend to use Intelij).
Don't forget to import edu.princeton.cs.algs4.*; library. You can download that library
from algs4.cs.princeton.edu (actual type of lib is jar file).


---	CONCLUSION	---

Finally I made it, initally this assignment was quite confusing, but I went through it.


package com.company;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;


public class PercolationStats {
    Percolation percolation;
    double[] resultsOfTrials;

    int numberOfExperiments;

    public  PercolationStats(int n, int trials)
    {
        numberOfExperiments = trials;
        resultsOfTrials = new double[trials];

        for (int i = 0; i < trials; i++)
        {
            percolation = new Percolation(n);
            int openSites = 0;
            while(!percolation.percolates())
            {
                int randRow = StdRandom.uniform(1, n + 1);
                int randCol = StdRandom.uniform(1, n + 1);

                if (!percolation.isOpen(randRow, randCol))
                {
                    percolation.open(randRow, randCol);
                    openSites++;
                }
            }
            resultsOfTrials[i] = (double) openSites / (n * n);
        }
    }

    public double mean()
    {
        return StdStats.mean(resultsOfTrials);
    }

    public double stddev()
    {
        return StdStats.stddev(resultsOfTrials);
    }

    public double confidenceLow()
    {
        return mean() - ((1.96 * stddev()) / Math.sqrt(numberOfExperiments));
    }

    public double confidenceHigh()
    {
        return mean() + ((1.96 * stddev()) / Math.sqrt(numberOfExperiments));
    }

    public static void main(String[] args)
    {
        int n = StdIn.readInt();
        int t = StdIn.readInt();

        Stopwatch stopwatch = new Stopwatch();
        PercolationStats percolationStats = new PercolationStats(n,t);

        System.out.println("mean()           = "
                + (int) Math.round((percolationStats.mean() * 1000000)) / 1000000.0);
        //double number2 = (int)(Math.round(number1 * 100))/100.0
        System.out.println("stddev()         = "
                + (int) Math.round((percolationStats.stddev() * 1000000)) / 1000000.0);
        System.out.println("confidenceLow()  = "
                + (int) Math.round((percolationStats.confidenceLow() * 1000000)) / 1000000.0);
        System.out.println("confidenceHigh() = "
                + (int) Math.round((percolationStats.confidenceHigh() * 1000000)) / 1000000.0);
        System.out.println("elapsed time     = " + stopwatch.elapsedTime());

    }
}

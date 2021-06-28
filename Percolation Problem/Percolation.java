package com.company;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] arr;                    // n by n grid
    private WeightedQuickUnionUF uf;            // dsu
    public int numberOfOpenSites = 0;          // number of opened cells
    private int size = 0;

    private int top;                            // assumed virtual top cell

    private int[] openSitesAtBottomRow;         // to keep track of opened sites in the bottom row

    public Percolation(int n)
    {
        if(n <= 0)
            throw new IllegalArgumentException();
        size = n;
        arr = new boolean[n][n];        // initially all the values are false
        uf = new WeightedQuickUnionUF(n * n + 1); // +1 for virtual top cell
        top = 0;
        openSitesAtBottomRow = new int[size];
    }

    public void open(int row, int col)
    {
        if(row <= 0 || row > size || col <= 0 || col > size)
            throw new IllegalArgumentException();
        if (isOpen(row, col)) {
            return;
        }
        arr[row - 1][col - 1] = true;
        numberOfOpenSites++;

        if (row == 1)
            uf.union(top, getPosInUF(row, col));
        if(row == size)
            openSitesAtBottomRow[col - 1] = getPosInUF(row, col);
        if (row - 1 > 0 && isOpen(row - 1, col))
            uf.union(getPosInUF(row - 1, col), getPosInUF(row, col));
        if (row + 1 <= size && isOpen(row + 1, col))
            uf.union(getPosInUF(row + 1, col), getPosInUF(row, col));
        if (col - 1 > 0 && isOpen(row, col - 1))
            uf.union(getPosInUF(row, col - 1), getPosInUF(row, col));
        if (col + 1 <= size && isOpen(row, col + 1))
            uf.union(getPosInUF(row, col + 1), getPosInUF(row, col));
    }

    public boolean isOpen(int row, int col)
    {
        if(row <= 0 || row > size || col <= 0 || col > size)
            throw new IllegalArgumentException("Illegal Argument, check the values of 'row', 'col");
        return arr[row - 1][col - 1];
    }

    public boolean isFull(int row, int col)
    {
        if(row <= 0 || row > size || col <= 0 || col > size)
            throw new IllegalArgumentException();
        return uf.find(top) == uf.find(getPosInUF(row, col));
    }

    public int numberOfOpenSites()
    {
        return numberOfOpenSites;
    }

    private int getPosInUF(int row, int col)
    {
        return (row - 1) * size + col;
    }

    private String printArr()
    {
        String s = "";
        for (boolean[] bs : arr) {
            for (boolean bl : bs) {
                s = s + bl + " ";
            }
            s = s + "\n";
        }
        s = s.replaceAll("true", "T");
        s = s.replaceAll("false", "F");

        return s;
    }
    public boolean percolates()
    {
        for (int i : openSitesAtBottomRow) {
            if(uf.find(top) == uf.find(i) && i != 0)
                return true;
        }
        return false;
    }

    public static void main(String[] args)
    {
        final int N = 4;
        /*          UNIT TESTING OF MY PROGRAM          */
        Percolation percolation = new Percolation(N);
        // F F F F
        // F F F F
        // F F F F
        // F F F F      // initially all sites are blocked (false)
        percolation.open(1,1);
        // T F F F
        // F F F F
        // F F F F
        // F F F F
        percolation.open(1,2);
        // T T F F
        // F F F F
        // F F F F
        // F F F F
        percolation.open(2,2);
        // T T F F
        // F T F F
        // F F F F
        // F F F F
        percolation.open(3,2);
        // T T F F
        // F T F F
        // F T F F
        // F F F F
        percolation.open(3,4);
        // T T F F
        // F T F F
        // F T F T
        // F F F F
        percolation.open(4,4);
        // T T F F
        // F T F F
        // F T F T
        // F F F T
        percolation.open(4,2);
        // T T F F
        // F T F F
        // F T F T
        // F T F T

        /*----------------------------*/

        System.out.println("Number of open sites: " + percolation.numberOfOpenSites());
        // expected value is : 7

        System.out.println("Cell (2,2) is open : " + percolation.isOpen(2,2));
        // T  T  F  F
        // F (T) F  F
        // F  T  F  T
        // F  T  F  T  // expected value is : true

        System.out.println("Cell (3,3) is open : " + percolation.isOpen(3,3));
        // T  T  F  F
        // F  T  F  F
        // F  T (F) T
        // F  T  F  T  // expected value is : false

        /* ------------------------------------------------- */

        System.out.println("Cell (3,2) is full : " + percolation.isFull(3,2));
        // T (T) F  F  // connected to an open site in the top row
        // F (T) F  F
        // F (T) F  T
        // F  T  F  T  // expected value is : true

        System.out.println("Cell (4,4) is full : " + percolation.isFull(4,4));
        // T  T  F  F   // not connected to an open site in the top row
        // F  T  F  F
        // F  T  F (T)
        // F  T  F (T)  // expected value is : false

        /* ---------------------------------------------------------------- */

        System.out.println(percolation.printArr());
        System.out.println("System percolates : " + percolation.percolates());

    }
}


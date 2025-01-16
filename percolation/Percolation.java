import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // creates n-by-n grid, with all sites initially blocked
    private final WeightedQuickUnionUF uf;
    private final int n, virtualTop, virtualBottom;
    private int openSitesCount;
    private final boolean[][] openSites;

    public Percolation(int n) {
        if(n <= 0) throw new IllegalArgumentException("n must be larger than 0");
        this.uf = new WeightedQuickUnionUF(n * n + 3);
        this.n = n;
        this.virtualTop = n * n + 1;
        this.virtualBottom = n * n + 2;
        this.openSitesCount = 0;
        openSites = new boolean[n + 1][n + 1];
    }
    private int to1D(int row, int col){
        return (row - 1) * n + col;
    }
    private void validate(int row, int col){
        if (row <= 0 || row > n || col <= 0 || col > n){
            throw new IllegalArgumentException("Invalid bound");
        }
    }
    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        validate(row, col);
        if (!isOpen(row, col)){
            openSites[row][col] = true;
            openSitesCount ++;
            int site = to1D(row, col);
            if (row == 1){
                uf.union(site, virtualTop);
            }
            if (row == n){
                uf.union(site, virtualBottom);
            }
            if (row > 1 && isOpen(row - 1, col)){
                uf.union(site, to1D(row - 1,col));
            }
            if (row < n && isOpen(row + 1, col)){
                uf.union(site, to1D(row + 1,col));
            }
            if (col > 1 && isOpen(row, col - 1)){
                uf.union(site, to1D(row,col - 1));
            }
            if (col < n  && isOpen(row, col + 1)){
                uf.union(site, to1D(row,col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        validate(row, col);
        return openSites[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        validate(row, col);
        return uf.find(virtualTop) == uf.find(to1D(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return openSitesCount;
    }

    // does the system percolate?
    public boolean percolates(){
        return uf.find(virtualTop) == uf.find(virtualBottom);
    }

    // test client (optional)
    public static void main(String[] args){
        Percolation perc = new Percolation(4);
        perc.open(1,1);
        perc.open(2,1);
        perc.open(3,1);
        perc.open(4,1);
        System.out.println("Percolates: " + perc.percolates());
        System.out.println("Open sites Count:  " + perc.openSitesCount);
    }
}

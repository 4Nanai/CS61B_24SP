public class Percolation {
    // TODO: Add any necessary instance variables.
    private final int[] grid;
    private int openSite;
    private static int size;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        grid = new int[N * N];
        for (int i = 0; i < N * N; i++) {
            grid[i] = -1;
        }
        openSite = 0;
        size = N;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }
        grid[row * size + col] = row * size + col;
        openSite++;
        if (col < size - 1 && isOpen(row, col + 1)) {
            union(row, col, row, col + 1);
        }
        if (col > 0 && isOpen(row, col - 1)) {
            union(row, col, row, col - 1);
        }
        if (row < size - 1 && isOpen(row + 1, col)) {
            union(row + 1, col, row, col);
        }
        if (row > 0 && isOpen(row - 1, col)) {
            union(row - 1, col, row, col);
        }
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row * size + col] != -1;
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        if (!isOpen(row, col)) {
            return false;
        }
        int siteState = find(row, col);
        return siteState < size && siteState >= 0;
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return openSite;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        for (int i = 0; i < size; i++) {
            if (isFull(size - 1, i)) {
                return true;
            }
        }
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.
    /* 臥槽你的，這WeightedQuickUnionUF就不能放在task前面嗎，我妹看到要用這個ADT，自己實現了一個UF */
    public int find(int row, int col) {
        if (!isOpen(row, col) || (grid[row * size + col] == row * size + col)) {
            return size * row + col;
        } else {
            return grid[row * size + col] = find(grid[row * size + col] / size, grid[row * size + col] % size);
        }
    }

    public void union(int row1, int col1, int row2, int col2) {
        int father1 = find(row1, col1);
        int father2 = find(row2, col2);
        if (father1 == father2) {
            return;
        }
        if (father1 <= father2) {
            grid[father2] = father1;
        } else {
            grid[father1] = father2;
        }
    }

}

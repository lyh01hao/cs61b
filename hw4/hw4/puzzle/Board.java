package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;
public class Board implements WorldState {
    private static final int BLANK = 0;
    private int[][] tiles;
    private int N;
    public Board(int[][] tiles) {
        this.N = tiles.length;
        this.tiles = new int[N][N];
        for (int r = 0; r < N; r += 1) {
            for (int c = 0; c < N; c += 1) {
                this.tiles[r][c] = tiles[r][c];
            }
        }
    }
    public int tileAt(int i, int j) {
        if (i < 0 || i > size() || j < 0 || j > size()) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }
    public int size() {
        return tiles[0].length;
    }
    public int hamming() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) != (i) * N + j + 1) {
                    count++;
                }
            }
        }
        return count;
    }
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) == 0) {
                    continue;
                }
                int row = (tileAt(i, j) - 1) / N;
                int col = (tileAt(i, j) - 1) % N;
                manhattan += Math.abs(row - i) + Math.abs(col - j);
            }
        }
        return manhattan;
    }
    @Override
    public boolean equals(Object y) {
        if (y == null || y.getClass() != this.getClass()) {
            return false;
        }
        Board yBoard = (Board) y;
        if (N != yBoard.N) {
            return false;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.tileAt(i, j) != yBoard.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }
    /** From Josh **/
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

}

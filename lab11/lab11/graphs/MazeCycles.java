package lab11.graphs;

import edu.princeton.cs.algs4.StdRandom;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int[] cameFrom;
    private boolean cycleFound = false;

    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        cameFrom = new int[maze.V()];
        int randX = StdRandom.uniform(1, maze.N() + 1);
        int randY = StdRandom.uniform(1, maze.N() + 1);
        int start = maze.xyTo1D(randX, randY);
        marked[start] = true;
        cameFrom[start] = start;
        dfs(start);
        announce();


    }

    // Helper methods go here
    private void dfs(int v) {
        for (int w : maze.adj(v)) {

            if (cycleFound) {
                return;
            }

            if (!marked[w]) {
                marked[w] = true;
                cameFrom[w] = v;
                dfs(w);
            } else if (w != cameFrom[v]) { // When `w` is not the parent of `v` (circle found)
                cameFrom[w] = v;

                int cur = v;    // Reconstruct circle
                edgeTo[cur] = cameFrom[cur];
                while (cur != w) {
                    cur = cameFrom[cur];
                    edgeTo[cur] = cameFrom[cur];
                }

                cycleFound = true;
                return;
            }
        }
    }
}


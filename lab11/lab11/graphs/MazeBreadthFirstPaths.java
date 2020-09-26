package lab11.graphs;
import edu.princeton.cs.algs4.Queue;
/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private Queue<Integer> queue;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        queue = new Queue<>();
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {

        marked[s] = true;
        announce();
        queue.enqueue(s);

        while (!queue.isEmpty()) {
            int currentVertex = queue.dequeue();
            if (currentVertex == t) {
                targetFound = true;
            }
            if (targetFound) {
                return;
            }
            for (int w : maze.adj(currentVertex)) {
                if (!marked[w]) {
                    queue.enqueue(w);
                    marked[w] = true;
                    edgeTo[w] = currentVertex;
                    distTo[w] = distTo[currentVertex] + 1;
                    announce();
                }
            }
        }


    }


    @Override
    public void solve() {
        bfs();
    }
}


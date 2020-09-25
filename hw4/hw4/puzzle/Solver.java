package hw4.puzzle;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.MinPQ;
import java.util.Comparator;
import java.util.HashMap;


public class Solver {
    private HashMap<WorldState, Integer> cache = new HashMap<>();
    private Stack<WorldState> path = new Stack<>();
    private int count = 0;

    private class SearchNode {
        private WorldState state;
        private int numOfMoves;
        private Integer priority;
        private SearchNode previous;

        private SearchNode(WorldState state, SearchNode previous) {
            this.previous = previous;
            this.state = state;
            if (previous != null) {
                this.numOfMoves = previous.numOfMoves + 1;
            } else {
                this.numOfMoves = 0;
            }
            if (!cache.containsKey(state)) {
                cache.put(state, state.estimatedDistanceToGoal());
            }
            this.priority = numOfMoves + cache.get(state);
        }
    }

    private class SearchNodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            return o1.priority.compareTo(o2.priority);
        }
    }

    public Solver(WorldState initial) {
        MinPQ<SearchNode> minPQ = new MinPQ<>(new SearchNodeComparator());
        SearchNode currentNode = new SearchNode(initial, null);

        while (!currentNode.state.isGoal()) {
            for (WorldState ws : currentNode.state.neighbors()) {
                if (currentNode.previous == null || !ws.equals(currentNode.previous.state)) {
                    SearchNode current = new SearchNode(ws, currentNode);
                    minPQ.insert(current);
                    count++;
                }
            }
            currentNode = minPQ.delMin();
        }
        for (SearchNode node = currentNode; node != null; node = node.previous) {
            path.push(node.state);
        }
    }
    public int moves() {
        return this.path.size() - 1;
    }
    public Iterable<WorldState> solution() {
        return path;
    }
}

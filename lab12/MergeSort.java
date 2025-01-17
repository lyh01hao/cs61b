import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> compositeQueue = new Queue<>();
        for (Item i : items) {
            Queue<Item> forOneItem = new Queue<>();
            forOneItem.enqueue(i);
            compositeQueue.enqueue(forOneItem);
        }
        return compositeQueue;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        Queue<Item> mergedQueue = new Queue<>();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            mergedQueue.enqueue(getMin(q1, q2));
        }
        return mergedQueue;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    /** 并无其他作用，只是用来简化新建一个队列的操作，需要复制一个新的队列，这样做方便一点 **/
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        if (items.size() <= 1) {
            return items;
        }
        Queue<Queue<Item>> queues = makeSingleItemQueues(items);
        Queue<Item> leftQueue = new Queue<>();
        Queue<Item> rightQueue = new Queue<>();
        int left = items.size() / 2;
        for (int i = 0; i < left; i++) {
            leftQueue.enqueue(queues.dequeue().dequeue());
        }
        int right = items.size();
        for (int i = left; i < right; i++) {
            rightQueue.enqueue(queues.dequeue().dequeue());
        }
        Queue q1 = mergeSort(leftQueue);
        Queue q2 = mergeSort(rightQueue);

        return mergeSortedQueues(q1, q2);
    }

    public static void main(String[] args) {
        Queue<Integer> studentID = new Queue<>();
        studentID.enqueue(5);
        studentID.enqueue(78);
        studentID.enqueue(71);
        studentID.enqueue(1);
        studentID.enqueue(114514);
        System.out.println(studentID);
        Queue sortedID =  MergeSort.mergeSort(studentID);
        System.out.println("original: " + studentID);
        System.out.println("sorted:   " + sortedID);

    }
}

import edu.princeton.cs.algs4.Queue;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     *
     * The items in q2 will be catenated after all of the items in q1.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item: q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /** Returns a random item from the given queue. */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted  A Queue of unsorted items
     * @param pivot     The item to pivot on
     * @param less      An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are less than the given pivot.
     * @param equal     An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are equal to the given pivot.
     * @param greater   An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        // Your code here!
        for (Item i : unsorted) {
            int com = i.compareTo(pivot);
            if (com < 0) {
                less.enqueue(i);
            }
            if (com > 0) {
                greater.enqueue(i);
            }
            if (com == 0) {
                equal.enqueue(i);
            }
        }
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> quickSort(
            Queue<Item> items) {
        // Your code here!
        if (items.size() <= 1) {
            return items;
        }
        Queue<Item> less = new Queue<>();
        Queue<Item> equal = new Queue<>();
        Queue<Item> grater = new Queue<>();
        Item pivot = getRandomItem(items);
        partition(items, pivot, less, equal, grater);
        Queue q1 = quickSort(less);
        Queue q2 = quickSort(grater);
        Queue<Item> result = catenate(q1, equal);
        result = catenate(result, q2);
        return result;
    }

    public static void main(String[] args) {
        Queue<Integer> studentID = new Queue<>();
        studentID.enqueue(5);
        studentID.enqueue(78);
        studentID.enqueue(71);
        studentID.enqueue(1);
        studentID.enqueue(114514);
        System.out.println(studentID);
        Queue sortedID =  QuickSort.quickSort(studentID);
        System.out.println("original: " + studentID);
        System.out.println("sorted:   " + sortedID);
    }
}

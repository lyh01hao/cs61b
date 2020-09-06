/**
 * created by Yihao Lin
 * @param <T>
 */
public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    private int initSize = 4;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    public void addFirst(T item){
        if (size == items.length){
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst = ((nextFirst - 1) + items.length) % items.length;
        size++;
    }
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int start = newItems.length / 2 - size / 2;
        int newIndex = start + 1;
        for (int i = 0; i < size; i++) {
            newItems[newIndex] = items[(nextFirst + 1 + i) % items.length];
            newIndex++;
        }
        items = newItems;
        nextFirst = start;
        nextLast = newIndex;
    }
    public void addLast(T item){
        if (size == items.length){
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        int curFirst = (nextFirst + 1) % items.length;
        for(int i = 0 ;i < size;i++){
            System.out.print(items[i + curFirst] + " ");
        }
        System.out.println();
    }
    public T removeFirst(){
        if (isEmpty()) {
            return null;
        }
        if ((double) size / items.length < 0.25 && items.length > initSize){
            resizeDown();
        }

        nextFirst = (nextFirst + 1) % items.length;
        T removed = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return removed;
    }
    public T removeLast(){
        if (isEmpty()) {
            return null;
        }
        if ((double) size / items.length < 0.25 && items.length > initSize){
            resizeDown();
        }

        nextLast = ((nextLast - 1) + items.length) % items.length;
        T removed = items[nextLast];
        items[nextLast] = null;
        size--;
        return removed;
    }
    public T get(int index){
        if (index < 0 || index >= size) {
            return null;
        }
        int trueIndex;
        trueIndex = (nextFirst + 1 + index) % items.length;
        return items[trueIndex];
    }
    private void resizeDown(){
        resize(size * 2 < initSize ? initSize : size * 2);
    }
}


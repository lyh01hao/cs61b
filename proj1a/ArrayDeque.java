public class ArrayDeque<T> {
    T[] items;
    int size;
    public ArrayDeque(){
        items = (T[]) new Object[100];
        size = 0;
    }

    public void addFirst(T item){
        if(items.length == size){
            T[] temp = (T[]) new Object[size * 2];
            System.arraycopy(items,0,temp,1,size);
            items = temp;
        }else{
            T[] temp = (T[]) new Object[size];
            System.arraycopy(items,0,temp,1,size);
            items = temp;
        }
        items[0] = item;
        size ++;
    }
    private void resize(int capacity){
        T[] temp = (T[]) new Object[capacity];
        System.arraycopy(items,0,temp,0,size);
        items = temp;
    }
    public void addLast(T item){
        if(items.length == size){
            resize(size * 2);
        }
        items[size] = item;
        size ++;
    }
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }else{
            return false;
        }
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        for(int i = 0 ;i<size;i++){
            System.out.print(items[i]+" ");
        }
        System.out.println();
    }
    public T removeFirst(){
        T removed = items[0];
        T[] temp = (T[]) new Object[size];
        System.arraycopy(items,1,temp,0,size);
        items = temp;
        size --;
        if(size <= 0.25* items.length){
            resizeDown((int)0.75* items.length);
        }
        return removed;
    }
    public T removeLast(){
        T removed = items[size];
        items[size] = null;
        size --;
        if(size <= 0.25* items.length){
            resizeDown((int)0.75* items.length);
        }
        return removed;
    }
    public T get(int index){
        return items[index];
    }
    public void resizeDown(int capacity){
        T[] temp = (T[]) new Object[capacity];
        System.arraycopy(items,0,temp,0,size);
        items = temp;
    }
}

public class LinkedListDeque<T> {
    List sentinel;
    int size;
    public class List{
        T value;
        List pre;
        List post;

        List(T value, List pre, List post){
            this.value = value;
            this.pre = pre;
            this.post = post;
        }
    }

    public LinkedListDeque(){
        sentinel = new List(null,null,null);
        sentinel.pre = sentinel;
        sentinel.post = sentinel;
        size = 0;
    }

    public void addFirst(T item){
        List newList = new List(item,sentinel,sentinel.post);
        sentinel.post = newList;
        newList.post.pre = newList;
        size ++;
    }
    public void addLast(T item){
        List newList = new List(item,sentinel.pre,sentinel);
        sentinel.pre = newList;
        newList.pre.post = newList;
        size ++;
    }
    public boolean isEmpty(){
        if (size == 0){
            return true;
        }else{
            return false;
        }
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        List l = sentinel;
        for(int i = 0; i < size ; i++){
            l = l.post;
            System.out.print(l.value+" ");
        }
        System.out.println();
    }
    public T removeFirst(){
        if(size == 0){
            return null;
        }else{
            List target = sentinel.post;
            sentinel.post.post.pre = sentinel;
            sentinel.post = sentinel.post.post;
            size--;
            return target.value;
        }
    }
    public T removeLast(){
        if(size == 0){
            return null;
        }else{
            List target = sentinel.pre;
            sentinel.pre.pre.post = sentinel;
            sentinel.pre = sentinel.pre.pre;
            size --;
            return target.value;
        }

    }
    public T get(int index){
        List l = sentinel;
        for(int i = 0; i <= index; i++){
            l = l.post;
        }
        return l.value;
    }
    public T getRecursive(int index){
        return getRecursiveHelper(sentinel,index).value;
    }
    private List getRecursiveHelper(List start, int index){
        if(index == 0){
            return start.post;
        }
         return getRecursiveHelper(start.post ,index - 1);
    }
}

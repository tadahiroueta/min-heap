public class MinHeapGeneric<T extends Comparable<T>> {
    protected static final int DEFAULT_CAPACITY = 10;

    protected T[] heap = (T[]) new Comparable[DEFAULT_CAPACITY];
    protected int size = 1;

    public MinHeapGeneric(int capacity) { heap = (T[]) new Comparable[capacity]; }
    public MinHeapGeneric(T... values) { buildHeap(values); }

    public int getSize() { return size; }
    public T peekMinimum() { return heap[1]; }

    private int getLeftChildIndex(int index) { return 2 * index; }
    private int getRightChildIndex(int index) { return 2 * index + 1; }
    private int getParentIndex(int index) { return index / 2; }

    private void buildHeap(T[] nums) {
        size += nums.length;
        heap = (T[]) new Comparable[size];
        System.arraycopy(nums, 0, heap, 1, nums.length);

        for (int i = size - 1; i > 0; i--) siftDown(heap[i], i);
    }

    private void doubleCapacity() {
        T[] temp = (T[]) new Comparable[heap.length * 2];
        System.arraycopy(heap, 0, temp, 0, heap.length);
        heap = temp;
    }

    public void insert(T value) {
        if (size == heap.length) doubleCapacity();

        heap[size] = value;
        int addedIndex = size++;
        bubbleUp(value, addedIndex);
    }

    private void bubbleUp(T value, int addedIndex) {
        while (true) {
            final int parentIndex = getParentIndex(addedIndex);
            if (parentIndex == 0 || heap[parentIndex].compareTo(heap[addedIndex]) <= 0) return;
            
            heap[addedIndex] = heap[parentIndex];
            heap[parentIndex] = value;
            addedIndex = parentIndex;
    }}

    private T popLast() {
        final T max = heap[--size];
        heap[size] = null;
        return max;
    }

    public T popMinimum() {
        final T last = popLast();
        final T min = heap[1];
        siftDown(last, 1);
        return min;
    }

    private void siftDown(final T last, int lastIndex) {
        while (true) {
            final int leftChildIndex = getLeftChildIndex(lastIndex);
            final int rightChildIndex = getRightChildIndex(lastIndex);

            if (leftChildIndex >= size && rightChildIndex >= size) break; // no children

            if (leftChildIndex >= size) { // only right child
                if (last.compareTo(heap[rightChildIndex]) <= 0) break; // larger child

                heap[lastIndex] = heap[rightChildIndex];
                lastIndex = rightChildIndex;
            }

            if (rightChildIndex >= size) { // only left child
                if (last.compareTo(heap[leftChildIndex]) <= 0) break; // larger child

                heap[lastIndex] = heap[leftChildIndex];
                lastIndex = leftChildIndex;
            }

            final T leftChild = heap[leftChildIndex];
            final T rightChild = heap[rightChildIndex];

            final T minChild = leftChild.compareTo(rightChild) > 0 ? rightChild : leftChild;
            final int minChildIndex = minChild == leftChild ? leftChildIndex : rightChildIndex;

            if (last.compareTo(minChild) <= 0) break; // larger children

            heap[lastIndex] = minChild;
            lastIndex = minChildIndex;
        }
        heap[lastIndex] = last; // lays it to rest
        return;
    }


    // copied for printing
    @Override
    public String toString () {
        String output = "";
        for (int i = 1; i < getSize(); i++) output += heap[i] + ", ";
        return output.substring(0, output.lastIndexOf(",")); //lazily truncate last comma
    }
 
    /** method borrowed with minor modifications from the internet somewhere, for printing */
    public void display() {
        int nBlanks = 32, itemsPerRow = 1, column = 0, j = 1;
        String dots = "...............................";
        System.out.println(dots + dots);
        while (j < this.getSize()) {
            if (column == 0) 
                for (int k = 0; k < nBlanks; k++)
                    System.out.print(' ');

            System.out.print((heap[j] == null)? "" : heap[j]);

            if (++column == itemsPerRow) {
                nBlanks /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
            }

            else
                for (int k = 0; k < nBlanks * 2 - 2; k++)
                    System.out.print(' ');
            
            j++;
        }
        System.out.println("\n" + dots + dots);
}}
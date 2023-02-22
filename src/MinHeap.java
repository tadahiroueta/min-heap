public class MinHeap {
    protected static final int DEFAULT_CAPACITY = 10;

    protected Integer[] heap = new Integer[DEFAULT_CAPACITY];
    protected int size = 1;

    public MinHeap(int capacity) { heap = new Integer[capacity]; }
    public MinHeap(Integer... nums) { buildHeap(nums); }

    public int getSize() { return size; }
    public int peekMinimum() { return heap[1]; }

    public int getLeftChildIndex(int index) { return 2 * index; }
    public int getRightChildIndex(int index) { return 2 * index + 1; }
    public int getParentIndex(int index) { return index / 2; }

    private void buildHeap(Integer[] nums) {
        size += nums.length;
        heap = new Integer[size];
        System.arraycopy(nums, 0, heap, 1, nums.length);

        for (int i = size - 1; i > 0; i--) siftDown(heap[i], i);
    }

    private void doubleCapacity() {
        Integer[] temp = new Integer[heap.length * 2];
        System.arraycopy(heap, 0, temp, 0, heap.length);
        heap = temp;
    }

    public void insert(int value) {
        if (size == heap.length) doubleCapacity();

        heap[size] = value;
        int addedIndex = size++;
        bubbleUp(value, addedIndex);
    }

    private void bubbleUp(int value, int addedIndex) {
        while (true) {
            final int parentIndex = getParentIndex(addedIndex);
            if (parentIndex == 0 || heap[parentIndex] <= heap[addedIndex]) return;
            
            heap[addedIndex] = heap[parentIndex];
            heap[parentIndex] = value;
            addedIndex = parentIndex;
    }}

    private int popLast() {
        final int max = heap[--size];
        heap[size] = null;
        return max;
    }

    public int popMinimum() {
        final int last = popLast();
        final int min = heap[1];
        siftDown(last, 1);
        return min;
    }

    private void siftDown(final int last, int lastIndex) {
        while (true) {
            final int leftChildIndex = getLeftChildIndex(lastIndex);
            final int rightChildIndex = getRightChildIndex(lastIndex);

            final int leftChild = leftChildIndex >= size ? Integer.MAX_VALUE : heap[leftChildIndex]; // max int leaf is unexistant
            final int rightChild = rightChildIndex >= size ? Integer.MAX_VALUE : heap[rightChildIndex];

            final int minChild = Math.min(leftChild, rightChild);
            final int minChildIndex = minChild == leftChild ? leftChildIndex : rightChildIndex;

            if (last <= minChild) {
                heap[lastIndex] = last; // lays it to rest
                return;
            }

            heap[lastIndex] = minChild;
            lastIndex = minChildIndex;
    }}


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
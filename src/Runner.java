public class Runner
{
	public static void main(String[] args) 
	{
		// MinHeapGeneric<Integer> heap = new MinHeapGeneric<Integer>();
		
		// int[] nums = {8, 42, 35, 4, -1, 99, 76, 20};
		
		// for (int i = 0; i < nums.length; i++) 
		// 	heap.insert(nums[i]);
        
        MinHeapGeneric<String> heap = new MinHeapGeneric<String>("D", "H", "F", "C", "A", "J", "I", "E");
		
		System.out.println("Heap toString: " + heap);
		System.out.println("\ninitial state of the heap");
		heap.display();
		
		heap.insert("G"); System.out.println("inserting G into the heap...");
		heap.display();
		
		heap.insert("B"); System.out.println("inserting B into the heap...");
		heap.display();
		
		System.out.println("Pop min (new min at root) = " + heap.popMinimum());
		heap.display();

		System.out.println("Peek min (shouldn't change) = " + heap.peekMinimum());
		heap.display();
	}
}
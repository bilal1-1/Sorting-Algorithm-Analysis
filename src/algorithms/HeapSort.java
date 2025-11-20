package algorithms;
import datastructures.Heap;

public class HeapSort {
    public static void sort(int[] arr) {
        Heap heap = new Heap(arr.length);  // create a heap with the same capacity as the array

        for (int num : arr) {   // insert all elements into the heap
            heap.insert(num);
        }

        for (int i = arr.length - 1; i >= 0; i--) { 
            arr[i] = heap.extractMax();   // place the largest element at the end
        }
    }
}

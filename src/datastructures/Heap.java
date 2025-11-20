package datastructures;

public class Heap {
    private int[] data; // the array that stores the heap elements
    private int size;   // how many elements are currently in the heap


    public Heap(int capacity) {
        data = new int[capacity];  // create an empty array with given capacity
        size = 0;                  // heap starts empty
    }

    public void insert(int value) {
        if (size == data.length) throw new RuntimeException("Heap is full");  // check if the heap is full
        data[size] = value;    // place the new value at the end
        siftUp(size);          // move it up to maintain heap property
        size++;                // increase the number of elements
    }

    public int extractMax() {
        if (size == 0) throw new RuntimeException("Heap is empty");   // check if the heap is empty
        int max = data[0];          // the largest element is at the root
        data[0] = data[size - 1];   // move the last element to the root
        size--;                     // reduce the heap size
        siftDown(0);             // push the new root down to fix the heap
        return max;
    }

    private void siftUp(int i) {       // move the new element upward if needed
        while (i > 0) {                // continue as long as the node has a parent
            int parent = (i - 1) / 2;  // find the parent index
            if (data[i] <= data[parent])  
            break;                     // stop if the heap property is already correct
            swap(i, parent);           // swap with the parent to move upward
            i = parent;                // continue from the parent's position
        }
    }

    private void siftDown(int i) {     // move the element downward if needed
        while (2 * i + 1 < size) {     // continue as long as it has at least one child
            int left = 2 * i + 1;      
            int right = 2 * i + 2;     // find the left and right child positions
            int largest = i;

            if (left < size && data[left] > data[largest]) largest = left;    // update largest if the left child is bigger
            if (right < size && data[right] > data[largest]) largest = right; // update largest if the right child is bigger

            if (largest == i) 
            break;                     // stop if the heap property is already correct
            swap(i, largest);          // swap with the larger child
            i = largest;               // continue from the new position
        }
    }

    private void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];              // simple swap between two positions
        data[j] = temp;
    }
}

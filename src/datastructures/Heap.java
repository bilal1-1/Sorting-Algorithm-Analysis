package datastructures;

public class Heap {
    private int[] data;
    private int size;

    public Heap(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    public void insert(int value) {
        if (size == data.length) throw new RuntimeException("Heap is full");
        data[size] = value;
        siftUp(size);
        size++;
    }

    public int extractMax() {
        if (size == 0) throw new RuntimeException("Heap is empty");
        int max = data[0];
        data[0] = data[size - 1];
        size--;
        siftDown(0);
        return max;
    }

    private void siftUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (data[i] <= data[parent]) break;
            swap(i, parent);
            i = parent;
        }
    }

    private void siftDown(int i) {
        while (2 * i + 1 < size) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int largest = i;

            if (left < size && data[left] > data[largest]) largest = left;
            if (right < size && data[right] > data[largest]) largest = right;

            if (largest == i) break;
            swap(i, largest);
            i = largest;
        }
    }

    private void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}

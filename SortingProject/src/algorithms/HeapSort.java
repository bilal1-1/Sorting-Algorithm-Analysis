package algorithms;
import datastructures.Heap;

public class HeapSort {
    public static void sort(int[] arr) {
        Heap heap = new Heap(arr.length);

        // Diziyi heap'e ekle
        for (int num : arr) {
            heap.insert(num);
        }

        // Heap'ten elemanları çıkararak sıralı diziye koy
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = heap.extractMax();
        }
    }
}

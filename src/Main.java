

import java.util.Random;

import algorithms.BubbleSort;
import algorithms.HeapSort;
import algorithms.MergeSort;
import algorithms.QuickSort;
import algorithms.SelectionSort;
import datastructures.SinglyLinkedList;
import datastructures.DynamicArray;
import datastructures.Heap; // heap import eklendi
import utils.CSVWriter;

public class Main {

    public static void main(String[] args) {

        int[] sizes = {1000, 5000, 10000, 20000, 50000};

        int trials = 5; // her boyut için 5 tekrar
        Random rand = new Random();

        CSVWriter csv = new CSVWriter("data/results.csv");
        
        // CSV başlığı
        //csv.writeLine("Algorithm,n,trial,timeMs");

        for (int n : sizes) {
            for (int t = 1; t <= trials; t++) {

                int[] arr = new int[n];
                for (int i = 0; i < n; i++)
                    arr[i] = rand.nextInt(500000);

                System.out.println("\n--- Array Size: " + n + " | Trial: " + t + " ---");

                // Bubble Sort
                int[] arrCopy = arr.clone();
                long start = System.nanoTime();
                BubbleSort.sort(arrCopy);
                long end = System.nanoTime();
                double time = (end - start) / 1_000_000.0;
                csv.writeLine("BubbleSort," + n + "," + t + "," + time);

                // Merge Sort
                arrCopy = arr.clone();
                start = System.nanoTime();
                MergeSort.sort(arrCopy);
                end = System.nanoTime();
                time = (end - start) / 1_000_000.0;
                csv.writeLine("MergeSort," + n + "," + t + "," + time);

                // Quick Sort
                arrCopy = arr.clone();
                start = System.nanoTime();
                QuickSort.sort(arrCopy);
                end = System.nanoTime();
                time = (end - start) / 1_000_000.0;
                csv.writeLine("QuickSort," + n + "," + t + "," + time);

                // Heap Sort
                arrCopy = arr.clone();
                start = System.nanoTime();
                HeapSort.sort(arrCopy);
                end = System.nanoTime();
                time = (end - start) / 1_000_000.0;
                csv.writeLine("HeapSort," + n + "," + t + "," + time);

                // Selection Sort
                arrCopy=arr.clone();
                start = System.nanoTime();
                SelectionSort.sort(arrCopy);
                end = System.nanoTime();
                time = (end - start) / 1_000_000.0;
                csv.writeLine("SelectionSort," + n + "," + t + "," + time);
            }
        }

        csv.close();

        System.out.println("\n~Data Structures~\n");

        SinglyLinkedList list = new SinglyLinkedList();

        list.addFirst(10);
        list.addLast(20);
        list.addLast(30);
        list.printList();

        list.remove(20);
        list.printList();

        System.out.println(list.contains(30));
        System.out.println(list.getSize());

        // Heap
        Heap heap = new Heap(10);  // 10 elemanlık bir heap
        System.out.println("\n--Heap--\n");

        heap.insert(15);
        heap.insert(40);
        heap.insert(10);
        heap.insert(25);
        heap.insert(30);

        System.out.println("Extracted max: " + heap.extractMax()); // 40
        System.out.println("Extracted max: " + heap.extractMax()); // 30

        heap.insert(50);
        heap.insert(5);

        System.out.println("Extracted max: " + heap.extractMax()); // 50

        
        // DynamicArray
        DynamicArray arr = new DynamicArray(5);
        System.out.println("\n--Dynamic Array--\n");
            
        System.out.println("Initial size: " + arr.size()); // 0
            
        arr.add(10);
        arr.add(20);
        arr.add(30);
            
        System.out.println("Size after adding 3 elements: " + arr.size()); // 3
            
        arr.add(40);
        arr.add(50);
        arr.add(60); // kapasite dolduğu için büyütür
            
        System.out.println("Size after adding more elements: " + arr.size()); // 6
            
            
        System.out.println("\nDeney tamamlandı. Sonuçlar data/results.csv dosyasına kaydedildi.");
        
    }
}

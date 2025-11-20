

import java.util.Random;
import datastructures.Randomgenerator;

import algorithms.BubbleSort;
import algorithms.HeapSort;
import algorithms.MergeSort;
import algorithms.QuickSort;
import algorithms.SelectionSort;
import datastructures.SinglyLinkedList;
import datastructures.DynamicArray;
import datastructures.Heap; 
import utils.CSVWriter;

public class Main {

    public static void main(String[] args) {

        int[] sizes = {1000, 5000, 10000, 20000, 50000}; // array sizes to test

        int trials = 5;  // number of trials for each size
        Random rand = new Random();  // random number generator

        CSVWriter csv = new CSVWriter("data/results.csv");  // writer for CSV output file

        // CSV header
        // csv.writeLine("Algorithm,n,trial,timeMs");

        for (int n : sizes) {
            for (int t = 1; t <= trials; t++) {   // run each algorithm for every size and trial

                int[] arr = new int[n];
                for (int i = 0; i < n; i++)
                    arr[i] = rand.nextInt(500000);   // create random input array

                System.out.println("\n--- Array Size: " + n + " | Trial: " + t + " ---");

                // Bubble Sort
                int[] arrCopy = arr.clone();
                long start = System.nanoTime();
                BubbleSort.sort(arrCopy);                      // measure BubbleSort time and write result
                long end = System.nanoTime();
                double time = (end - start) / 1_000_000.0;
                csv.writeLine("BubbleSort," + n + "," + t + "," + time);

                // Merge Sort
                arrCopy = arr.clone();
                start = System.nanoTime();                     // measure MergeSort time and write result
                MergeSort.sort(arrCopy);
                end = System.nanoTime();
                time = (end - start) / 1_000_000.0;
                csv.writeLine("MergeSort," + n + "," + t + "," + time);

                // Quick Sort
                arrCopy = arr.clone();
                start = System.nanoTime();
                QuickSort.sort(arrCopy);                       // measure QuickSort time and write result
                end = System.nanoTime();
                time = (end - start) / 1_000_000.0;
                csv.writeLine("QuickSort," + n + "," + t + "," + time);

                // Heap Sort
                arrCopy = arr.clone();
                start = System.nanoTime();
                HeapSort.sort(arrCopy);                        // measure HeapSort time and write result
                end = System.nanoTime();
                time = (end - start) / 1_000_000.0;
                csv.writeLine("HeapSort," + n + "," + t + "," + time);

                // Selection Sort
                arrCopy=arr.clone();
                start = System.nanoTime();
                SelectionSort.sort(arrCopy);                   // measure SelectionSort time and write result
                end = System.nanoTime();
                time = (end - start) / 1_000_000.0;
                csv.writeLine("SelectionSort," + n + "," + t + "," + time);
            }
        }

        csv.close();     // close the CSV file

        System.out.println("\n~Data Structures~\n");

        SinglyLinkedList list = new SinglyLinkedList();      // create a new linked list

        list.addFirst(10);
        list.addLast(20);    
        list.addLast(30);
        list.printList();              // test linked list operations

        list.remove(20);
        list.printList();

        System.out.println(list.contains(30));
        System.out.println(list.getSize());

        // Heap
        Heap heap = new Heap(10);   // create heap with capacity 10
        System.out.println("\n--Heap--\n");

        heap.insert(15);
        heap.insert(40); 
        heap.insert(10);     // insert values into heap
        heap.insert(25);           
        heap.insert(30);

        System.out.println("Extracted max: " + heap.extractMax()); // 40
        System.out.println("Extracted max: " + heap.extractMax()); // 30

        heap.insert(50);
        heap.insert(5);      // more insertions

        System.out.println("Extracted max: " + heap.extractMax()); // 50

        
        // DynamicArray
        DynamicArray arr = new DynamicArray(5);
        System.out.println("\n--Dynamic Array--\n");
            
        System.out.println("Initial size: " + arr.size()); // 0
            
        arr.add(10);
        arr.add(20);
        arr.add(30);        // adding elements
            
        System.out.println("Size after adding 3 elements: " + arr.size()); // 3
            
        arr.add(40);
        arr.add(50);
        arr.add(60);    // triggers expansion when full
            
        System.out.println("Size after adding more elements: " + arr.size()); // 6

        // Random generator
        System.out.println("\n--Random Generator--\n");

        int[] sizess = {1000, 3000, 5000}; // array sizes to test with the seeded generator
        int trialss = 5; // number of trials per size

        Randomgenerator rg = new Randomgenerator(571); // create generator with seed

        for (int n : sizess) {
            for (int t = 1; t <= trialss; t++) { // repeat the experiment for each size and trial

                int[] base = rg.generate(n, 500000);   // generate an array of 'n' random numbers (0 to 500000)

                System.out.println("\nArray Size: " + n + " | Trial: " + t);  // print current test info

                // Bubble Sort
                long start = System.nanoTime();
                BubbleSort.sort(base.clone());  
                long end = System.nanoTime();
                System.out.println("BubbleSort: " + ((end - start) / 1_000_000.0) + " ms"); // measure BubbleSort execution time

                // Merge Sort
                start = System.nanoTime();
                MergeSort.sort(base.clone());
                end = System.nanoTime();
                System.out.println("MergeSort: " + ((end - start) / 1_000_000.0) + " ms"); // measure MergeSort execution time

                // Quick Sort
                start = System.nanoTime();
                QuickSort.sort(base.clone());
                end = System.nanoTime();
                System.out.println("QuickSort: " + ((end - start) / 1_000_000.0) + " ms"); // measure QuickSort execution time

                // Heap Sort
                start = System.nanoTime();
                HeapSort.sort(base.clone());
                end = System.nanoTime();
                System.out.println("HeapSort: " + ((end - start) / 1_000_000.0) + " ms"); // measure HeapSort execution time

                // Selection Sort
                start = System.nanoTime();
                SelectionSort.sort(base.clone());
                end = System.nanoTime();
                System.out.println("SelectionSort: " + ((end - start) / 1_000_000.0) + " ms"); // measure SelectionSort time
            }
        }        
        
        System.out.println("\nRandom generator finish.");    
            
        System.out.println("\nThe experiment is complete. The results have been saved to data/results.csv.");
        
    }
}

# Sorting Algorithms & Data Structures Analysis


This project is a performance analysis and training initiative that includes custom-built implementations of basic sorting algorithms and data structures from scratch using Java.

The application measures the execution time of various algorithms on generated datasets of different sizes and exports the results to a CSV file.


## Features

1. Sorting Algorithms
The performance of the following algorithms has been tested on different array sizes:

Bubble Sort: A simple comparison based sorting.

Merge Sort: A divide and conquer approach.

Quick Sort: A pivot based divide and conquer approach.

Heap Sort: A comparison based sorting using a Binary Heap.

2. Data Structures
Manually implemented to demonstrate the basic logic:

Heap: Supports insert, extractMax, siftUp, and siftDown operations.

Singly Linked List: Supports adding elements, removing values, checking for list, and printing the list.


## Installation & Usage

To run the project from terminal, do the following:

Execute the compiled Main class:
java -cp bin Main

(Note: If you are using an IDE like IntelliJ IDEA or Eclipse, simply run the Main.java file.)


## Sample Output

--- Array Size: 10000 ---
BubbleSort: 86.8536 ms
MergeSort: 1.0029 ms
QuickSort: 0.5829 ms
HeapSort: 1.1341 ms

~Data Structures~

--Linked List--
10 -> 20 -> 30 -> null
10 -> 30 -> null
true
Last Size: 2

--Heap--
Extracted max: 40
Extracted max: 30
Extracted max: 50


## License
This project was created for educational purposes. You can use and modify it.
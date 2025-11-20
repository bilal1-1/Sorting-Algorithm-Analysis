package algorithms;

public class BubbleSort {
    public static void sort(int[] array) {
        boolean swapped;   // A boolean to check if a swap happens.
                                                    
        for (int i = 0; i < array.length - 1; i++) {   // The outer loop runs each full pass over the array.
            swapped = false;                           // Reset the swap flag at the start of each pass.
            for (int j = 0; j < array.length - i - 1; j++) {  //The inner loop compares each pair of neighboring elements.
                if (array[j] > array[j + 1]) {  // If the left element is larger than the right one, they must be swapped.
                    int temp = array[j];
                    array[j] = array[j + 1];   // Standard swap operation using a temporary variable.
                    array[j + 1] = temp;
                    swapped = true;   // Swap occurred.
                }
            }
            if (!swapped) break;  // If nothing changed, stop the algorithm.
        }
    }
}

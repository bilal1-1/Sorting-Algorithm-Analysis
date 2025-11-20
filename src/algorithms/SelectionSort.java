package algorithms;

public class SelectionSort {

    public SelectionSort(){  // empty constructor
    }

    public static void sort(int[] arr) {
        int n = arr.length;  // get array length

        for (int i = 0; i < n - 1; i++) {  // loop through the array
            int minIndex = i;  // assume the current position has the smallest element

            
            for (int j = i + 1; j < n; j++) { // search for the smallest element in the remaining array
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;  // update index of the smallest element
                }
            }

            
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;  // swap the smallest element with the current position
        }
    }
}

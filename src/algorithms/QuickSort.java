package algorithms;

public class QuickSort {
    public static void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);   // start quicksort on the full array
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);  // place pivot in the correct position
            quickSort(arr, low, pivotIndex - 1);         // sort the left side     
            quickSort(arr, pivotIndex + 1, high);        // sort the right side
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];    // choose the last element as pivot

        int i = low - 1;          // boundary for elements smaller than pivot
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {     // expand the smaller side
                i++;
                int temp = arr[i];
                arr[i] = arr[j];      // swap smaller element into position
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];       // place pivot in its final sorted position
        arr[high] = temp;
        return i + 1;                 // return pivot index
    }
}

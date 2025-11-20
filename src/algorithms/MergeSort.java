package algorithms;

public class MergeSort {
    public static void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);   // start merge sort on the full array
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;         // find the middle point
            mergeSort(arr, left, mid);            // sort the left half
            mergeSort(arr, mid + 1, right);       // sort the right half
            merge(arr, left, mid, right);         // merge the two sorted halves
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;     //
        int n2 = right - mid;        // sizes of the left and right temporary arrays

        int[] L = new int[n1];       //
        int[] R = new int[n2];       // create temp arrays

        for (int i = 0; i < n1; i++) {   // copy left half
            L[i] = arr[left + i]; 
        }
        
        for (int j = 0; j < n2; j++) {   // copy right half
            R[j] = arr[mid + 1 + j];
        }
        int i = 0, j = 0, k = left;      // initial indexes for L, R, and main array

        while (i < n1 && j < n2) {       // merge while both arrays have elements
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }

        while (i < n1) arr[k++] = L[i++]; // copy any remaining left elements
        while (j < n2) arr[k++] = R[j++]; // copy any remaining right elements
    }
}

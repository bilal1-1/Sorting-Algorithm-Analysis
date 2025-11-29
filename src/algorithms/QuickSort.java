package algorithms;

public class QuickSort {
    public static void sort(int[] arr, int left, int right) {
        int i = left;
        int j = right;

        // Pivot: ortadaki eleman
        int pivot = arr[(left + right) / 2];

        // Bölme işlemi
        while (i <= j) {
            // Pivotdan küçükleri solda bırak
            while (arr[i] < pivot) {
                i++;
            }
            // Pivotdan büyükleri sağda bırak
            while (arr[j] > pivot) {
                j--;
            }

            // Yerleri yanlışsa değiştir
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                i++;
                j--;
            }
        }

        // Sol tarafı sort
        if (left < j)
            sort(arr, left, j);

        // Sağ tarafı sort
        if (i < right)
            sort(arr, i, right);
    }
}

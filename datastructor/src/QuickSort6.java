public class QuickSort6 extends SortAlgorithm {
    private static final int INSERTION_THRESHOLD = 10; // 阈值

    public void sort(Comparable[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(Comparable[] arr, int low, int high) {
        if (high - low < INSERTION_THRESHOLD) {
            //当然这里也可以选择创建一个临时数组，把它扔到现成的插入排序里
            insertionSort(arr, low, high);
        } else {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(Comparable[] arr, int low, int high) {
        Comparable pivot = arr[high];
        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            if (less(arr[j], pivot)) {
                i++;
                exchange(arr, i, j);
            }
        }
        exchange(arr, i + 1, high);
        return i + 1;
    }

    private void insertionSort(Comparable[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Comparable key = arr[i];
            int j = i - 1;
            while (j >= low && less(key, arr[j])) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
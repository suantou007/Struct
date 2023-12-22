public class MergeSort extends SortAlgorithm {
    private static final int INSERTION_THRESHOLD = 10; // ��ֵ��С�ڸó��ȵ�������ʹ�ò�������

    public void sort(Comparable[] arr) {
        Comparable[] temp = new Comparable[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    private void mergeSort(Comparable[] arr, Comparable[] temp, int low, int high) {
        if (low < high) {
            if (high - low <= INSERTION_THRESHOLD) {
                // ʹ�ò��������С��ģ�������������
                insertionSort(arr, low, high);
            } else {
                int mid = low + ((high - low) >> 1);
                mergeSort(arr, temp, low, mid);
                mergeSort(arr, temp, mid + 1, high);
                if (less(arr[mid], arr[mid + 1])) {
                    // �������������ֵС�ڵ����ұ����������Сֵ������ϲ�
                    return;
                }
                merge(arr, temp, low, mid, high);
            }
        }
    }

    private void merge(Comparable[] arr, Comparable[] temp, int low, int mid, int high) {
        System.arraycopy(arr, low, temp, low, high - low + 1);

        int i = low, j = mid + 1, k = low;
        while (i <= mid && j <= high) {
            if (less(temp[i], temp[j])) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }
        while (i <= mid) {
            arr[k++] = temp[i++];
        }
        while (j <= high) {
            arr[k++] = temp[j++];
        }
    }

    private void insertionSort(Comparable[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Comparable current = arr[i];
            int j = i - 1;
            while (j >= low && less(current, arr[j])) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = current;
        }
    }
}
public class QuickSort extends SortAlgorithm {
    public void sort(Comparable[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(Comparable[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            //��׼����ȷ�±�
            quickSort(arr, low, pi-1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(Comparable[] arr, int low, int high) {
        Comparable pivot = arr[high];
        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            if (less(arr[j], pivot)) {
                i++;
                //һ��һ����С�ķŵ����ȥ
                exchange(arr, i, j);
            }
        }
        exchange(arr, i + 1, high);
        //�����ٽ�ֵ�ŵ��м䣬�������
        return i + 1;
        //���طָ������λ��
    }
}
public class Insertion extends SortAlgorithm {
    private static final int INSERTION_THRESHOLD = 10; // ��ֵ������ȷ����ʱ�л�����������

    public void sort(Comparable[] objs) {
        int N = objs.length;
        for (int i = 1; i < N; i++) {

//����ʵ�����Ż�4
            if (i <= INSERTION_THRESHOLD) {
                // ��С��ģ������ʹ����ͨ��������
                insertionSort(objs, i);
            } else {
                // ʹ�ö��ֲ���ȷ������λ�ã������ٽ�������
                Comparable current = objs[i];

//����ʵ���Ż�2���Ż�3����3��ʵ������Խ�������ܴ��Ӱ��
                int j = binarySearch(objs, current, 0, i - 1);
                for (int k = i; k > j; k--) {
                    objs[k] = objs[k - 1];
                }
                objs[j] = current;
            }
        }
    }

    private void insertionSort(Comparable[] objs, int endIndex) {
        for (int i = 1; i <= endIndex; i++) {
            Comparable current = objs[i];
            int j = i - 1;
            while (j >= 0 && less(current, objs[j])) {
                objs[j + 1] = objs[j];
                j--;
            }
            objs[j + 1] = current;
        }
    }

//��������Ƕ��ֲ��ң��Ż�1

    private int binarySearch(Comparable[] objs, Comparable target, int start, int end) {
        int low = start;
        int high = end;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (less(target, objs[mid])) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
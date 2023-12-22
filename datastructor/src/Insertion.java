public class Insertion extends SortAlgorithm {
    private static final int INSERTION_THRESHOLD = 10; // 阈值，用于确定何时切换到插入排序

    public void sort(Comparable[] objs) {
        int N = objs.length;
        for (int i = 1; i < N; i++) {

//这里实现了优化4
            if (i <= INSERTION_THRESHOLD) {
                // 对小规模子数组使用普通插入排序
                insertionSort(objs, i);
            } else {
                // 使用二分查找确定插入位置，并减少交换次数
                Comparable current = objs[i];

//这里实现优化2和优化3，但3其实并不会对结果产生很大的影响
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

//下面这堆是二分查找，优化1

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
public class ShellSort extends SortAlgorithm {
    public void sort(Comparable[] arr) {
        int n = arr.length;
        int gap = 1;

        // 初始化间隔序列，满足 hi= h * 3 + 1
        while (gap < n / 3) {
            gap = gap * 3 + 1;
        }
        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                Comparable temp = arr[i];
                int j = i;

                while (j >= gap && less(temp, arr[j - gap])) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }

                arr[j] = temp;
            }
            gap = (gap-1) / 3;  // 缩小间隔
        }
    }
}
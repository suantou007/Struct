public class BubbleSort extends SortAlgorithm {
    public void sort(Comparable[] arr) {
        int n = arr.length;
        int end=n-1;
        int j=0;
        boolean swapped =true;
        while(swapped) {
            swapped=false;
           for (j = 0; j < end; j++) {
               if (less(arr[j + 1], arr[j])) {
                   exchange(arr, j, j + 1);
                   swapped=true;
               }
           }
           end--;
        }
    }
}




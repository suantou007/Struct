
public class SortTest {
    // 使用指定的排序算法完成一次排序所需要的时间，单位是纳秒
    public static double time(SortAlgorithm alg, Double[] numbers){
        double start = System.nanoTime();
        alg.sort(numbers);
        double end = System.nanoTime();
        return end - start;
    }
    // 为了避免一次测试数据所造成的不公平，对一个实验完成T次测试，获得T次测试之后的平均时间
    public static double test(SortAlgorithm alg, Double[] numbers, int T)
    {
        Double[] temp=new Double[numbers.length];
        double totalTime = 0;
        for(int i = 0; i < T; i++)
            for (int j = 0; j < numbers.length; j++) {
                temp[j] = numbers[j];
            }
            totalTime += time(alg, temp);
        return totalTime/T;
    }

    // 执行样例，仅供参考。
    static int[] dataLength = new int[9]; // 创建一个大小为 9 的数组，用于存储 8 到 16 次方的结果
    public static void set(){
        for (int i = 8; i <= 16; i++) {
            dataLength[i - 8] = (int) Math.pow(2, i); // 计算 2 的 i 次方，并存储到数组中
        }
    }
    public static double[] ToDoubleArray(int[] intArray) {
        double[] doubleArray = new double[intArray.length];

        for (int i = 0; i < intArray.length; i++) {
            doubleArray[i] = intArray[i];
        }

        return doubleArray;
    }
    public static double[] Square(int[] array) {
        double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
           result[i]=result[i]*result[i];
           //result[i]=result[i]*Math.log(array[i]) / Math.log(2);
        }
        return result;
    }
    public static double[] Log(double[] array) {
        double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Math.log(array[i]) / Math.log(10);
        }
        return result;
    }
    public static void timeclick(SortAlgorithm alg){
        set();
        double[] elapsedTime = new double[dataLength.length];
        double[] bestTime = new double[dataLength.length];
        double[] worstTime = new double[dataLength.length];
        double[] UnevenTime=new double[dataLength.length];

        for(int i = 0; i < dataLength.length; i++) {
            elapsedTime[i] = test(alg, GenerateData.getRandomData(dataLength[i]), 5);
            bestTime[i] = test(alg, GenerateData.getSortedData(dataLength[i]), 5);
            worstTime[i] = test(alg, GenerateData.getInversedData(dataLength[i]), 5);
            UnevenTime[i] = test(alg, GenerateData.getUnevenData(dataLength[i]), 5);
        }

        double[] X= ToDoubleArray(dataLength);
        double[][] Y={elapsedTime,bestTime,worstTime,UnevenTime,Square(dataLength)};
        //LineXYDemo.print("QuickSort",X,Y);
    }


    // 由于测试数据的规模大小，算法性能，机器性能等因素，请同学们耐心等待每次程序的运行。

    public static void main(String[] args) {
        //针对插入排序法的测试
        //SortAlgorithm sort1 = new BubbleSort();
        //SortAlgorithm sort2 = new Insertion();
        //SortAlgorithm sort3 = new SelectionSort();
      //SortAlgorithm sort4 = new ShellSort();
        SortAlgorithm sort5 = new QuickSort();
       //SortAlgorithm sort6 = new MergeSort();
       // timeclick(sort1);
       //timeclick(sort3);
       //timeclick(sort3);
      timeclick(sort5);
       //timeclick(sort1);
    }
}

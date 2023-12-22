
import java.util.Random;

public class GenerateData {
    // 生成一个长度为N的均匀分布的数据序列
    public static Double[] getRandomData(int N){
        Double[] numbers = getSortedData(N);
        shuffle(numbers, 0, numbers.length);
        return numbers;
    }
    // 生成一个长度为N的正序的数据序列
    public static Double[] getSortedData(int N){
        Double[] numbers = new Double[N];
        double t = 0.0;
        for (int i = 0; i < N; i++){
            numbers[i] = t;
            t = t + 1.0/N;
        }
        return numbers;
    }
    private static int[] getSpecialData(int size, double duplicateRate) {
        Random random = new Random();
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            if (random.nextDouble() < duplicateRate) {
                // 生成重复元素
                array[i] = random.nextInt(10);
            } else {
                // 生成非重复元素
                array[i] = i;
            }
        }

        return array;
        //主函数里面有转化的
    }
    // 生成一个长度为N的逆序的数据序列
    public static Double[] getInversedData(int N){
        Double[] numbers = new Double[N];
        double t = 1.0;
        for (int i = 0; i < N; i++){
            t = t - 1.0/N;
            numbers[i] = t;
        }
        return numbers;
    }
    // 将数组numbers中的[left,right)范围内的数据随机打乱
    private static void shuffle(Double[] numbers, int left, int right){
        int N = right - left;
        Random rand = new Random();
        for(int i = 0; i < N; i++){
            int j = i + rand.nextInt(N-i);
            exchange(numbers, i+left, j+left);
        }
    }
    private static void exchange(Double[] numbers, int i, int j){
        double temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
    public static Double[] getUnevenData(int size) {
        Double[] data = new Double[size];
        int halfSize = size / 2;
        int quarterSize = size / 4;
        int eighthSize = size / 8;
        double t= 0.0;
        // 生成 1/2 的数据为 0
        for (int i = 0; i < halfSize; i++) {
            data[i] = t+0;
        }
        // 生成 1/4 的数据为 1
        for (int i = halfSize; i < halfSize + quarterSize; i++) {
            data[i] = t+1;
        }
        // 生成 1/8 的数据为 2
        for (int i = halfSize + quarterSize; i < halfSize + quarterSize + eighthSize; i++) {
            data[i] = t+2;
        }
        // 生成 1/8 的数据为 3
        for (int i = halfSize + quarterSize + eighthSize; i < size; i++) {
            data[i] = t+3;
        }
        // 随机打乱数据的顺序
        shuffle(data,0,size);
        return data;
    }

    public static Double[] getNormalDistributionData(int size, double mean, double stddev) {
        Double[] numbers = new Double[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            double u1 = rand.nextDouble();
            double u2 = rand.nextDouble();
            double z0 = Math.sqrt(-2.0 * Math.log(u1)) * Math.cos(2.0 * Math.PI * u2);
            double value = mean + stddev * z0;
            numbers[i] = value;
        }
        return numbers;
    }
    public static void main(String[] args) {
        Double[] numbers = getRandomData(1000);
        for(int i = 0; i < 100; i++)
            System.out.printf("%5.3f ", numbers[i]);
    }
}

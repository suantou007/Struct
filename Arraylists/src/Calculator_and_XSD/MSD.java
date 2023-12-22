package Calculator_and_XSD;

import Struct.MyQueue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MSD {
    public static void msdSort(String[] arr) {
        if (arr == null || arr.length <= 1) return;
        int mode = 0;
        MyQueue<String> temp = new MyQueue<>(1, arr.length);
        msdSort(arr, temp, 8, mode);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = temp.dequeue(0);
        }
    }
    public static void msdSort(String[] str, MyQueue temp, int digit, int mode) {
        //���ַ�����ȡ���λ���ȷ�
        MyQueue queue = new MyQueue<Integer>(26, str.length);//����27������
        for (int i = 0; i < str.length; i++) {
            int index;//Ͱ���±�
            char c = str[i].charAt(mode);
            if (c >= 'A' && c <= 'Z') {
                index = c - 'A';
            } else if (c >= 'a' && c <= 'z') {
                index = c - 'a';
            } else {
                index = 25;
            }//�����ִ�Сд
            queue.enqueue(index, str[i]);
        }
        if (mode == digit-1) {
            for (int j = 0; j < 26; j++) {
                while (!queue.isEmpty(j)) {temp.enqueue(0, queue.dequeue(j));}
            }//����
        } else {
            mode++;
            for (int j = 0; j < 26; j++) {
                String[] arr = new String[queue.size(j)];
                int k = 0;
                while (!queue.isEmpty(j)) {
                    arr[k] = (String) queue.dequeue(j);
                    k++;
                }
                msdSort(arr, temp, digit, mode);
            }

        }
    }

    public static void msdSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int maxDigit = getNumDigits(arr);  // ��ȡ���λ��
        msdSort(arr, 0, arr.length - 1, maxDigit);
    }

    static int getNumDigits(int[] num) {//�����������λ��
        int max = num[0];//������
        int digits = 0;//λ��
        for (int i = 0; i < num.length; i++) {
            if (num[i] > max) max = num[i];
        }
        while (max / 10 != 0) {
            digits++;
            max = max / 10;
        }
        if (max % 10 != 0) {
            digits++;
        }
        return digits;
    }

    private static void msdSort(int[] arr, int left, int right, int digit) {
        if (left >= right || digit <= 0) {
            return;
        }
        int[] count = new int[10];  // �������飬����ͳ��ÿ��Ͱ��Ԫ�صĸ���
        int[] temp = new int[right - left + 1];  // ��ʱ���飬���ڴ洢�����Ľ��
        int div = (int) Math.pow(10, digit - 1);  // ���ڻ�ȡ��ǰλ���ϵ�����
        // ͳ��ÿ��Ͱ�е�Ԫ�ظ���
        for (int i = left; i <= right; i++) {
            int num = (arr[i] / div) % 10;
            count[num]++;
        }
        // ����ÿ��Ͱ��Ԫ���ڽ�������е���ʼλ��
        int[] startIndex = new int[10];
        int prevCount = 0;
        for (int i = 0; i < 10; i++) {
            startIndex[i] = prevCount;
            prevCount += count[i];
        }
        // ��Ԫ�ذ��յ�ǰλ�����䵽��Ӧ��Ͱ��
        //�ֶ���Ͱ
        for (int i = left; i <= right; i++) {
            int num = (arr[i] / div) % 10;
            temp[startIndex[num]] = arr[i];
            startIndex[num]++;
        }
        // �������Ľ�����ƻ�ԭ����
        System.arraycopy(temp, 0, arr, left, temp.length);

        // ��ÿ��Ͱ�е�Ԫ�صݹ��������
        for (int i = 0; i < 10; i++) {
            int bucketLeft = left + startIndex[i] - count[i];
            int bucketRight = left + startIndex[i] - 1;
            msdSort(arr, bucketLeft, bucketRight, digit - 1);
        }

    }
    public static int[] convertToIntArray(String[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = Integer.parseInt(arr[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        String filePath1 = "src/radixSort1.txt";
        String filePath2 = "src/radixSort2.txt"; // �滻Ϊ����ļ�·��

        try {
            String content1 = Files.readString(Paths.get(filePath1));
            int[] arr1 = convertToIntArray(content1.split("\\s+"));
            msdSort(arr1);
            for (int i = 0; i < arr1.length; i++) {
                System.out.println(arr1[i]);
            }
            String content2 = Files.readString(Paths.get(filePath2));
            String[] arr2 = content2.split("\\s+");
            msdSort(arr2);
            for (int i = 0; i < arr2.length; i++) {
                System.out.println(arr2[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
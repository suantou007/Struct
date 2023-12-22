package Calculator_and_XSD;

import Struct.MyQueue;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LSD {
    public static void LSD(String[] str) {
        //���ַ�����ȡ���λ���ȷ�
        MyQueue queue = new MyQueue<Integer>(27, str.length);//����27������
        //27��Ͱ�����е�27��Ͱ�����洢����ĸ����������ַ��������ִ�Сд
        int digits = str[0].length();//�ȳ��ַ��ĳ���
        int mode = 1;
        while (digits != 0) {
            for (int i = 0; i < str.length; i++) {
                int index;//Ͱ���±�
                char c=str[i].charAt(digits - 1);
                if (c >= 'A' && c <= 'Z') {
                    index = c - 'A';
                } else if (c >= 'a' && c <= 'z') {
                    index = c - 'a';
                } else {
                    index = 26;
                }//�����ִ�Сд
                queue.enqueue(index, str[i]);
                //��Ͱ����
            }
            int k = 0;
            for (int j = 0; j < 27; j++) {
                while (!queue.isEmpty(j)) {
                    str[k] = (String) queue.dequeue(j);
                    k++;
                }
            }//����
            digits--;//λ����
        }
    }
    public static void LSD(int[] num) {
        //�����ֲ������λ���ȷ�����
        MyQueue queue = new MyQueue(10, num.length);//����10������
        int digits = getNumDigits(num);
        int mode = 1;
        while (digits != 0) {
            for (int i = 0; i < num.length; i++) {
                queue.enqueue((num[i] / mode) % 10, num[i]);
                //��Ͱ����,(num[i]/mode)%10��ʾȡ��λ��
            }
            int k = 0;
            for (int j = 0; j < 10; j++) {
                while (!queue.isEmpty(j)) {
                    num[k] = (int) queue.dequeue(j);
                    k++;
                }
            }//����
            digits--;//λ����
            mode = mode * 10;
        }
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
                PrintWriter pw = new PrintWriter("src/output.txt"); // ��������ļ���PrintWriterʵ��
                String content1 = Files.readString(Paths.get(filePath1));
                pw.println("�������򲿷�");
                int[] arr1 = convertToIntArray(content1.split("\\s+"));
                LSD(arr1);
                for (int i = 0; i < arr1.length; i++) {
                    pw.print(arr1[i]+" ");
                }
                pw.println();
                pw.println("�ַ����򲿷�");
                String content2 = Files.readString(Paths.get(filePath2));
                String[] arr2 = content2.split("\\s+");
                LSD(arr2);
                for (int i = 0; i < arr2.length; i++) {
                   pw.println(arr2[i]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}

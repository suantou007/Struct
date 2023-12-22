package Calculator_and_XSD;

import Struct.MyQueue;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LSD {
    public static void LSD(String[] str) {
        //对字符串采取最低位优先法
        MyQueue queue = new MyQueue<Integer>(27, str.length);//分配27个队列
        //27个桶，其中第27个桶用来存储除字母以外的其他字符，不区分大小写
        int digits = str[0].length();//等长字符的长度
        int mode = 1;
        while (digits != 0) {
            for (int i = 0; i < str.length; i++) {
                int index;//桶的下标
                char c=str[i].charAt(digits - 1);
                if (c >= 'A' && c <= 'Z') {
                    index = c - 'A';
                } else if (c >= 'a' && c <= 'z') {
                    index = c - 'a';
                } else {
                    index = 26;
                }//不区分大小写
                queue.enqueue(index, str[i]);
                //按桶分配
            }
            int k = 0;
            for (int j = 0; j < 27; j++) {
                while (!queue.isEmpty(j)) {
                    str[k] = (String) queue.dequeue(j);
                    k++;
                }
            }//出队
            digits--;//位上移
        }
    }
    public static void LSD(int[] num) {
        //对数字采用最低位优先法排序
        MyQueue queue = new MyQueue(10, num.length);//分配10个队列
        int digits = getNumDigits(num);
        int mode = 1;
        while (digits != 0) {
            for (int i = 0; i < num.length; i++) {
                queue.enqueue((num[i] / mode) % 10, num[i]);
                //按桶分配,(num[i]/mode)%10表示取的位数
            }
            int k = 0;
            for (int j = 0; j < 10; j++) {
                while (!queue.isEmpty(j)) {
                    num[k] = (int) queue.dequeue(j);
                    k++;
                }
            }//出队
            digits--;//位上移
            mode = mode * 10;
        }
    }
    static int getNumDigits(int[] num) {//获得最大的数的位数
        int max = num[0];//最大的数
        int digits = 0;//位数
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
            String filePath2 = "src/radixSort2.txt"; // 替换为你的文件路径
            try {
                PrintWriter pw = new PrintWriter("src/output.txt"); // 创建输出文件的PrintWriter实例
                String content1 = Files.readString(Paths.get(filePath1));
                pw.println("数字排序部分");
                int[] arr1 = convertToIntArray(content1.split("\\s+"));
                LSD(arr1);
                for (int i = 0; i < arr1.length; i++) {
                    pw.print(arr1[i]+" ");
                }
                pw.println();
                pw.println("字符排序部分");
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

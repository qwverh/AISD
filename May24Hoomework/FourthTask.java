package May24Hoomework;

import java.util.Arrays;

public class FourthTask {
    public static void main(String[] args) {
        int[] mas1 = {15, 5, 11, 10, 12,122};
        int s = 100;
        Arrays.sort(mas1);
        int sum = 0;
        int count = 0;
        for (int i = 0; i < mas1.length ; i++) {
            if(sum + mas1[i] < s){
                sum += mas1[i];
                count++;
            }else{
                System.out.println(count);
                break;
            }
        }
    }
}

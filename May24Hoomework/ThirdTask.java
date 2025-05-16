package May24Hoomework;

import java.util.Arrays;
import java.util.Comparator;

public class ThirdTask {
    public String largestNumber(int[] nums) {
        String[] strNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strNums[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strNums, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                String order1 = a + b;
                String order2 = b + a;
                return order2.compareTo(order1);
            }
        });
        if (strNums[0].equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (String num : strNums) {
            sb.append(num);
        }
        return sb.toString();
    }

    public static void main(String[] args) {

    }
}

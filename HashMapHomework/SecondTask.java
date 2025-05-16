package HashMapHomework;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;

public class SecondTask {
    public static void main(String[] args) {
        int[] mas = {1, 2, 4, 5, 5, 4, 5};
        System.out.println(result(mas, 11));

    }
    public static boolean result(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k < 1) {
            return false;
        }

        Map<Integer, Integer> numToIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (numToIndex.containsKey(num) && abs(i - numToIndex.get(num)) <= k) {
                return true;
            }
            numToIndex.put(num, i);
        }
        return false;
    }
}

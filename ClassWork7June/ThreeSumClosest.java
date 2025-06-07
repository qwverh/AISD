package ClassWork7June;

import java.util.Arrays;

public class ThreeSumClosest {
    public static int[] threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int closestSum = Integer.MAX_VALUE;
        int[] result = new int[3];

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int currentDiff = Math.abs(sum - target);


                if (sum == target) {
                    return new int[]{nums[i], nums[left], nums[right]};
                }


                if (currentDiff < Math.abs(closestSum - target)) {
                    closestSum = sum;
                    result[0] = nums[i];
                    result[1] = nums[left];
                    result[2] = nums[right];
                }

                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        int[] result = threeSumClosest(nums, target);

        System.out.println("Ближайшая тройка: [" + result[0] + ", " + result[1] + ", " + result[2] + "]");
        System.out.println("Сумма: " + (result[0] + result[1] + result[2]));
    }
}

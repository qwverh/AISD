import java.util.Arrays;

public class ClosestPairSum {
    public static int[] findClosestPair(int[] arr, int target) {
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length - 1;
        int minDiff = Integer.MAX_VALUE;
        int[] bestPair = new int[2];

        while (left < right) {
            int currentSum = arr[left] + arr[right];
            int currentDiff = Math.abs(currentSum - target);

            if (currentDiff < minDiff) {
                minDiff = currentDiff;
                bestPair[0] = arr[left];
                bestPair[1] = arr[right];
            }


            if (currentSum < target) {
                left++;
            } else if (currentSum > target) {
                right--;  
            } else {
                return new int[]{arr[left], arr[right]};  // Идеальная пара
            }
        }
        return bestPair;
    }

    public static void main(String[] args) {
        int[] arr = {10, 22, 28, 29, 30, 40};
        int target = 54;
        int[] result = findClosestPair(arr, target);

        System.out.println("Ближайшая пара: [" + result[0] + ", " + result[1] + "]");
        System.out.println("Сумма: " + (result[0] + result[1]));
    }
}

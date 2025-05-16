package HashMapHomework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThirdTask {
    public static int[] intersect(int[] nums1, int[] nums2) {
        // Подсчет частот для первого массива
        Map<Integer, Integer> freqMap1 = new HashMap<>();
        for (int num : nums1) {
            freqMap1.put(num, freqMap1.getOrDefault(num, 0) + 1);
        }

        // Подсчет частот для второго массива и нахождение пересечения
        Map<Integer, Integer> freqMap2 = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for (int num : nums2) {
            if (freqMap1.containsKey(num) && freqMap1.get(num) > 0) {
                result.add(num);
                freqMap1.put(num, freqMap1.get(num) - 1);
            }
        }


        int[] intersection = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            intersection[i] = result.get(i);
        }

        return intersection;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 4, 2, 2, 4, 1};
        int[] nums2 = {2, 2, 4, 5};
        int[] result = intersect(nums1, nums2);

        // Вывод результата
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
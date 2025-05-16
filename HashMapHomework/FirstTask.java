package HashMapHomework;

import java.util.HashMap;
import java.util.Map;

public class FirstTask {
    public static void main(String[] args) {
        String str = "Peach Peach peach banana apple apple mama";
        count(str);
    }

    public static void count(String str) {
        String[] mas = str.split(" ");
        Map<String, Integer> map = new HashMap<>();
        for (String x : mas) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}

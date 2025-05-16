package May24Hoomework;

public class FirstTask {
    public static void main(String[] args) {

        int[] mas = {1,2,3,4};
        int left = 0;
        int right = mas.length - 1;
        while(left < right ) {
            int mid = left + ((right - left) / 2) + 1;
            System.out.println("MID: " + mid);
            if (mas[mid - 1] < mas[mid]) {
                right = mid - 1;
            } else if (mas[mid - 1] > mas[mid]) {
                left = mid ;
            }
        }
        System.out.println(left);
    }
}

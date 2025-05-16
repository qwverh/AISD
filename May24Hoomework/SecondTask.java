package May24Hoomework;

public class SecondTask {
    public static void main(String[] args) {
        int[] mas1 = {5, 4, 3, 2, 1, 7, 77, 87};

        String s1 = "the sky is blue";
        String s2 = "blue is sky the";
        String[] masS1 = s1.split(" ");
        String[] masS2 = s2.split(" ");
        StringBuilder rS1 = new StringBuilder();
        for (int i = masS1.length - 1; i >= 0; i--) {
            rS1.append(masS1[i] + " ");
        }

        rS1.toString();
        System.out.println(rS1);
    }
}

package May31Homework;

public class KaratsubaBinaryMultiplication {

    // Основной метод умножения Карацубы
    public static long karatsubaMultiply(long x, long y) {
        // Базовые случаи
        if (x == 0 || y == 0) return 0;
        if (x == 1) return y;
        if (y == 1) return x;

        // Определяем количество битов
        int n = Math.max(Long.toBinaryString(x).length(), Long.toBinaryString(y).length());
        if (n % 2 != 0) n++;

        // Разбиваем числа на половинки
        long half = (long) Math.pow(2, n / 2);
        long a = x >> (n / 2);
        long b = x & (half - 1);
        long c = y >> (n / 2);
        long d = y & (half - 1);

        // Рекурсивные вызовы
        long ac = karatsubaMultiply(a, c);
        long bd = karatsubaMultiply(b, d);
        long aPlusB = binaryAdd(a, b);
        long cPlusD = binaryAdd(c, d);
        long abcd = karatsubaMultiply(aPlusB, cPlusD);

        // Вычисляем ad + bc через вычитание
        long adPlusBc = binarySubtract(binarySubtract(abcd, ac), bd);

        // Собираем результат
        return binaryAdd(binaryAdd(ac << n, adPlusBc << (n / 2)), bd);
    }

    // Метод для двоичного сложения (имитирует поразрядное сложение)
    public static long binaryAdd(long a, long b) {
        while (b != 0) {
            long carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }
        return a;
    }

    // Метод для двоичного вычитания (имитирует поразрядное вычитание)
    public static long binarySubtract(long a, long b) {
        while (b != 0) {
            long borrow = (~a) & b;
            a = a ^ b;
            b = borrow << 1;
        }
        return a;
    }

    public static void main(String[] args) {
        long num1 = 0b1101; // 13
        long num2 = 0b1010; // 10

        long product = karatsubaMultiply(num1, num2);
        System.out.println("Результат: " + Long.toBinaryString(product) +
                " (" + product + ")");
    }
}
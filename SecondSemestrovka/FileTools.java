package SecondSemestrovka;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class FileTools {
    public static void generate() {
        for (int i = 1; i <= 100; i++) {
            try (OutputStream fos = new FileOutputStream("test" + i + ".txt")) {
                Random random = new Random();
                StringBuilder sb = new StringBuilder();
                for (int j = 1; j <= i * 100; j++) {
                    sb.append(random.nextInt(100000)).append(";");
                }
                fos.write(sb.toString().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static int[] read(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String file = reader.readLine();
            String[] array = file.split(";");
            int[] result = new int[array.length];
            for (int i = 0; i < array.length; i++) {
                result[i] = Integer.parseInt(array[i]);
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void write(String fileName) {
        try (OutputStream fos = new FileOutputStream(fileName)) {
            StringBuilder sb = new StringBuilder();
            sb.append("№;Size;TimeAdd(ns);TimeDelete(ns);TimeSearch(ns)\n");

            for (int i = 1; i <= 100; i++) {
                Random r = new Random();

                int[] arrayInput = read("test" + i + ".txt");
                B_Tree b_tree = new B_Tree(5);

                for (int value : arrayInput) {
                    b_tree.insert(value);
                }

                // Измерение времени вставки
                long totalTimeAdd = 0;
                for (int k = 0; k < 1000; k++) {
                    int val = r.nextInt(100000);
                    long start = System.nanoTime();
                    b_tree.insert(val);
                    long end = System.nanoTime();
                    totalTimeAdd += (end - start);
                    b_tree.delete(val); // Удаляем после вставки, чтобы дерево не росло бесконечно
                }

                // Измерение времени удаления
                long totalTimeDelete = 0;
                for (int k = 0; k < 1000; k++) {
                    int val = arrayInput[r.nextInt(arrayInput.length)];
                    b_tree.insert(val); // Вставляем перед удалением, чтобы точно был в дереве
                    long start = System.nanoTime();
                    b_tree.delete(val);
                    long end = System.nanoTime();
                    totalTimeDelete += (end - start);
                }

                // Измерение времени поиска
                long totalTimeSearch = 0;
                for (int k = 0; k < 1000; k++) {
                    int val = arrayInput[r.nextInt(arrayInput.length)];
                    long start = System.nanoTime();
                    b_tree.search(val);
                    long end = System.nanoTime();
                    totalTimeSearch += (end - start);
                }

                long avgAdd = totalTimeAdd / 1000;
                long avgDelete = totalTimeDelete / 1000;
                long avgSearch = totalTimeSearch / 1000;

                sb.append(i).append(";")
                        .append(arrayInput.length).append(";")
                        .append(avgAdd).append(";")
                        .append(avgDelete).append(";")
                        .append(avgSearch).append("\n");
            }

            fos.write(sb.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

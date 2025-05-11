package AlgotithmDijkstra;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static AlgotithmDijkstra.Dijkstra.generateGraph;
import static AlgotithmDijkstra.Dijkstra.runDijkstra;

public class Main {
    public static void main(String[] args) throws IOException {
        int numFiles = 100; //количество созданных файлов
        int startV = 100; //стартовое количество вершин
        int stepV = 100; //
        double edgeRatio = 5;
        //для того чтобы проверить работоспособность алгоритма на разных входных данных(количество вершин,
        //а также множество ребер) необохдимо изменить переменную edgeRatio в зависимости от условия для
        //сложности проверки: для разреженного графа E = qV(q ∈ [1, 10)),
        //для более плотного графа E = qV(q >= 10).

        //при запуске основного класса Main перезаписываются результаты в 100 файлов, а также
        //обновляются результаты в сводном файле summary.scv
        new File("dynamic_graphs").mkdir();

        // Создаём сводный файл
        PrintWriter summaryWriter = new PrintWriter(new FileWriter("dynamic_graphs/summary.csv"));
        summaryWriter.println("Vertices,Iterations,Time(ms)"); // Заголовок

        for (int i = 0; i < numFiles; i++) {
            int V = startV + i * stepV;
            int E = (int) (V * edgeRatio);

            System.out.printf("Генерация графа %d: V = %d, E = %d\n", i+1, V, E);
            List<List<Dijkstra.Edge>> graph = generateGraph(V, E);

            String filename = String.format("dynamic_graphs/graph_%02d.txt", i+1);
            runDijkstra(graph, 0, filename, summaryWriter);
        }

        summaryWriter.close();
    }
}

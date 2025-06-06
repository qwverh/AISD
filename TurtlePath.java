public class TurtlePath {
    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;    // количество строк
        int n = grid[0].length; // количество столбцов

        // Создаем таблицу для хранения минимальных сумм
        int[][] dp = new int[m][n];

        // Инициализируем начальную точку
        dp[0][0] = grid[0][0];

        // Заполняем первую строку (движение только вправо)
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }

        // Заполняем первый столбец (движение только вниз)
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        // Заполняем остальные ячейки таблицы
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // Минимальная сумма - минимум из верхней и левой ячейки + текущее значение
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }

        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        System.out.println("Минимальная сумма пути: " + minPathSum(grid));
    }
}
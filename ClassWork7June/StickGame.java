package ClassWork7June;

import java.util.*;

public class StickGame {
    public static int determineWinner(int n, Map<Integer, List<Integer>> moves) {
        boolean[] dp = new boolean[n + 1];
        dp[0] = false;

        for (int i = 1; i <= n; i++) {
            dp[i] = false;
            for (int move : moves.getOrDefault(i, Collections.emptyList())) {
                if (move <= i && !dp[i - move]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n] ? 1 : 2;
    }

    public static void main(String[] args) {
        int n = 5;
        Map<Integer, List<Integer>> moves = new HashMap<>();
        moves.put(1, Arrays.asList(1));
        moves.put(2, Arrays.asList(1, 2));
        moves.put(3, Arrays.asList(1, 2, 3));
        moves.put(4, Arrays.asList(1, 2));
        moves.put(5, Arrays.asList(1, 2, 3));

        int winner = determineWinner(n, moves);
        System.out.println("Выиграет игрок " + winner);
    }
}
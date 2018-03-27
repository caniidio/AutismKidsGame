package humazed.github.com.autismkidsgame.puzzle.utils;

import java.util.Random;

/**
 * Puzzle
 * Created by ZQ on 2016/3/24.
 */
public class GlobalUtils {
    /**
     * @param total
     * @return
     */
    public static int[] getRamdomList(int total) {
        int[] result = new int[total];
        for (int i = 0; i < total; i++) {
            result[i] = i;
        }

        Random random = new Random();
        for (int i = 0; i < total; i++) {
            int p = random.nextInt(total);
            int temp = result[i];
            result[i] = result[p];
            result[p] = temp;
        }

        random = null;
        return result;
    }

}

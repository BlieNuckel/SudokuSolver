import java.util.*;

public class Main {

    public static void main(String[] args) {
        Integer[][] table;
        table = new Integer[][]{
                {0, 7, 0, 0, 0, 0, 3, 0, 0},
                {0, 4, 0, 0, 5, 3, 9, 0, 0},
                {9, 0, 0, 0, 0, 8, 0, 0, 0},
                {0, 0, 6, 0, 0, 0, 0, 0, 4},
                {3, 0, 0, 0, 4, 9, 0, 0, 0},
                {0, 0, 5, 0, 0, 0, 0, 6, 0},
                {0, 0, 0, 1, 0, 0, 2, 0, 0},
                {0, 5, 0, 0, 0, 0, 7, 0, 0},
                {0, 0, 0, 0, 7, 0, 0, 5, 1},
        };

        Integer[][] solvedTable = solver(table);
        for (Integer[] i: solvedTable) {
            System.out.println(Arrays.toString(i));
        }
    }

    private static Integer[][] solver(Integer[][] table) {
        Map<Integer, List<int[]>> boxDict = Constraints.boxConstructor();
        int currentField = 0;
        List<int[]> tiles = tableParser(table);
        boolean accepted = false;
        boolean endOfTable;
        int currentValue;

        do {
            endOfTable = false;
            int[] currentIndex = tiles.get(currentField);
            currentValue = table[currentIndex[0]][currentIndex[1]];
            if (currentField == tiles.size() - 1)
                endOfTable = true;
            while (currentValue < 9) {
                accepted = false;
                table[currentIndex[0]][currentIndex[1]] = ++currentValue;
                boolean row = Constraints.row(table, currentIndex[0]);
                boolean col = Constraints.col(table, currentIndex[1]);
                boolean box = Constraints.box(table, currentIndex, boxDict);
                if (row & col & box) {
                    accepted = true;
                    break;
                }
            }
            if (accepted) {
                if (currentField < tiles.size()) {
                    currentField++;
                }
            } else {
                table[currentIndex[0]][currentIndex[1]] = 0;
                currentField--;
            }
        } while (!accepted | !endOfTable);
        return table;
    }

    private static List<int[]> tableParser(Integer[][] table) {
        List<int[]> tiles = new ArrayList<>();

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] == 0) {
                    tiles.add(new int[]{i, j});
                }
            }

        }
        return tiles;
    }
}

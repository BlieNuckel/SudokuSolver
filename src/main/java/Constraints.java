import java.util.*;

public class Constraints {

    public static final List<Integer> DEFAULT_VALUES = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    public static Map<Integer, List<int[]>> boxConstructor() {
        // Construct 9 lists with a coordinate overview of each box, so the appropriate box can be checked
        Map<Integer, List<int[]>> boxDict = new HashMap<>(); // dict to store box info in
        int box = 1;
        for (int row = 0; row <= 6; row += 3) { // for loop to construct row boxes
            int rowMax = row + 2; // creating upper limit from loop, to make row index change every third box
            for (int col = 0; col <= 6; col += 3) { // for loo to construct column boxes
                int colMax = col + 2; // creating upper limit from loop, to make col index change every third box
                List<int[]> tempList = new ArrayList<>(); // temp list for storing box indexes in
                for (int i = row; i <= rowMax; i++) { // for loop managing current row index
                    for (int j = col; j <= colMax; j++) { // for loop managing current col index
                        tempList.add(new int[]{i, j}); // array added to list, with row, col indexes
                    }
                }
                boxDict.put(box, tempList);
                box++;
            }
        }
        return boxDict;
    }

    // check that row fits with sudoku rules
    public static boolean row(Integer[][] table, Integer row) {
        boolean pass = true; // Bool to check if row fits sudoku rules
        List<Integer> copyDefVal = new ArrayList<>(DEFAULT_VALUES);
        for (int i : table[row]) { // loop through integers in row
            if (i == 0)
                continue;
            if (copyDefVal.contains(i)) {
                copyDefVal.remove(Integer.valueOf(i)); // remove number in field from list
            } else {
                pass = false; // if two of the same numbers is found this will be triggered
                break;
            }
        }
        return pass;
    }

    // check that column fits with sudoku rules
    public static boolean col(Integer[][] table, Integer col) {
        boolean pass = true; // bool to check if column fits sudoku rules
        List<Integer> column = new ArrayList<>(); // store values from column
        List<Integer> copyDefVal = new ArrayList<>(DEFAULT_VALUES);
        for (Integer[] row: table)
            column.add(row[col]); // collects the values from each of the rows, to construct the column
        for (int i : column) {

            if (i == 0)
                continue;
            if (copyDefVal.contains(i)) {
                copyDefVal.remove(Integer.valueOf(i)); // remove number in field from list
            } else {
                pass = false; // if two of the same number is found this will be triggered
                break;
            }
        }
        return pass;
    }

    // check that box fits with sudoku rules
    public static boolean box(Integer[][] table, int[] field, Map<Integer, List<int[]>> boxDict) {
        boolean pass = true; // bool to check if box fits sudoku rules

        // Compares the value of each field that is in the box
        int counter = 1;
        List<Integer> copyDefVal = new ArrayList<>(DEFAULT_VALUES);
        for (int i = 0 ; i <= 6 ; i += 3) {
            int iMax = i + 2;
            for (int j = 0; j <= 6 ; j += 3) {
                int jMax = j + 2;
                if (i <= field[0] & field[0] <= iMax) {
                    if (j <= field[1] & field[1] <= jMax) {
                        for (int[] index: boxDict.get(counter)) {
                            if (table[index[0]][index[1]] == 0)
                                continue;
                            if (copyDefVal.contains(table[index[0]][index[1]])) {
                                copyDefVal.remove(table[index[0]][index[1]]);
                            } else {
                                pass = false;
                                break;
                            }
                        }
                    }
                }
                counter++;
            }
        }
        return pass;
    }
}

package com.example.demo.leetCode.array;

/**
 * 36. 有效的数独
 *   判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 *
 *   数字 1-9 在每一行只能出现一次。
 *   数字 1-9 在每一列只能出现一次。
 *   数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 */
public class Sudoku {

    public static boolean isValidSudoku(char[][] board){
        if (board == null || board.length != 9) {
            return false;
        }
        int[] existMap = new int[9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int intVal = board[i][j] - '1';
                if (intVal >= 0 && intVal <= 8) {
                    int index = (1<<j) | (1<<(i+9)) | (1<<(j/3 + i/3*3 + 18));

                    int oldVal = existMap[intVal];
                    if ((oldVal & index) == 0) {
                        existMap[intVal] = oldVal | index;
                    }else{
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'},
        };
        System.out.println(isValidSudoku(board));
        
    }

}

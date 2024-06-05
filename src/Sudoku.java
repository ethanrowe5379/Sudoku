import java.sql.Time;
import java.util.Arrays;
import java.util.Random;

public class Sudoku {

    int[][] gameBoard = new int[9][9];
    int[][][] row = new int[9][9][2];
    int[][][] col = new int[9][9][2];
    int[][][] box = new int[9][9][2];

    public Sudoku() {
        System.out.println("Initialising Board");
        this.initialiseGameBoard();
        generateGameBoard();
    }

    private void initialiseGameBoard(){
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++){
                gameBoard[y][x] = 0;
                row[y][x][0] = y;
                row[y][x][1] = x;
                col[x][y][0] = y;
                col[x][y][1] = x;

                if(y < 3 && y > 0){
                    box[y][x][0] = 0;
                }else if (y < 6 && y > 2){
                    box[y][x][0] = 3;
                }else if (y < 9 && y > 5){
                    box[y][x][0] = 6;
                }

                if(x < 3 && x > 0){
                    box[y][x][1] = 0;
                }else if (x < 6 && x > 2){
                    box[y][x][1] = 3;
                }else if (x < 9 && x > 5){
                    box[y][x][1] = 6;
                }
            }
        }
    }

    private void printGameBoard(){
        for(int[] i: this.gameBoard){
            for(int elem: i){
                System.out.print(elem + " ");
            }
            System.out.print("\n");
        }
    }

    public boolean x_found(int val, int[][] unit){
        boolean results = false;
            for(int i = 0; i < 9; i++){
                int iR = unit[i][0];
                int iC = unit[i][1];
                results = val == gameBoard[iR][iC];
                if(results){
                    break;
                }
            }
        return results;
    }

    public void generateGameBoard(){
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        int val = 0;
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++){
                val = getRandom(x, y, r);
                gameBoard[y][x] = val;
            }
            printGameBoard();
        }
    }

    public int getRandom(int x, int y, Random r){
        int temp = 0;
        while(true){
            temp = r.nextInt(9) + 1;
            if(!checkBox(temp, box[y][x])) {
                break;
            }
        }
        return temp;
    }

    public boolean checkBox(int val, int[] unit){
        boolean results = false;
        int iR = unit[0];
        int iC = unit[1];
        System.out.println(iR + ", "+ iC);
        for(int j = 0; j < 3; j++){
            for(int k = 0; k < 3; k++) {
                if (val == gameBoard[iR + j][iC + k]) {
                    results = true;
                    break;
                }
            }
        }
        return results;
    }
}

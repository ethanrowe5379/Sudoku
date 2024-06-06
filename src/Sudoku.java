import java.util.Random;

public class Sudoku {

    public int[][] gameBoard = new int[9][9];
    private final int[][][] row = new int[9][9][2];
    private final int[][][] col = new int[9][9][2];
    private final int[][][] box = new int[9][9][2];

    /*
        Sudoku() - Constructor
        Initialises and generates the game board.
    */
    public Sudoku() {
        System.out.println("Initialising Board");
        this.initialiseGameBoard();
        generateGameBoard();
    }

    /*
        initialiseGameBoard()
        Private Method due to only being used within this class.
        Will Set all values within the game board to 0
        Sets the row, col and box indexes too.
        This is to make it easier to search for values.
     */
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

    /*
        printGameBoard()
        As it says on the tin. Will print the game board to the console.
        Utilising a nested for each loop for each element in a row and subsequently each column.
     */
    private void printGameBoard(){
        for(int[] i: this.gameBoard){
            for(int elem: i){
                System.out.print(elem + " ");
            }
            System.out.print("\n");
        }
    }

    /*
        x_found(int val, int[][] unit)
        int val - the value to be checked
        int[][] unit - the row or col to be checked.
        Derived from the book "Sudoku programming with C" with minor modifications
        Couldn't get the box check to work so only works for checking row and col values.
     */
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

    /*
        generateGameBoard()

     */
    public void generateGameBoard(){
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        int val;
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++){
                val = getRandom(x, y, r);
                //If returned Value is 0, checking for valid value has timed out
                if(val == 0){
                    resetRow(y); //Resets entire row
                    x = -1; // Resets the loop
                    continue;
                }
                gameBoard[y][x] = val;
            }
            printGameBoard();
        }
    }

    private void resetRow(int row){
        for(int x = 0; x < 9; x++) {
            gameBoard[row][x] = 0;
        }
    }

    private int getRandom(int x, int y, Random r){
        int temp;
        int counter = 0;
        while(true){
            counter++;
            temp = r.nextInt(9) + 1;
            if(!checkBox(temp, box[y][x]) && !x_found(temp, col[x]) && !x_found(temp, row[y])) {
                break;
            }
            if(counter == 9){
                return 0;
            }
        }
        return temp;
    }

    private boolean checkBox(int val, int[] unit){
        boolean results = false;
        int iR = unit[0];
        int iC = unit[1];
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

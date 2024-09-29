import java.util.Scanner;

class Sudoku{

    public static void main(String[] args) {
        int[][] board = new int[9][9];
        System.out.println("\nEnter values of the board (Enter 0 for empty cells)");
        try (Scanner sc = new Scanner(System.in)) {
            for(int i=0; i<9; i++){
                System.out.println("\nEnter values for row:"+i);
                for(int j=0;j<9;j++){
                    int test = sc.nextInt();
                    if(test<0 || test>9){
                        j--;
                        System.err.println("Enter a value between 0-9");
                    }
                    else{
                        board[i][j] = test;
                    }  
                }
            }
            displayBoard(board);
        }
       if(puzzleSolver(board)){
            System.out.println("Solved successfully");
       }
       else{
            System.err.println("Unsolvable board");
       }
       displayBoard(board);
        
    }

    private static void displayBoard(int[][] board){
        for(int i=0; i<9; i++){
            if (i % 3 == 0 && i != 0) {
                System.out.println("------+-------+------");
                }   
            for(int j=0;j<9;j++){
                if (j % 3 == 0 && j != 0) {  
                    System.out.print("| ");
                }
                System.out.print(board[i][j] == 0 ? ". " : board[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private static boolean puzzleSolver(int[][] board){
        if(winCondition(board)){
            return true;
        }
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(board[i][j] == 0){
                    for(int val=1; val<10; val++){
                        if(conditionChecker(board, i, j, val)){
                            board[i][j] = val;
                            if(puzzleSolver(board)){
                                return true;
                            }
                            board[i][j]=0;
                            }
                        }
                        displayBoard(board);
                        return false;
                    }   
                }
            }
        return true;
    }

    private static boolean winCondition(int[][] board){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if (board[i][j] == 0){
                    return false;
                }
                if(!conditionChecker(board, i, j,board[i][j])) {return false;}
            }
        }
        return true;
    }

    private static boolean conditionChecker(int[][] board,int row,int col,int value){
        int subGridX = ((row/3)*3);
        int subGridY = ((col/3)*3);
        for (int i = 0; i < 9; i++) {
            if(board[row][i]==value){
                return false;
            }
            else if(board[i][col]==value){
                return false;
            }
        }
        for(int i=subGridX; i<subGridX+3; i++){
            for(int j=subGridY; j<subGridY+3; j++){
                if(board[i][j] == value)    return false;
            }
        }
        return true;
    }
}
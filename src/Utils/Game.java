package Utils;

import Models.Board;
import Models.Move;
import Models.Player;

import java.util.List;

public class Game {

    char[][] board;
    List<Player> players;
    List<Move> moves;
    Boolean start;
    int currentPlayerIdx;

    public Game(Board board, List<Player> players, List<Move> moves) {
        this.board = board.getGrid();
        this.players = players;
        this.moves = moves;
        this.start = true;
        this.currentPlayerIdx = 0;
    }

    public void printBoard(){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(board[i][j]>='A' && board[i][j]<='Z'){
                    System.out.print(board[i][j] + " ");
                }else{
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println("...........................");
    }

    public void play(){
        printBoard();
        for(Move move : moves){
            if(isValidMove(move)) {
                int x = move.getX()-1;
                int y = move.getY()-1;
                Player currentPlayer = getCurrentPlayer();
                board[x][y] = currentPlayer.getSymbol();
                System.out.println(currentPlayer.getName());
                printBoard();
                checkIfGameOver();
                checkIfPlayerWon(currentPlayer);
            }
        }
    }

    private void checkIfPlayerWon(Player currentPlayer) {
        checkHorizontally(currentPlayer);
        checkVertically(currentPlayer);
        checkDiagonally(currentPlayer);
    }

    private void checkDiagonally(Player currentPlayer) {
        char cur = currentPlayer.getSymbol();
        if(cur==board[0][0] || cur==board[0][2]){
            if(board[1][1] == cur){
                if(board[2][0]==cur || board[2][2]==cur){
                    System.out.println(currentPlayer.getName() + " won!");
                }
            }
        }
    }

    private void checkVertically(Player currentPlayer) {
        for(int i=0; i<board.length; i++){
            char cur = board[0][i];
            if(cur != currentPlayer.getSymbol())
                return;
            for(int j=1; j<board[i].length; j++){
                if(cur != board[j][i]){
                    break;
                }else{
                    if(j==board.length-1){
                        System.out.println(currentPlayer.getName() + " won!");
                        return;
                    }
                }
            }
        }
    }

    private void checkHorizontally(Player currentPlayer) {
        for(int i=0; i<board.length; i++){
            char cur = board[i][0];
            if(cur != currentPlayer.getSymbol())
                return;
            for(int j=1; j<board[i].length; j++){
                if(cur != board[i][j]){
                    break;
                }else{
                    if(j==board[i].length-1){
                        System.out.println(currentPlayer.getName() + " won!");
                        return;
                    }
                }
            }
        }
    }

    private void checkIfGameOver() {
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(board[i][j] == '\u0000'){
                    return;
                }
            }
        }
        System.out.println("Game Over");
    }

    private boolean isValidMove(Move move) {
        int x = move.getX()-1;
        int y = move.getY()-1;
        if(board[x][y] != '\u0000') {
            System.out.println("Invalid Move");
            return false;
        }
        return true;
    }

    private Player getCurrentPlayer() {
        if(start){
            start = false;
            return players.get(currentPlayerIdx);
        }else{
            currentPlayerIdx = (currentPlayerIdx + 1) % players.size();
            return players.get(currentPlayerIdx);
        }
    }
}

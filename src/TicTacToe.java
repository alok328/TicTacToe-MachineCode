import Models.Board;
import Models.Move;
import Models.Player;
import Utils.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Player> players = new ArrayList<>();
        List<Move> moves = new ArrayList<>();
        while (sc.hasNext()){
            String in = sc.nextLine();
            if(in.equals("exit")){
                System.out.println("Exit");
                break;
            }else if(in.charAt(0)>='A' && in.charAt(0)<='Z'){
                System.out.println("Player " + in);
                String[] strings = in.split(" ");
                players.add(new Player(strings[1], strings[0].charAt(0)));
            }else{
                String[] strings = in.split(" ");
                if(strings.length == 2)
                    moves.add(new Move(Integer.parseInt(strings[0]), Integer.parseInt(strings[1])));
                else{
                    System.out.println("Invalid Input");
                    System.exit(-1);
                }
            }
        }
        Board board = new Board(3, 3);
        Game game = new Game(board, players, moves);
        game.play();
    }

}

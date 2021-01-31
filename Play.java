import java.util.Scanner;

public class Play {
  public static void main(String[] args) {

    boolean twoPlayers = true;

    TicTacToe board = new TicTacToe();
    Scanner scan = new Scanner(System.in);
    System.out.println("Welcome to TicTacToe!");
    board.printBoard();
    System.out.println("You may go first. ");

    while (true) {
      System.out.println("Choose a x coordinate position.");
      int xInput = Integer.parseInt(scan.nextLine());
      System.out.println("Choose the y position.");
      int yInput = Integer.parseInt(scan.nextLine());
      if (board.isCellFull(yInput - 1, xInput - 1) == false) {
        board.placeLetter(yInput - 1, xInput - 1);
      } else {
        System.out.println("Position already full.");
        continue;
      }
      board.printBoard();

      // computer move or two player mode for now
      if (twoPlayers == true) {
        board.twoPlayerMode();
      }
      if (board.isGameOver()) {
        break;
      }
      System.out.println("Other player's turn");
    }
    scan.close();
  }
}

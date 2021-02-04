import java.util.*;

public class TicTacToe {
  // for niall to do - print out the board, choose the move, th
  // Static class designated for main - "hello, what difficulty do you want to
  // play (normal or hard)"
  // Scanner "if scan = hard, this" "if scan = normal"

  public static void main(String[] args) {
    Board board = new Board();
    Scanner scan = new Scanner(System.in);
    while (true) {
      board.printBoard();
      System.out.println();
      playerMove(board, scan);
      computerMove(board, "asd");
      board.printBoard();
      System.out.println();
      if (board.isGameOver() || board.checkBoard() == Node.X || board.checkBoard() == Node.O) {
        break;
      }
    }
    scan.close();
  }

  public static void playerMove(Board board, Scanner scan) {
    while (true) {
      System.out.println("Input the x coordinate of your move: ");
      int inputx = Integer.parseInt(scan.nextLine()) - 1;
      System.out.println("Input the y coordinate of your move: ");
      int inputy = Integer.parseInt(scan.nextLine()) - 1;
      if (board.isCellFull(inputx, inputy)) {
        continue;
      }
      board.placeNode(inputx, inputy, Node.O);
      break;
    }
  }

  public static void computerMove(Board board, String difficulty) {
    int[] ai = new int[2];
    if (difficulty.equalsIgnoreCase("-a")) {
      ai = PlayerAI.move(board, Node.X);
    } else {
      ai = PlayerAI.normalAI(board, Node.X); // unfinished function
    }
    board.placeNode(ai[0], ai[1], Node.X);

  }
}
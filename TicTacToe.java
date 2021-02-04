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
      while (true) {
        int inputx = Integer.parseInt(scan.nextLine()) - 1;
        int inputy = Integer.parseInt(scan.nextLine()) - 1;
        if (board.isCellFull(inputx, inputy)) {
          continue;
        }
        board.placeNode(inputx, inputy, Node.O);
        break;
      }
      int[] ai = Minimax.move(board, Node.X);
      board.placeNode(ai[0], ai[1], Node.X);
      board.printBoard();
      System.out.println();
      if (board.isGameOver() || board.checkBoard() == Node.X || board.checkBoard() == Node.O) {
        break;
      }
    }
    scan.close();
  }
}
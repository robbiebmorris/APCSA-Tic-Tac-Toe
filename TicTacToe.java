import java.util.*;

public class TicTacToe {
  public static void main(String[] args) {
    Board board = new Board();
    Scanner scan = new Scanner(System.in);

    System.out.println("Welcome to Tic Tac Toe!");
    while (true) {
      System.out.print("Choose a difficulty: ");
      String difficulty = scan.nextLine();
      board.printBoard();
      int xOrOs = (int) (Math.random() * (2 - 1 + 1) + 1);
      if (xOrOs == 1) {
        System.out.println("By random choice, you go first.");
        playerMove(board, scan);
        board.printBoard();
      } else {
        System.out.println("By random choice, the computer plays first.");
      }

      while (true) {
        System.out.println("Computer: ");

        computerMove(board, difficulty);
        board.printBoard();
        if (board.checkBoard() == Node.X || board.checkBoard() == Node.O) {
          System.out.println("You lost!");
          break;
        } else if (board.isGameOver()) {
          System.out.println("It's a tie!");
          break;
        }

        playerMove(board, scan);
        board.printBoard();
        if (board.checkBoard() == Node.X || board.checkBoard() == Node.O) {
          System.out.println("You win!");
          break;
        } else if (board.isGameOver()) {
          System.out.println("It's a tie!");
          break;
        }
      }
      System.out.println("Play again? (y/n)");
      String playAgain = scan.nextLine();
      if (playAgain.equalsIgnoreCase("y")) {
        for (int row = 0; row < Board.boardSize; row++) {
          for (int col = 0; col < Board.boardSize; col++) {
            board.placeNode(row, col, Node.EMPTY);
          }
        }
        continue;
      } else {
        break;
      }
    }
    scan.close();
  }

  public static void playerMove(Board board, Scanner scan) {
    while (true) {
      System.out.print("Input the y coordinate of your move: ");
      int inputx = Integer.parseInt(scan.nextLine()) - 1;
      System.out.print("Input the x coordinate of your move: ");
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
      ai = PlayerAI.advancedAI(board);
    } else {
      ai = PlayerAI.normalAI(board, Node.X);
    }
    board.placeNode(ai[0], ai[1], Node.X);
  }
}

import java.util.*;

public class Minimax extends Board {

  public Minimax() {

  }

  // Go through all of the possible moves and check if they are possible
  public List<int[]> possibleMoves() {
    List<int[]> moves = new ArrayList<int[]>();
    if (isGameOver()) {
      return moves;
    }
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        if (board[row][col] == Node.EMPTY) {
          int[] newMove = { row, col };
          moves.add(newMove);
        }
      }
    }
    return moves;
  }

  // terminology:
  // terminal node: node with no children
  public static int[] minimax(Board board, int depth, boolean maximising) {

    // undetermined

    return new int[] { 1, 2 };
  }

  // heuristic board evaluation
  public static int evaluateBoard(Board board) {
    int sumX = 0;
    int sumO = 0;
    int countX = 0;
    int countO = 0;

    // check rows
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        if (board.getNode(row, col).getNode() == 'X') {
          countX++;
        }
        if (board.getNode(row, col).getNode() == 'O') {
          countO++;
        }
      }
      sumX += valueCounter(countX, countO, true);
      sumO += valueCounter(countX, countO, false);
      countO = 0;
      countX = 0;
    }

    // check columns
    for (int col = 0; col < boardSize; col++) {
      for (int row = 0; row < boardSize; row++) {
        if (board.getNode(row, col).getNode() == 'X') {
          countX++;
        }
        if (board.getNode(row, col).getNode() == 'O') {
          countO++;
        }
      }
      sumX += valueCounter(countX, countO, true);
      sumO += valueCounter(countX, countO, false);
      countO = 0;
      countX = 0;
    }

    // check right to left diagonal
    for (int i = 0; i < boardSize; i++) {
      if (board.getNode(i, i).getNode() == 'X') {
        countX++;
      }
      if (board.getNode(i, i).getNode() == 'O') {
        countO++;
      }
    }
    sumX += valueCounter(countX, countO, true);
    sumO += valueCounter(countX, countO, false);
    countO = 0;
    countX = 0;

    // check left to right diagonal
    for (int i = 0; i < boardSize; i++) {
      if (board.getNode(i, (boardSize - 1) - i).getNode() == 'X') {
        countX++;
      }
      if (board.getNode(i, (boardSize - 1) - i).getNode() == 'O') {
        countO++;
      }
    }
    sumX += valueCounter(countX, countO, true);
    sumO += valueCounter(countX, countO, false);
    return sumX + sumO;
  }

  // if true, return sumX. if not, return sumO
  public static int valueCounter(int countX, int countO, boolean sumType) {
    int sumO = 0;
    int sumX = 0;
    if (countO == 3) {
      sumO -= 100;
    } else if (countO == 2 && countX == 0) {
      sumO -= 10;
    } else if (countO == 1 && countX == 0) {
      sumO -= 1;
    }
    if (countX == 3) {
      sumX += 100;
    } else if (countX == 2 && countO == 0) {
      sumX += 10;
    } else if (countX == 1 && countO == 0) {
      sumX += 1;
    }
    if (sumType) {
      return sumX;
    }
    return sumO;
  }

  public static void main(String[] args) {
    Board board = new Board();
    board.newBoard();
    board.placeNode(1, 1, true);
    board.placeNode(2, 0, false);
    board.placeNode(0, 2, true);
    board.placeNode(1, 0, false);
    board.placeNode(2, 2, true);
    board.placeNode(0, 1, false);

    // board.placeNode(1, 2, false);
    board.printBoard();
    System.out.println(evaluateBoard(board));
  }
}
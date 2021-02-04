import java.util.*;

public class PlayerAI extends Board {

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

  // get best move given a board
  public static int[] advancedAI(Board board, Node letter) {
    int[] move = new int[2];
    int maxNum = Integer.MIN_VALUE;
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        if (board.getNode(row, col) == Node.EMPTY) {
          board.placeNode(row, col, letter);
          int minimax = minimax(board, 5, false, letter);
          board.placeNode(row, col, Node.EMPTY);
          if (minimax > maxNum) {
            move[0] = row;
            move[1] = col;
            maxNum = minimax;
          }
        }
      }
    }
    return move;
  }

  public static int minimax(Board board, int depth, boolean maximizing, Node letter) {

    int evaluation = evaluateBoard(board);
    // support for if the letter is O. then the evaluation has to be negative,
    // because evaluation function is written in perspective of X player
    if (letter == Node.O) {
      evaluation = -evaluation;
    }
    if (depth == 0 || board.isGameOver() || evaluation > 0) {
      return evaluation;
    }
    int maxNum = Integer.MIN_VALUE;
    int minNum = Integer.MAX_VALUE;

    if (maximizing) {
      for (int row = 0; row < boardSize; row++) {
        for (int col = 0; col < boardSize; col++) {
          if (board.getNode(row, col) == Node.EMPTY) {
            board.placeNode(row, col, letter);
            maxNum = Integer.max(maxNum, minimax(board, depth - 1, maximizing, letter));
            board.placeNode(row, col, Node.EMPTY);
          }
        }
      }
      return maxNum;
    } else {
      for (int row = 0; row < boardSize; row++) {
        for (int col = 0; col < boardSize; col++) {
          if (board.getNode(row, col) == Node.EMPTY) {
            board.placeNode(row, col, letter);
            minNum = Integer.min(minNum, minimax(board, depth - 1, maximizing, letter));
            board.placeNode(row, col, Node.EMPTY);
          }
        }
      }
      return minNum;
    }
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

  public static int[] normalAI(Board board, Node letter) {
    int[] move = new int[2];
    // 1. if a winning move is available, take it
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        if (!board.isCellFull(row, col)) {
          board.placeNode(row, col, letter);
          if (board.checkBoard() == letter) {
            move[0] = row;
            move[1] = col;
            return move;
          }
          board.placeNode(row, col, Node.EMPTY);
        }
      }
    }

    // 2. if the opponent is threatening a winning play, block it.
    Node opponentLetter;
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        if (!board.isCellFull(row, col)) {
          if (letter == Node.O) {
            opponentLetter = Node.X;
          } else {
            opponentLetter = Node.O;
          }
          board.placeNode(row, col, opponentLetter);
          if (board.checkBoard() == opponentLetter) {
            move[0] = row;
            move[1] = col;
            return move;
          }
          board.placeNode(row, col, Node.EMPTY);
        }
      }
    }
    // 3. if the centre square is available, take it
    if (!board.isCellFull(1, 1)) {
      move[0] = 1;
      move[1] = 1;
      return move;
    }
    // 4. else choose randomly between any remaining square
    while (true) {
      int randomX = (int) (Math.random() * 2);
      int randomY = (int) (Math.random() * 2);
      if (!board.isCellFull(randomX, randomY)) {
        move[0] = randomX;
        move[1] = randomY;
        return move;
      }
    }
  }
}
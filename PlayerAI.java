public class PlayerAI extends Board {

  public static int[] advancedAI(Board board) {
    int[] move = new int[2];
    int maxNum = Integer.MIN_VALUE;
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        if (!board.isCellFull(row, col)) {
          board.placeNode(row, col, Node.X);
          int minimax = miniMax(board, 6, false);
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

  public static int miniMax(Board board, int depth, boolean maximizing) {

    int evaluation = evaluateBoard(board);

    if (depth == 0 || board.isGameOver() || Math.abs(evaluation) > 0) {
      return evaluation;
    }

    int maxNum = Integer.MIN_VALUE;
    int minNum = Integer.MAX_VALUE;
    if (maximizing) {
      for (int row = 0; row < boardSize; row++) {
        for (int col = 0; col < boardSize; col++) {
          if (board.getNode(row, col) == Node.EMPTY) {
            board.placeNode(row, col, Node.X);
            maxNum = Math.max(maxNum, miniMax(board, depth - 1, false));
            board.placeNode(row, col, Node.EMPTY);
          }
        }
      }
      return maxNum;
    } else {
      for (int row = 0; row < boardSize; row++) {
        for (int col = 0; col < boardSize; col++) {
          if (board.getNode(row, col) == Node.EMPTY) {
            board.placeNode(row, col, Node.O);
            minNum = Math.min(minNum, miniMax(board, depth - 1, true));
            board.placeNode(row, col, Node.EMPTY);
          }
        }
      }
      return minNum;
    }
  }

  // heuristic board evaluation
  public static int evaluateBoard(Board board) {
    int sum = 0;
    int infX = Node.X.getNode() * boardSize;
    int infO = Node.O.getNode() * boardSize;

    // check rows
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        sum += board.getNode(row, col).getNode();
      }
      if (sum == infX) {
        return 10;
      } else if (sum == infO) {
        return -10;
      }
      sum = 0;
    }

    // check columns
    for (int col = 0; col < boardSize; col++) {
      for (int row = 0; row < boardSize; row++) {
        sum += board.getNode(row, col).getNode();
      }
      if (sum == infX) {
        return 10;
      } else if (sum == infO) {
        return -10;
      }
      sum = 0;
    }

    // check right to left diagonal
    for (int i = 0; i < boardSize; i++) {
      sum += board.getNode(i, i).getNode();
    }
    if (sum == infX) {
      return 10;
    } else if (sum == infO) {
      return -10;
    }
    sum = 0;
    // check left to right diagonal
    for (int i = 0; i < boardSize; i++) {
      sum += board.getNode(i, (boardSize - 1) - i).getNode();
    }
    if (sum == infX) {
      return 10;
    } else if (sum == infO) {
      return -10;
    }
    return 0;
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
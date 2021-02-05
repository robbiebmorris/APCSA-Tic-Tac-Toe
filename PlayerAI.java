public class PlayerAI extends Board {

  // running the minimax algorithm to return a coordinate array of the optimal
  // next move
  public static int[] advancedAI(Board board) {
    int[] move = new int[2];
    int maxNum = Integer.MIN_VALUE;
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        if (!board.isCellFull(row, col)) {
          // make a move on a theoretical board
          board.placeNode(row, col, Node.X);
          int minimax = miniMax(board, 5, false);
          // fix the board back to normal for next iteration of loop
          board.placeNode(row, col, Node.EMPTY);
          // if better value, assign that coordinate to the move array
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

  // mainstream implementation of the minimaxing algorithm
  public static int miniMax(Board board, int depth, boolean maximizing) {

    // run the evaluation of the current board
    int evaluation = evaluateBoard(board);

    // if the depth is 0, the game is over, or the evluation tells us that we win,
    // return that evaluation
    if (depth == 0 || board.isGameOver() || Math.abs(evaluation) > 0) {
      return evaluation;
    }

    int maxNum = Integer.MIN_VALUE;
    int minNum = Integer.MAX_VALUE;
    if (maximizing) {
      for (int row = 0; row < boardSize; row++) {
        for (int col = 0; col < boardSize; col++) {
          if (board.getNode(row, col) == Node.EMPTY) {
            // put theoretical node on board
            board.placeNode(row, col, Node.X);
            // analyze new board, and find if the current maxNum (which is representative of
            // negative infinity at the start) is less than the new move's value
            maxNum = Math.max(maxNum, miniMax(board, depth - 1, false));
            // return board back to normal
            board.placeNode(row, col, Node.EMPTY);
          }
        }
      }
      return maxNum;
    } else {
      for (int row = 0; row < boardSize; row++) {
        for (int col = 0; col < boardSize; col++) {
          if (board.getNode(row, col) == Node.EMPTY) {
            // put theoretical node on board
            board.placeNode(row, col, Node.O);
            // analyze new board, and find if the current minNum (which is representative of
            // positive infinity at the start) is more than the new move's value
            minNum = Math.min(minNum, miniMax(board, depth - 1, true));
            // return board back to normal
            board.placeNode(row, col, Node.EMPTY);
          }
        }
      }
      return minNum;
    }
  }

  // heuristic board evaluation
  public static int evaluateBoard(Board board) {
    // representative of the sum accross any given line of 3
    int sum = 0;
    // the char value of the node * 3
    int infX = Node.X.getNode() * boardSize;
    int infO = Node.O.getNode() * boardSize;

    // return 10 for winning board, -10 for losing board, and 0 for a neutral board

    // check rows for winning board
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

    // check columns for winning board
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

    // check right to left diagonal for winning board
    for (int i = 0; i < boardSize; i++) {
      sum += board.getNode(i, i).getNode();
    }
    if (sum == infX) {
      return 10;
    } else if (sum == infO) {
      return -10;
    }
    sum = 0;

    // check left to right diagonal for winning board
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

  // normal rule based AI
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

public class Board {

  Node[][] board;
  static int boardSize = 3;

  public Board() {
    board = new Node[boardSize][boardSize];
    newBoard();
  }

  public void newBoard() {
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        board[row][col] = Node.EMPTY;
      }
    }
  }

  public void printBoard() {
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        System.out.print(board[row][col].getNode());
      }
      System.out.println();
    }
  }

  /*
   * public String toString()//Adapting the print board method to make it better {
   * String boardOutput = "\n+-----+\n"; for (int i = 0; i < ROWS; i++) {
   * boardOutput += "|"; for (int j = 0; j < COLUMNS; j++) { boardOutput += board
   * [i][j] + "|"; } boardOutput += "\n+-----+\n"; } boardOutput += "\n\n";
   * 
   * return boardOutput; }//end toString method
   */

  public boolean isCellFull(int xPos, int yPos) {
    if (board[xPos][yPos] == Node.EMPTY) {
      return false;
    }
    return true;
  }

  public boolean isGameOver() {
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        if (board[row][col] == Node.EMPTY) {
          return false;
        }
      }
    }
    return true;
  }

  public Node getNode(int row, int col) {
    return this.board[row][col];
  }

  public void placeNode(int row, int col, boolean isX) {
    if (isX) {
      board[row][col] = Node.X;
    } else {
      board[row][col] = Node.O;
    }
  }

  // for niall to do
  public Node checkBoard() {// This checks who has won the game
    boolean fullBoard = true;

    // Check if the board is full and nobody won.
    // Note: The board is not full in any space is " "
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if (board[i][j] == Node.EMPTY) {
          fullBoard = false;
        }
      }
    }

    // Next check all rows across.
    for (int i = 0; i < boardSize; i++) {
      if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != Node.EMPTY) {
        if (board[i][0] == Node.X) {
          return Node.X;
        } else {
          return Node.O;

        }
      }
    }

    // Next check all columns down.
    for (int j = 0; j < boardSize; j++)
      if (board[0][j] == (board[1][j]) && board[1][j] == (board[2][j]) && board[0][j] != Node.EMPTY)
        if (board[0][j] == Node.X)
          return Node.X;
        else
          return Node.O;

    // Now check diagonal, down-right.
    if (board[0][0] == (board[1][1]) && board[1][1] == (board[2][2]) && board[0][0] != Node.EMPTY)
      if (board[1][1] == Node.X)
        return Node.X;
      else
        return Node.O;

    // Now check diagonal, down-left.
    if (board[0][2] == (board[1][1]) && board[1][1] == (board[2][0]) && board[0][2] != Node.EMPTY)
      if (board[1][1] == Node.X)
        return Node.X;
      else
        return Node.O;

    // If fullBoard is still false, no clear winner.
    if (!fullBoard)
      return null;

    // If we reach this line, no one has won
    return Node.EMPTY;

  }// end checkBoard method
   // check columns for win

  // check rows for win

  // check diaganols for win

}

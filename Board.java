public class Board {

  Node[][] board;
  static int boardSize = 3;

  // constructor method when new object board is created
  public Board() {
    board = new Node[boardSize][boardSize];
    newBoard();
  }

  // function to create new board
  public void newBoard() {
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        board[row][col] = Node.EMPTY;
      }
    }
  }

  // prints out the board with x and y coordinates
  public void printBoard() {
    System.out.println("    1   2   3");
    System.out.println("  -------------");
    for (int row = 0; row < boardSize; row++) {
      System.out.print((row + 1) + " | ");
      for (int col = 0; col < boardSize; col++) {
        System.out.print(board[row][col].getNode());
        System.out.print(" | ");
      }
      System.out.println();
      System.out.println("  -------------");

    }
  }

  // check if coordinate pair (xpos, ypos) has a letter or not
  public boolean isCellFull(int xPos, int yPos) {
    if (board[xPos][yPos] == Node.EMPTY) {
      return false;
    }
    return true;
  }

  // check if the game is over via draw
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

  // return the node at coordinates (row, col)
  public Node getNode(int row, int col) {
    return this.board[row][col];
  }

  // put a node letter at coordinates (row, col)
  public void placeNode(int row, int col, Node letter) {
    board[row][col] = letter;
  }

  public Node checkBoard() {// This checks who has won the game
    boolean fullBoard = true;

    // Check if the board is full and nobody won
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
      if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != Node.EMPTY)
        if (board[0][j] == Node.X)
          return Node.X;
        else
          return Node.O;

    // Now check diagonal, down-right.
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != Node.EMPTY)
      if (board[1][1] == Node.X)
        return Node.X;
      else
        return Node.O;

    // Now check diagonal, down-left.
    if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != Node.EMPTY)
      if (board[1][1] == Node.X)
        return Node.X;
      else
        return Node.O;

    // If fullBoard is still false, no clear winner.
    if (!fullBoard)
      return null;

    // If we reach this line, no one has won and it is a tie
    return Node.EMPTY;

  }// end checkBoard method
}

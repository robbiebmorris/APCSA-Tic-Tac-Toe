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
  //
  public void checkWinner() {
    // check columns for win

    // check rows for win

    // check diaganols for win
  }

}

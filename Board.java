public class Board {

  private Node[][] board;
  int boardSize = 3;

  public Board() {
    board = new Node[boardSize][boardSize];
    newBoard();
  }

  public void newBoard() {
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        board[row][col] = Node.EMPTY;
      }
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

  // for niall to do
  //
  public void checkWinner() {
    // check columns for win

    // check rows for win

    // check diaganols for win
  }

}

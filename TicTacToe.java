public class TicTacToe {

  char[][] board;
  int boardSize = 3;
  private char playerLetter;

  public TicTacToe() {
    // default player letter
    playerLetter = 'X';
    newBoard();
  }

  public void newBoard() {
    board = new char[boardSize][boardSize];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        board[i][j] = ' ';
      }
    }
  }

  // temporary
  public void printBoard() {
    System.out.println("    1   2   3  ");
    System.out.print("  -------------\n");
    for (int i = 0; i < 3; i++) {
      System.out.print((i + 1) + " | ");
      for (int j = 0; j < 3; j++) {
        System.out.print(board[i][j] + " | ");
      }
      System.out.print("\n  -------------\n");
    }
  }

  public void placeLetter(int xPos, int yPos) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[xPos][yPos] == ' ') {
          board[xPos][yPos] = playerLetter;
        }
      }
    }
  }

  public boolean isCellFull(int xPos, int yPos) {
    if (board[xPos][yPos] == ' ') {
      return false;
    }
    return true;
  }

  public boolean isGameOver() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] == ' ') {
          return false;
        }
      }
    }
    return true;
  }

  public boolean checkWinner() {
    return true;
  }

  public void twoPlayerMode() {
    if (playerLetter == 'X') {
      playerLetter = 'O';
    } else {
      playerLetter = 'X';
    }
  }
}
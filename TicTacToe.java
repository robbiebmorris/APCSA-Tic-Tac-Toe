public class TicTacToe {

  // instance variables
  private int[][] board;

  public void board() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        board[i][j] = 0;
      }
    }
  }

  public boolean isGameOver() {
    return true;
  }

  public boolean checkWinner() {
    return true;
  }

  public void computerDifficulty(String difficulty) {

  }

  public static void main(String[] args) {

  }
}
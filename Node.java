public enum Node {
  X('X'), O('O'), EMPTY('-');

  private final char node;

  Node(char newNode) {
    this.node = newNode;
  }

  public char getNode() {
    return this.node;
  }

  public boolean isCellFull() {
    return this != EMPTY;
  }
}

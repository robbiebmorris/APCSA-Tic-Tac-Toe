public enum Node {
  X('x'), O('o'), EMPTY('-');

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

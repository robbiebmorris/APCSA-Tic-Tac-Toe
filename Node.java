//enumerate class
public enum Node {
  // declare enumerate values
  X('X'), O('O'), EMPTY('-');

  private final char node;

  // constructor method
  Node(char newNode) {
    this.node = newNode;
  }

  // simple getter method
  public char getNode() {
    return this.node;
  }

  // check if it doesn't equal empty
  public boolean isCellFull() {
    return this != EMPTY;
  }
}

package net.sf.anathema.character.reporting.extended.elements;

public class CellPadding {

  private final int rightPadding;
  private final int leftPadding;
  private final int bottomPadding;
  private final int topPadding;

  public CellPadding(int padding) {
    this(padding, padding, padding, padding);
  }

  public CellPadding(int topPadding, int bottomPadding, int leftPadding, int rightPadding) {
    this.topPadding = topPadding;
    this.bottomPadding = bottomPadding;
    this.leftPadding = leftPadding;
    this.rightPadding = rightPadding;
  }

  public int getBottomPadding() {
    return bottomPadding;
  }

  public int getRightPadding() {
    return rightPadding;
  }

  public int getLeftPadding() {
    return leftPadding;
  }

  public int getTopPadding() {
    return topPadding;
  }
}
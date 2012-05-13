package net.sf.anathema.framework.view.util;

import net.infonode.util.Direction;

import javax.swing.*;

public enum TabDirection {

  Down(Direction.DOWN, JTabbedPane.BOTTOM), Up(Direction.UP, JTabbedPane.TOP), Left(Direction.LEFT, JTabbedPane.LEFT), Right(Direction.RIGHT, JTabbedPane.RIGHT);

  private Direction direction;
  private int placement;

  private TabDirection(Direction direction, int placement) {
    this.direction = direction;
    this.placement = placement;
  }

  public Direction getDirection() {
    return direction;
  }

  public int getPlacement() {
    return placement;
  }
}
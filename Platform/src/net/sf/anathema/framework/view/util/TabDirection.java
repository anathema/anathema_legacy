package net.sf.anathema.framework.view.util;

import net.infonode.util.Direction;

public enum TabDirection {

  Down(Direction.DOWN), Up(Direction.UP), Left(Direction.LEFT), Right(Direction.RIGHT);

  private Direction direction;

  private TabDirection(Direction direction) {
    this.direction = direction;
  }

  public Direction getDirection() {
    return direction;
  }
}
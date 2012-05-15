package net.sf.anathema.framework.view.util;

import javax.swing.JTabbedPane;

public enum TabDirection {

  Down(JTabbedPane.BOTTOM), Up(JTabbedPane.TOP), Left(JTabbedPane.LEFT), Right(JTabbedPane.RIGHT);

  private int placement;

  private TabDirection(int placement) {
    this.placement = placement;
  }

  public int getPlacement() {
    return placement;
  }
}
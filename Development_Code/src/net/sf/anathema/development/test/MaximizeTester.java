package net.sf.anathema.development.test;

import java.awt.Frame;

public class MaximizeTester {

  public static void main(String[] args) {
    Frame frame = new Frame();
    frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    frame.pack();
    frame.setVisible(true);
  }
}
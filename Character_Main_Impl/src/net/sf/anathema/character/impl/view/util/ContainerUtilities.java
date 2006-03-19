package net.sf.anathema.character.impl.view.util;

import javax.swing.JComponent;
import javax.swing.JSplitPane;

import net.sf.anathema.lib.gui.container.LazilyDividingSplitPane;

public class ContainerUtilities {

  private ContainerUtilities() {
    // nothing to do
  }

  public static JSplitPane createHorizontalSplitPane(JComponent left, JComponent right) {
    return createSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
  }

  public static JSplitPane createVerticalSplitPane(JComponent upper, JComponent lower) {
    return createSplitPane(JSplitPane.VERTICAL_SPLIT, upper, lower);
  }

  private static LazilyDividingSplitPane createSplitPane(int orientation, JComponent first, JComponent second) {
    LazilyDividingSplitPane pane = new LazilyDividingSplitPane(orientation, false, first, second);
    pane.setOneTouchExpandable(true);
    return pane;
  }
}
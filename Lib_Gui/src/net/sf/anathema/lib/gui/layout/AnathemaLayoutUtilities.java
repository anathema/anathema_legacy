package net.sf.anathema.lib.gui.layout;

import javax.swing.JSplitPane;

public class AnathemaLayoutUtilities {

  public static JSplitPane createSplitPane(double dividerLocation) {
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    splitPane.setOneTouchExpandable(true);
    splitPane.setDividerLocation(dividerLocation);
    splitPane.setDividerSize(7);
    return splitPane;
  }
}
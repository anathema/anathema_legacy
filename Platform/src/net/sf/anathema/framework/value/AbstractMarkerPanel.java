package net.sf.anathema.framework.value;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public abstract class AbstractMarkerPanel extends JPanel {

  public AbstractMarkerPanel() {
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setOpaque(false);
  }

  public abstract void resizeMarkerRectangle(int width);
}
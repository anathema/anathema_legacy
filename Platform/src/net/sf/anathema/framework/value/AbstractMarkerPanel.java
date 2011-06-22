package net.sf.anathema.framework.value;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public abstract class AbstractMarkerPanel extends JPanel {

  private static final long serialVersionUID = 6148978861215561807L;

  public AbstractMarkerPanel() {
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    setOpaque(false);
  }

  public abstract void resizeMarkerRectangle(int width);
}
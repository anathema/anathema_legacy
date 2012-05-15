package net.sf.anathema.lib.gui.widgets;

import javax.swing.JComponent;
import java.awt.Dimension;

public class Gap extends JComponent {

  private final Dimension preferredSize;

  public Gap(int preferredWidth, int preferredHeight) {
    this(new Dimension(preferredWidth, preferredHeight));
  }

  public Gap(Dimension preferredSize) {
    super();
    this.preferredSize = preferredSize;
  }

  @Override
  public Dimension getPreferredSize() {
    if (preferredSize == null) {
      return super.getPreferredSize();
    }
    return preferredSize;
  }
}
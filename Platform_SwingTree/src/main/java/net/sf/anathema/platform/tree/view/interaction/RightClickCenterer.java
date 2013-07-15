package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.PolygonPanel;

import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RightClickCenterer extends MouseAdapter {
  private final PolygonPanel polygonPanel;

  public RightClickCenterer(PolygonPanel polygonPanel) {
    this.polygonPanel = polygonPanel;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    boolean rightMouseButton = SwingUtilities.isRightMouseButton(e);
    if (e.getClickCount() == 1 && rightMouseButton) {
      polygonPanel.centerOn(e.getPoint().x, e.getPoint().y);
    }
  }
}
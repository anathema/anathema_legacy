package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.PolygonPanel;

import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RightClickResetter extends MouseAdapter {
  private final PolygonPanel polygonPanel;

  public RightClickResetter(PolygonPanel polygonPanel) {
    this.polygonPanel = polygonPanel;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    boolean rightMouseButton = SwingUtilities.isRightMouseButton(e);
    if (e.getClickCount() == 2 && rightMouseButton) {
      polygonPanel.resetTransformation();
      polygonPanel.revalidate();
    }
  }
}
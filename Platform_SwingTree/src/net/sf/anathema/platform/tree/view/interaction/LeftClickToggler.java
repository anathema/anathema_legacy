package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.PolygonPanel;
import net.sf.anathema.platform.tree.view.draw.InteractiveGraphicsElement;

import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LeftClickToggler extends MouseAdapter {
  private final PolygonPanel polygonPanel;

  public LeftClickToggler(PolygonPanel panel) {
    this.polygonPanel = panel;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (!SwingUtilities.isLeftMouseButton(e)) {
      return;
    }
    polygonPanel.onElementAtPoint(e.getPoint()).perform(new ToggleAndRepaint());
  }

  private class ToggleAndRepaint implements Closure {
    @Override
    public void execute(InteractiveGraphicsElement polygon) {
      polygon.toggle();
      polygonPanel.repaint();
    }
  }
}
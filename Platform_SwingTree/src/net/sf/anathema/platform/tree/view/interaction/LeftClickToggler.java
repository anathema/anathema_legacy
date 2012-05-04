package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.PolygonPanel;

import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class LeftClickToggler extends MouseAdapter {
    PolygonPanel polygonPanel;
    Random seed = new Random();

    public LeftClickToggler(PolygonPanel panel) {
        polygonPanel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!SwingUtilities.isLeftMouseButton(e)){
            return;
        }
        polygonPanel.togglePolygonAtPoint(e.getPoint());
    }
}
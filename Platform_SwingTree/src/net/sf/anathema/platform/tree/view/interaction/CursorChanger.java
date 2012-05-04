package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.PolygonPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class CursorChanger extends MouseMotionAdapter {
    private final PolygonPanel polygonPanel;

    public CursorChanger(PolygonPanel polygonPanel) {
        this.polygonPanel = polygonPanel;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        polygonPanel.changeCursor(e.getPoint());
    }
}
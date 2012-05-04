package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.PolygonPanel;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class WheelScaler implements MouseWheelListener {
    private final PolygonPanel polygonPanel;

    public WheelScaler(PolygonPanel polygonPanel) {
        this.polygonPanel = polygonPanel;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        polygonPanel.scale(e.getUnitsToScroll());
    }
}
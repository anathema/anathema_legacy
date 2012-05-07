package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.tree.view.interaction.LeftClickPanner;
import net.sf.anathema.platform.tree.view.interaction.LeftClickToggler;
import net.sf.anathema.platform.tree.view.interaction.RightClickResetter;
import net.sf.anathema.platform.tree.view.interaction.WheelScaler;

public class InteractionTreeListening {
  private final PolygonPanel polygonPanel;

  public InteractionTreeListening(PolygonPanel polygonPanel) {
    this.polygonPanel = polygonPanel;
  }

  public void initialize() {
    LeftClickPanner panner = new LeftClickPanner(polygonPanel);
    polygonPanel.addMouseListener(panner);
    polygonPanel.addMouseMotionListener(panner);
    polygonPanel.addMouseListener(new LeftClickToggler(polygonPanel));
    polygonPanel.addMouseListener(new RightClickResetter(polygonPanel));
    polygonPanel.addMouseWheelListener(new WheelScaler(polygonPanel));
  }
}

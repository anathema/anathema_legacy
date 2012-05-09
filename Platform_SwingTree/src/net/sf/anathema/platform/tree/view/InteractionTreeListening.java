package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.svgtree.presenter.view.NodeInteractionListener;
import net.sf.anathema.platform.tree.view.container.Cascade;
import net.sf.anathema.platform.tree.view.interaction.LeftClickPanner;
import net.sf.anathema.platform.tree.view.interaction.LeftClickToggler;
import net.sf.anathema.platform.tree.view.interaction.RightClickResetter;
import net.sf.anathema.platform.tree.view.interaction.WheelScaler;

public class InteractionTreeListening {
  private final Cascade cascade;
  private final PolygonPanel polygonPanel;
  private final NodeInteractionListener interactionListener;

  public InteractionTreeListening(Cascade cascade, PolygonPanel polygonPanel, NodeInteractionListener interactionListener) {
    this.cascade = cascade;
    this.polygonPanel = polygonPanel;
    this.interactionListener = interactionListener;
  }

  public void initialize() {
    LeftClickPanner panner = new LeftClickPanner(polygonPanel);
    polygonPanel.addMouseListener(panner);
    polygonPanel.addMouseMotionListener(panner);
    polygonPanel.addMouseListener(new LeftClickToggler(cascade, polygonPanel, interactionListener));
    polygonPanel.addMouseListener(new RightClickResetter(polygonPanel));
    polygonPanel.addMouseWheelListener(new WheelScaler(polygonPanel));
  }
}

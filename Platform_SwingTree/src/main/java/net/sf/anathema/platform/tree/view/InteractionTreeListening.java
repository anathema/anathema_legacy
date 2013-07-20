package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.tree.display.NodeInteractionListener;
import net.sf.anathema.platform.tree.view.container.Cascade;
import net.sf.anathema.platform.tree.view.interaction.CtrlLeftClickDetailsRequester;
import net.sf.anathema.platform.tree.view.interaction.CursorChanger;
import net.sf.anathema.platform.tree.view.interaction.LeftClickPanner;
import net.sf.anathema.platform.tree.view.interaction.LeftClickSelector;
import net.sf.anathema.platform.tree.view.interaction.RightClickCenterer;
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
    polygonPanel.addMousePressListener(panner);
    polygonPanel.addMouseMotionListener(panner);
    polygonPanel.addMouseClickListener(new LeftClickSelector(cascade, polygonPanel, interactionListener));
    polygonPanel.addMouseClickListener(new CtrlLeftClickDetailsRequester(cascade, polygonPanel, interactionListener));
    polygonPanel.addMouseClickListener(new RightClickResetter(polygonPanel));
    polygonPanel.addMouseClickListener(new RightClickCenterer(polygonPanel));
    polygonPanel.addMouseMotionListener(new CursorChanger(polygonPanel));
    polygonPanel.addMouseWheelListener(new WheelScaler(polygonPanel));
  }
}

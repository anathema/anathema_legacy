package net.sf.anathema.platform.tree.view.container;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.tree.display.NodeProperties;
import net.sf.anathema.platform.tree.view.PolygonPanel;
import net.sf.anathema.platform.tree.view.interaction.ShapeWithPosition;

public class ProxyCascade implements Cascade {
  private Cascade delegate = new NullCascade();

  public void setDelegate(Cascade cascade) {
    this.delegate = cascade;
  }

  @Override
  public void colorNode(String nodeId, RGBColor fillColor) {
    delegate.colorNode(nodeId, fillColor);
  }

  @Override
  public void setNodeAlpha(String nodeId, int alpha) {
    delegate.setNodeAlpha(nodeId, alpha);
  }

  @Override
  public void addTo(PolygonPanel panel) {
    delegate.addTo(panel);
  }

  @Override
  public void addToggleListener(NodeToggleListener listener) {
    delegate.addToggleListener(listener);
  }

  @Override
  public void removeToggleListener(NodeToggleListener listener) {
    delegate.removeToggleListener(listener);
  }

  @Override
  public void initNodeNames(NodeProperties properties) {
    delegate.initNodeNames(properties);
  }

  @Override
  public void determinePositionFor(String nodeId, ShapeWithPosition control) {
    delegate.determinePositionFor(nodeId, control);
  }

  public void clear() {
    this.delegate = new NullCascade();
  }
}
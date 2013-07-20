package net.sf.anathema.platform.tree.view.container;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.tree.display.NodeProperties;
import net.sf.anathema.platform.tree.display.draw.ShapeWithPosition;
import net.sf.anathema.platform.tree.view.interaction.PolygonPanel;

public class NullCascade implements Cascade {
  @Override
  public void colorNode(String nodeId, RGBColor fillColor) {
    //nothing to do
  }

  @Override
  public void setNodeAlpha(String nodeId, int alpha) {
    //nothing to do
  }

  @Override
  public void addTo(PolygonPanel panel) {
    //nothing to do
  }

  @Override
  public void addToggleListener(NodeToggleListener listener) {
    //nothing to do
  }

  @Override
  public void removeToggleListener(NodeToggleListener listener) {
    //nothing to do
  }

  @Override
  public void initNodeNames(NodeProperties properties) {
    //nothing to do
  }

  @Override
  public void determinePositionFor(String nodeId, ShapeWithPosition control) {
    //Nothing to do
  }
}
package net.sf.anathema.platform.tree.view.container;

import net.sf.anathema.platform.svgtree.presenter.view.NodeInteractionListener;
import net.sf.anathema.platform.tree.view.PolygonPanel;

import java.awt.Color;

public class NullCascade implements Cascade {
  @Override
  public void colorNode(String nodeId, Color fillColor) {
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
  public void addInteractionListener(NodeInteractionListener listener) {
    //nothing to do
  }

  @Override
  public void removeInteractionListener(NodeInteractionListener listener) {
    //nothing to do
  }
}

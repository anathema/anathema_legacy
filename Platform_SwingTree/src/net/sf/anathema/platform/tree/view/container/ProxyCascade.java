package net.sf.anathema.platform.tree.view.container;

import net.sf.anathema.platform.svgtree.presenter.view.NodeInteractionListener;
import net.sf.anathema.platform.svgtree.presenter.view.NodeProperties;
import net.sf.anathema.platform.tree.view.PolygonPanel;
import net.sf.anathema.platform.tree.view.draw.InteractiveGraphicsElement;

import java.awt.Color;

public class ProxyCascade implements Cascade {
  private Cascade delegate = new NullCascade();

  public void setDelegate(Cascade cascade) {
    this.delegate = cascade;
  }

  @Override
  public void colorNode(String nodeId, Color fillColor) {
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
  public void addInteractionListener(NodeInteractionListener listener) {
    delegate.addInteractionListener(listener);
  }

  @Override
  public void removeInteractionListener(NodeInteractionListener listener) {
    delegate.removeInteractionListener(listener);
  }

  @Override
  public void initNodeNames(NodeProperties properties) {
    delegate.initNodeNames(properties);
  }

  @Override
  public String getIdForElement(InteractiveGraphicsElement element) {
    return delegate.getIdForElement(element);
  }

  @Override
  public boolean hasElement(InteractiveGraphicsElement element) {
    return delegate.hasElement(element);
  }

  public void clear() {
    this.delegate = new NullCascade();
  }
}
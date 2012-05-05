package net.sf.anathema.platform.tree.view.container;

import net.sf.anathema.platform.svgtree.presenter.view.NodeInteractionListener;
import net.sf.anathema.platform.tree.view.PolygonPanel;
import net.sf.anathema.platform.tree.view.container.ContainerCascade;
import net.sf.anathema.platform.tree.view.draw.FilledPolygon;

import java.awt.Color;

public class SingleNodeContainer implements ContainerCascade {
  public SingleNodeContainer(FilledPolygon element) {
  }

  @Override
  public void colorNode(String nodeId, Color fillColor) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void setNodeAlpha(String nodeId, int alpha) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void addTo(PolygonPanel panel) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void addInteractionListener(NodeInteractionListener listener) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void removeInteractionListener(NodeInteractionListener listener) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public boolean hasNode(String nodeId) {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }
}

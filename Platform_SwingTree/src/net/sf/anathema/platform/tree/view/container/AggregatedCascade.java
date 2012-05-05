package net.sf.anathema.platform.tree.view.container;

import com.google.common.collect.Lists;
import net.sf.anathema.platform.svgtree.presenter.view.NodeInteractionListener;
import net.sf.anathema.platform.tree.view.PolygonPanel;

import java.awt.Color;
import java.util.List;

public class AggregatedCascade implements Cascade {
  private final List<ContainerCascade> cascades = Lists.newArrayList();

  @Override
  public void colorNode(String nodeId, Color fillColor) {
    for (ContainerCascade cascade : cascades) {
      if (cascade.hasNode(nodeId)) {
        cascade.colorNode(nodeId, fillColor);
      }
    }
  }

  @Override
  public void setNodeAlpha(String nodeId, int alpha) {
    for (ContainerCascade cascade : cascades) {
      if (cascade.hasNode(nodeId)) {
        cascade.setNodeAlpha(nodeId, alpha);
      }
    }
  }

  @Override
  public void addTo(PolygonPanel panel) {
    for (Cascade cascade : cascades) {
      cascade.addTo(panel);
    }
  }

  @Override
  public void addInteractionListener(NodeInteractionListener listener) {
    for (Cascade cascade : cascades) {
      cascade.addInteractionListener(listener);
    }
  }

  @Override
  public void removeInteractionListener(NodeInteractionListener listener) {
    for (Cascade cascade : cascades) {
      cascade.removeInteractionListener(listener);
    }
  }

  public void add(ContainerCascade cascade) {
    cascades.add(cascade);
  }
}

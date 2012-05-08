package net.sf.anathema.platform.tree.view.container;

import com.google.common.collect.Lists;
import net.sf.anathema.platform.svgtree.presenter.view.NodeInteractionListener;
import net.sf.anathema.platform.svgtree.presenter.view.NodeProperties;
import net.sf.anathema.platform.tree.view.PolygonPanel;
import net.sf.anathema.platform.tree.view.draw.InteractiveGraphicsElement;

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

  @Override
  public void initNodeNames(NodeProperties properties) {
    for (Cascade cascade : cascades) {
      cascade.initNodeNames(properties);
    }
  }

  @Override
  public boolean hasElement(InteractiveGraphicsElement element) {
    for (Cascade cascade : cascades) {
      if (cascade.hasElement(element)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String getIdForElement(InteractiveGraphicsElement element) {
    for (Cascade cascade : cascades) {
      if (cascade.hasElement(element)) {
        return cascade.getIdForElement(element);
      }
    }
    throw new IllegalArgumentException(
            "Received a request to identify, but the element is not part of the currently shown cascades.");
  }

  public void add(ContainerCascade cascade) {
    cascades.add(cascade);
  }
}

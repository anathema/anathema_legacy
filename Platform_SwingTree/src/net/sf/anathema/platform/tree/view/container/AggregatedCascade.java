package net.sf.anathema.platform.tree.view.container;

import com.google.common.collect.Lists;
import net.sf.anathema.platform.tree.presenter.view.NodeProperties;
import net.sf.anathema.platform.tree.view.PolygonPanel;
import net.sf.anathema.platform.tree.view.interaction.SpecialControl;

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
  public void addToggleListener(NodeToggleListener listener) {
    for (Cascade cascade : cascades) {
      cascade.addToggleListener(listener);
    }
  }

  @Override
  public void removeToggleListener(NodeToggleListener listener) {
    for (Cascade cascade : cascades) {
      cascade.removeToggleListener(listener);
    }
  }

  @Override
  public void initNodeNames(NodeProperties properties) {
    for (Cascade cascade : cascades) {
      cascade.initNodeNames(properties);
    }
  }

  @Override
  public void determinePositionFor(String nodeId, SpecialControl control) {
    for (ContainerCascade cascade : cascades) {
      cascade.determinePositionFor(nodeId, control);
    }
  }

  public void add(ContainerCascade cascade) {
    cascades.add(cascade);
  }
}

package net.sf.anathema.platform.tree.view.container;

import com.google.common.collect.Lists;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.tree.display.NodePresentationProperties;
import net.sf.anathema.platform.tree.display.draw.ShapeWithPosition;
import net.sf.anathema.platform.tree.view.draw.FlexibleArrow;
import net.sf.anathema.platform.tree.view.draw.InteractiveGraphicsElement;
import net.sf.anathema.platform.tree.view.interaction.PolygonPanel;
import org.jmock.example.announcer.Announcer;

import java.util.List;

public class DefaultContainerCascade implements ContainerCascade {
  private final Announcer<NodeToggleListener> listeners = Announcer.to(NodeToggleListener.class);
  private final List<IdentifiedPolygon> nodes = Lists.newArrayList();
  private final List<FlexibleArrow> edges = Lists.newArrayList();

  public void add(final IdentifiedPolygon node) {
    nodes.add(node);
    node.element.whenToggledDo(new Runnable() {
      @Override
      public void run() {
        listeners.announce().toggled(node.id);
      }
    });
  }

  public void add(FlexibleArrow element) {
    edges.add(element);
  }

  @Override
  public void colorNode(String nodeId, RGBColor fillColor) {
    for (IdentifiedPolygon node : nodes) {
      if (node.id.equals(nodeId)) {
        node.element.fill(fillColor);
        node.element.setAlpha(fillColor.alpha);
      }
    }
  }

  @Override
  public void addTo(PolygonPanel panel) {
    for (IdentifiedPolygon node : nodes) {
      node.element.setText(node.id);
      panel.add((InteractiveGraphicsElement) node.element);
    }
    for (FlexibleArrow edge : edges) {
      panel.add(edge);
    }
  }

  @Override
  public void addToggleListener(NodeToggleListener listener) {
    listeners.addListener(listener);
  }

  @Override
  public void removeToggleListener(NodeToggleListener listener) {
    listeners.removeListener(listener);
  }

  @Override
  public void initNodeNames(NodePresentationProperties properties) {
    for (IdentifiedPolygon node : nodes) {
      String nodeName = properties.getNodeText(node.id);
      node.element.setText(nodeName);
    }
  }

  @Override
  public void determinePositionFor(String nodeId, ShapeWithPosition control) {
    for (IdentifiedPolygon node : nodes) {
      if (node.id.equals(nodeId)) {
        node.element.position(control);
      }
    }
  }

  @Override
  public boolean hasNode(String nodeId) {
    for (IdentifiedPolygon node : nodes) {
      if (node.id.equals(nodeId)) {
        return true;
      }
    }
    return false;
  }

  public void moveBy(double x, double y) {
    for (IdentifiedPolygon node : nodes) {
      node.element.moveBy((int) x, (int) y);
    }
    for (FlexibleArrow edge : edges) {
      edge.moveBy((int) x, (int) y);
    }
  }
}
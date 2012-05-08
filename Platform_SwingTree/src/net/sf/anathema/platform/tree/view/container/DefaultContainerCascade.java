package net.sf.anathema.platform.tree.view.container;

import com.google.common.collect.Lists;
import net.sf.anathema.platform.svgtree.presenter.view.NodeInteractionListener;
import net.sf.anathema.platform.svgtree.presenter.view.NodeProperties;
import net.sf.anathema.platform.tree.view.PolygonPanel;
import net.sf.anathema.platform.tree.view.draw.FlexibleArrow;
import net.sf.anathema.platform.tree.view.draw.InteractiveGraphicsElement;
import org.jmock.example.announcer.Announcer;

import java.awt.Color;
import java.util.List;

public class DefaultContainerCascade implements ContainerCascade {
  private final Announcer<NodeInteractionListener> listeners = Announcer.to(NodeInteractionListener.class);
  private final List<IdentifiedPolygon> nodes = Lists.newArrayList();
  private final List<FlexibleArrow> edges = Lists.newArrayList();

  public void add(final IdentifiedPolygon node) {
    nodes.add(node);
    node.element.whenToggledDo(new Runnable() {
      @Override
      public void run() {
        listeners.announce().nodeSelected(node.id);
      }
    });
  }

  public void add(FlexibleArrow element) {
    edges.add(element);
  }

  @Override
  public void colorNode(String nodeId, Color fillColor) {
    for (IdentifiedPolygon node : nodes) {
      if (node.id.equals(nodeId)) {
        node.element.fill(fillColor);
      }
    }
  }

  @Override
  public void setNodeAlpha(String nodeId, int alpha) {
    for (IdentifiedPolygon node : nodes) {
      if (node.id.equals(nodeId)) {
        node.element.setAlpha(alpha);
      }
    }
  }

  @Override
  public void addTo(PolygonPanel panel) {
    for (IdentifiedPolygon node : nodes) {
      node.element.setText(node.id);
      panel.add(node.element);
    }
    for (FlexibleArrow edge : edges) {
      panel.add(edge);
    }
  }

  @Override
  public void addInteractionListener(NodeInteractionListener listener) {
    listeners.addListener(listener);
  }

  @Override
  public void removeInteractionListener(NodeInteractionListener listener) {
    listeners.removeListener(listener);
  }

  @Override
  public void initNodeNames(NodeProperties properties) {
    for (IdentifiedPolygon node : nodes) {
      String nodeName = properties.getNodeText(node.id);
      node.element.setText(nodeName);
    }
  }

  @Override
  public String getIdForElement(InteractiveGraphicsElement element) {
    for (IdentifiedPolygon node : nodes) {
      if (node.element.equals(element)) {
        return node.id;
      }
    }
    throw new IllegalArgumentException(
            "Received a request to identify, but the element is not part of the currently shown cascades.");
  }

  @Override
  public boolean hasElement(InteractiveGraphicsElement element) {
    for (IdentifiedPolygon node : nodes) {
      if (node.element.equals(element)) {
        return true;
      }
    }
    return false;
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
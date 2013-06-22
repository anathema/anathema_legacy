package net.sf.anathema.platform.tree.document.visualizer;

import net.sf.anathema.platform.tree.document.components.ILayer;
import net.sf.anathema.platform.tree.document.components.IVisualizableNode;
import net.sf.anathema.platform.tree.document.components.Layer;
import net.sf.anathema.platform.tree.util.Area;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class NodeProjectionTest {

  @Test
  public void doesNotBreakWhenTheFirstNodeShouldBeMoved() throws Exception {
    IVisualizableNode nodeToBeShifted = mock(IVisualizableNode.class);
    ILayer layer1 = createLayer(mock(IVisualizableNode.class));
    ILayer layer2 = createLayer(nodeToBeShifted);
    NodeProjection projection = new NodeProjection(layer1, layer2);
    projection.forceAllNodesOnTheSamePositionToTheLeft(nodeToBeShifted, -25);
  }

  private ILayer createLayer(IVisualizableNode node) {
    Layer layer = new Layer(new Area(0, 0), 0);
    layer.addNode(node);
    return layer;
  }
}
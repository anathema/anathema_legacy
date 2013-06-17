package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.platform.tree.document.components.ILayer;
import net.sf.anathema.platform.tree.document.components.IVisualizableNode;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.framework.ui.Area;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SwingGraphFactoryTest {

  private final ITreePresentationProperties properties = mock(ITreePresentationProperties.class);
  private final ILayer layer = mock(ILayer.class);
  private final SwingGraphFactory factory = new SwingGraphFactory(properties);

  @Before
  public void setUp() throws Exception {
    when(properties.getNodeDimension()).thenReturn(new Area());
    when(properties.getGapDimension()).thenReturn(new Area());
  }

  @Test
  public void createsSingleNode() throws Exception {
    addNodesToLayer(layer, 1);
    assertThat(factory.create(layer).isSingleNode(), is(true));
  }

  @Test
  public void createsNoSingleNodeForMultipleLayers() throws Exception {
    addNodesToLayer(layer, 1);
    ILayer nextLayer = mock(ILayer.class);
    addNodesToLayer(nextLayer, 1);
    assertThat(factory.create(layer, nextLayer).isSingleNode(), is(false));
  }

  @Test
  public void createsNoSingleNodeForMultipleNodes() throws Exception {
    addNodesToLayer(layer, 2);
    assertThat(factory.create(layer).isSingleNode(), is(false));
  }

  private void addNodesToLayer(ILayer layer, int amount) {
    IVisualizableNode[] nodes = new IVisualizableNode[amount];
    ArrayList<IVisualizableNode> list = new ArrayList<>();
    for (int i = 0; i < amount; i++) {
      list.add(mock(IVisualizableNode.class));
    }
    when(layer.getNodes()).thenReturn(list.toArray(nodes));
  }
}
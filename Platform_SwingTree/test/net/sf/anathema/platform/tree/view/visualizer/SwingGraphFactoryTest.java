package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.platform.svgtree.document.components.ILayer;
import net.sf.anathema.platform.svgtree.document.components.IVisualizableNode;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import org.junit.Before;
import org.junit.Test;

import java.awt.Dimension;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SwingGraphFactoryTest {

  ITreePresentationProperties properties = mock(ITreePresentationProperties.class);
  ILayer layer = mock(ILayer.class);
  private SwingGraphFactory factory = new SwingGraphFactory(properties);

  @Before
  public void setUp() throws Exception {
    when(properties.getNodeDimension()).thenReturn(new Dimension());
    when(properties.getGapDimension()).thenReturn(new Dimension());
  }

  @Test
  public void createsSingleNode() throws Exception {
    when(layer.getNodes()).thenReturn(new IVisualizableNode[1]);
    assertThat(factory.create(layer).isSingleNode(), is(true));
  }

  @Test
  public void createsNoSingleNodeForMultipleLayers() throws Exception {
    ILayer nextLayer = mock(ILayer.class);
    assertThat(factory.create(layer, nextLayer).isSingleNode(), is(false));
  }

  @Test
  public void createsNoSingleNodeForMultipleNodes() throws Exception {
    when(layer.getNodes()).thenReturn(new IVisualizableNode[2]);
    assertThat(factory.create(layer).isSingleNode(), is(false));
  }
}
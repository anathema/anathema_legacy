package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.platform.svgtree.document.components.ILayer;
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
    assertThat(factory.createForSingleNode(layer).isSingleNode(), is(true));
  }

  @Test
  public void createsMultiNode() throws Exception {
    assertThat(factory.create(layer).isSingleNode(), is(false));
  }
}
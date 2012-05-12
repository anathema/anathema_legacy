package net.sf.anathema.platform.svgtree.document.components;

import org.junit.Test;

import java.awt.Dimension;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LayerTest {

  private Layer layer = new Layer(new Dimension(0, 0), 0);
  private IVisualizableNode leftNode = mock(IVisualizableNode.class);
  private IVisualizableNode rightNode = mock(IVisualizableNode.class);

  @Test
  public void hasWidth0WithoutNodes() throws Exception {
    assertThat(layer.getWidth(), is(0));
  }

  @Test
  public void returnsRightSideOfRightMostNodeAsWidth() throws Exception {
    when(leftNode.getRightSide()).thenReturn(100);
    when(rightNode.getRightSide()).thenReturn(200);
    layer.addNode(rightNode);
    layer.addNode(leftNode);
    assertThat(layer.getWidth(), is(200));
  }
}

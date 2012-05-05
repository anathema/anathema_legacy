package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.platform.tree.view.container.AggregatedCascade;
import net.sf.anathema.platform.tree.view.container.ContainerCascade;
import net.sf.anathema.platform.tree.view.container.DefaultContainerCascade;
import org.junit.Test;

import java.awt.Dimension;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SingleNodeSwingGraphTest {

  private final Dimension dimension = new Dimension(0, 0);
  private final DefaultContainerCascade nodeContainer = mock(DefaultContainerCascade.class);
  private final SingleNodeSwingGraph swingGraph = new SingleNodeSwingGraph(dimension, nodeContainer);

  @Test
  public void isSingleNode() throws Exception {
    assertThat(swingGraph.isSingleNode(), is(true));
  }

  @Test
  public void translatesContainer() throws Exception {
    swingGraph.translateBy(5, 7);
    verify(nodeContainer).moveBy(5, 7);
  }

  @Test
  public void hasGivenDimension() throws Exception {
    assertThat(swingGraph.getDimension(), is(dimension));
  }

  @Test
  public void addsSelfToCascade() throws Exception {
    AggregatedCascade cascade = mock(AggregatedCascade.class);
    swingGraph.addTo(cascade);
    verify(cascade).add(isA(ContainerCascade.class));
  }
}

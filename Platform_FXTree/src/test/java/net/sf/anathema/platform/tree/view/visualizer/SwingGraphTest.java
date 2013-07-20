package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.framework.ui.Area;
import net.sf.anathema.platform.tree.view.SwingCascadeBuilder;
import net.sf.anathema.platform.tree.view.container.ContainerCascade;
import net.sf.anathema.platform.tree.view.container.DefaultContainerCascade;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SwingGraphTest {

  private final Area dimension = new Area();
  private final DefaultContainerCascade nodeContainer = mock(DefaultContainerCascade.class);
  private final SwingGraph swingGraph = new SwingGraph(nodeContainer, dimension, false);

  @Test
  public void isNoSingleNode() throws Exception {
    assertThat(swingGraph.isSingleNode(), is(false));
  }

  @Test
  public void isSingleNodeIfSoConstructed() throws Exception {
    SwingGraph swingGraph = new SwingGraph(nodeContainer, dimension, true);
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
    SwingCascadeBuilder cascade = mock(SwingCascadeBuilder.class);
    swingGraph.addTo(cascade);
    verify(cascade).add(isA(ContainerCascade.class));
  }
}

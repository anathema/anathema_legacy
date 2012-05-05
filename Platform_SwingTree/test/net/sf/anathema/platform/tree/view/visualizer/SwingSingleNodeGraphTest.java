package net.sf.anathema.platform.tree.view.visualizer;

import net.sf.anathema.platform.tree.view.container.AggregatedCascade;
import net.sf.anathema.platform.tree.view.container.ContainerCascade;
import net.sf.anathema.platform.tree.view.draw.FilledPolygon;
import net.sf.anathema.platform.tree.view.draw.PolygonMother;
import org.junit.Test;

import java.awt.Dimension;
import java.awt.Point;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SwingSingleNodeGraphTest {

  private final Dimension dimension = new Dimension(0, 0);
  private final FilledPolygon polygon = PolygonMother.squareAtOriginWithLength2();
  private final SwingSingleNodeGraph graph = new SwingSingleNodeGraph(polygon, dimension);

  @Test
  public void isSingleNode() throws Exception {
    assertThat(graph.isSingleNode(), is(true));
  }

  @Test
  public void translatesPolygon() throws Exception {
    graph.translateBy(5, 7);
    assertThat(polygon.contains(new Point(6, 8)), is(true));
  }


  @Test
  public void hasGivenDimension() throws Exception {
    assertThat(graph.getDimension(), is(dimension));
  }

  @Test
  public void addsSelfToCascade() throws Exception {
    AggregatedCascade cascade = mock(AggregatedCascade.class);
    graph.addTo(cascade);
    verify(cascade).add(isA(ContainerCascade.class));
  }
}

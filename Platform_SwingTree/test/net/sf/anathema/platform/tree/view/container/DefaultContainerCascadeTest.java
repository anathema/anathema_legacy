package net.sf.anathema.platform.tree.view.container;

import net.sf.anathema.platform.svgtree.presenter.view.NodeInteractionListener;
import net.sf.anathema.platform.tree.view.PolygonPanel;
import net.sf.anathema.platform.tree.view.draw.FilledPolygon;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import java.awt.Color;
import java.awt.Point;

import static net.sf.anathema.platform.tree.view.draw.PolygonMother.squareAtOriginWithLength2;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class DefaultContainerCascadeTest {

  private final FilledPolygon polygon1 = mock(FilledPolygon.class);
  private IdentifiedPolygon node1 = new IdentifiedPolygon(polygon1, "X");
  private final FilledPolygon polygon2 = mock(FilledPolygon.class);
  private IdentifiedPolygon node2 = new IdentifiedPolygon(polygon2, "Y");
  private final DefaultContainerCascade container = new DefaultContainerCascade();

  @Before
  public void setUp() throws Exception {
    container.add(node1);
    container.add(node2);
  }

  @Test
  public void movesPolygon() throws Exception {
    IdentifiedPolygon node = new IdentifiedPolygon(squareAtOriginWithLength2(), "Z");
    container.add(node);
    container.moveBy(5.5, 7.5);
    assertThat(node.element.contains(new Point(6, 8)), is(true));
  }

  @Test
  public void hasNodeWithId() throws Exception {
    assertThat(container.hasNode("X"), is(true));
  }

  @Test
  public void doesNotHaveNodeWithDifferentId() throws Exception {
    assertThat(container.hasNode("Z"), is(false));
  }

  @Test
  public void fillsNodeWithId() throws Exception {
    container.colorNode("X", Color.BLUE);
    verify(polygon1).fill(Color.BLUE);
  }

  @Test
  public void doesNotFillNodeWithDifferentId() throws Exception {
    container.colorNode("Y", Color.BLUE);
    verify(polygon1, never()).fill(Matchers.any(Color.class));
  }

  @Test
  public void alphasNodeWithId() throws Exception {
    container.setNodeAlpha("X", 111);
    verify(polygon1).setAlpha(111);
  }

  @Test
  public void doesNotAlphaNodeWithDifferentId() throws Exception {
    container.setNodeAlpha("Y", 10);
    verify(polygon1, never()).setAlpha(anyInt());
  }

  @Test
  public void addsNodeToPanel() throws Exception {
    PolygonPanel panel = mock(PolygonPanel.class);
    container.addTo(panel);
    verify(panel).add(polygon1);
  }

  @Test
  public void notifiesListenersOfToggledPolygon() throws Exception {
    FilledPolygon element = squareAtOriginWithLength2();
    IdentifiedPolygon node = new IdentifiedPolygon(element, "Z");
    container.add(node);
    NodeInteractionListener listener = mock(NodeInteractionListener.class);
    container.addInteractionListener(listener);
    element.toggle();
    verify(listener).nodeSelected("Z");
  }

  @Test
  public void removesListeners() throws Exception {
    FilledPolygon element = squareAtOriginWithLength2();
    IdentifiedPolygon node = new IdentifiedPolygon(element, "Z");
    container.add(node);
    NodeInteractionListener listener = mock(NodeInteractionListener.class);
    container.addInteractionListener(listener);
    container.removeInteractionListener(listener);
    element.toggle();
    verifyZeroInteractions(listener);
  }
}
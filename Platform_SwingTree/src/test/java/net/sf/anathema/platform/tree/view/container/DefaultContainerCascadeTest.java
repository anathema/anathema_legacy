package net.sf.anathema.platform.tree.view.container;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.tree.display.NodeProperties;
import net.sf.anathema.platform.tree.swing.SwingPolygonPanel;
import net.sf.anathema.platform.tree.view.draw.FilledPolygon;
import net.sf.anathema.platform.tree.view.draw.FlexibleArrow;
import net.sf.anathema.platform.tree.view.interaction.PolygonPanel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Matchers;

import static net.sf.anathema.platform.tree.view.draw.PolygonMother.squareAtOriginWithLength2;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

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
    assertThat(node.element.contains(new Coordinate(6, 8)), is(true));
  }

  @Test
  public void movesOtherElements() throws Exception {
    FlexibleArrow element = mock(FlexibleArrow.class);
    container.add(element);
    container.moveBy(5.5, 7.5);
    verify(element).moveBy(5, 7);
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
    container.colorNode("X", RGBColor.Blue);
    verify(polygon1).fill(RGBColor.Blue);
  }

  @Test
  public void doesNotFillNodeWithDifferentId() throws Exception {
    container.colorNode("Y", RGBColor.Blue);
    verify(polygon1, never()).fill(Matchers.any(RGBColor.class));
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
    PolygonPanel panel = mock(SwingPolygonPanel.class);
    container.addTo(panel);
    verify(panel).add(polygon1);
  }

  @Test
  public void addsOtherElementsToPanel() throws Exception {
    PolygonPanel panel = mock(SwingPolygonPanel.class);
    FlexibleArrow element = mock(FlexibleArrow.class);
    container.add(element);
    container.addTo(panel);
    verify(panel).add(element);
  }

  @Test
  public void setsNodeIdAsTextOnPolygon() throws Exception {
    PolygonPanel panel = mock(SwingPolygonPanel.class);
    container.addTo(panel);
    verify(polygon1).setText("X");
  }

  @Test
  public void setsTextBeforeAdding() throws Exception {
    PolygonPanel panel = mock(SwingPolygonPanel.class);
    container.addTo(panel);
    InOrder inOrder = inOrder(panel, polygon1);
    inOrder.verify(polygon1).setText(anyString());
    inOrder.verify(panel).add(polygon1);
  }

  @Test
  public void notifiesListenersOfToggledPolygon() throws Exception {
    FilledPolygon element = squareAtOriginWithLength2();
    IdentifiedPolygon node = new IdentifiedPolygon(element, "Z");
    container.add(node);
    NodeToggleListener listener = mock(NodeToggleListener.class);
    container.addToggleListener(listener);
    element.toggle();
    verify(listener).toggled("Z");
  }

  @Test
  public void removesListeners() throws Exception {
    FilledPolygon element = squareAtOriginWithLength2();
    IdentifiedPolygon node = new IdentifiedPolygon(element, "Z");
    container.add(node);
    NodeToggleListener listener = mock(NodeToggleListener.class);
    container.addToggleListener(listener);
    container.removeToggleListener(listener);
    element.toggle();
    verifyZeroInteractions(listener);
  }

  @Test
  public void setsInternationalizedNamesOnElements() throws Exception {
    NodeProperties properties = mock(NodeProperties.class);
    when(properties.getNodeText("X")).thenReturn("XNAME");
    container.initNodeNames(properties);
    verify(polygon1).setText("XNAME");
  }
}
package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.display.NodeInteractionListener;
import net.sf.anathema.platform.tree.view.PolygonPanel;
import net.sf.anathema.platform.tree.view.SwingPolygonPanel;
import net.sf.anathema.platform.tree.view.container.DefaultContainerCascade;
import net.sf.anathema.platform.tree.view.container.IdentifiedPolygon;
import net.sf.anathema.platform.tree.view.draw.FilledPolygon;
import net.sf.anathema.platform.tree.view.draw.PolygonMother;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.MouseEvent;

import static net.sf.anathema.platform.tree.view.interaction.LeftClickTogglerTest.AnyCoordinate;
import static net.sf.anathema.platform.tree.view.interaction.LeftClickTogglerTest.AnyPoint;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class CtrlLeftClickDetailsRequesterTest {

  private DefaultContainerCascade cascade = new DefaultContainerCascade();
  private FilledPolygon polygon = PolygonMother.squareAtOriginWithLength2();
  private IdentifiedPolygon identifiedPolygon = new IdentifiedPolygon(polygon, "X");
  private PolygonPanel panel = mock(SwingPolygonPanel.class);
  private MouseEvent event = mock(MouseEvent.class);
  private NodeInteractionListener listener = mock(NodeInteractionListener.class);
  private CtrlLeftClickDetailsRequester requester = new CtrlLeftClickDetailsRequester(cascade, panel, listener);

  @Before
  public void setUp() throws Exception {
    when(event.getPoint()).thenReturn(AnyPoint);
    when(panel.onElementAtPoint(AnyCoordinate)).thenReturn(new ElementExecutor(polygon));
    cascade.add(identifiedPolygon);
  }

  @Test
  public void informsListenerOfToggledElement() throws Exception {
    when(event.getModifiers()).thenReturn(MouseEvent.BUTTON1_MASK);
    when(event.isControlDown()).thenReturn(true);
    requester.mouseClicked(event);
    verify(listener).nodeDetailsDemanded("X");
  }


  @Test
  public void doesNotBindToElementPermanently() throws Exception {
    when(event.getModifiers()).thenReturn(MouseEvent.BUTTON1_MASK);
    when(event.isControlDown()).thenReturn(true);
    requester.mouseClicked(event);
    polygon.toggle();
    verify(listener, times(1)).nodeDetailsDemanded("X");
  }

  @Test
  public void doesNothingOnRightClick() throws Exception {
    when(event.getModifiers()).thenReturn(MouseEvent.BUTTON3_MASK);
    requester.mouseClicked(event);
    verifyZeroInteractions(panel);
  }

  @Test
  public void doesNothingOnLeftClickWithoutControlPressed() throws Exception {
    when(event.getModifiers()).thenReturn(MouseEvent.BUTTON1_MASK);
    requester.mouseClicked(event);
    verifyZeroInteractions(panel);
  }
}

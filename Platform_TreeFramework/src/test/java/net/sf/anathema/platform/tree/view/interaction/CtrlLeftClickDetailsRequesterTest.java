package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.display.NodeInteractionListener;
import net.sf.anathema.platform.tree.view.container.DefaultContainerCascade;
import net.sf.anathema.platform.tree.view.container.IdentifiedPolygon;
import net.sf.anathema.platform.tree.view.draw.FilledPolygon;
import net.sf.anathema.platform.tree.view.draw.PolygonMother;
import org.junit.Before;
import org.junit.Test;

import static net.sf.anathema.platform.tree.view.interaction.LeftClickTogglerTest.AnyCoordinate;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class CtrlLeftClickDetailsRequesterTest {

  private DefaultContainerCascade cascade = new DefaultContainerCascade();
  private FilledPolygon polygon = PolygonMother.squareAtOriginWithLength2();
  private IdentifiedPolygon identifiedPolygon = new IdentifiedPolygon(polygon, "X");
  private PolygonPanel panel = mock(PolygonPanel.class);
  private NodeInteractionListener listener = mock(NodeInteractionListener.class);
  private CtrlLeftClickDetailsRequester requester = new CtrlLeftClickDetailsRequester(cascade, panel, listener);

  @Before
  public void setUp() throws Exception {
    when(panel.onElementAtPoint(AnyCoordinate)).thenReturn(new ElementExecutor(polygon));
    cascade.add(identifiedPolygon);
  }

  @Test
  public void informsListenerOfToggledElement() throws Exception {
    requester.mouseClicked(MouseButton.Left, MetaKey.CTRL, AnyCoordinate, 1);
    verify(listener).nodeDetailsDemanded("X");
  }


  @Test
  public void doesNotBindToElementPermanently() throws Exception {
    requester.mouseClicked(MouseButton.Left, MetaKey.CTRL, AnyCoordinate, 1);
    polygon.toggle();
    verify(listener, times(1)).nodeDetailsDemanded("X");
  }

  @Test
  public void doesNothingOnRightClick() throws Exception {
    requester.mouseClicked(MouseButton.Right, MetaKey.CTRL, AnyCoordinate, 1);
    verifyZeroInteractions(panel);
  }

  @Test
  public void doesNothingOnLeftClickWithoutControlPressed() throws Exception {
    requester.mouseClicked(MouseButton.Left, MetaKey.NONE, AnyCoordinate, 1);
    verifyZeroInteractions(panel);
  }
}

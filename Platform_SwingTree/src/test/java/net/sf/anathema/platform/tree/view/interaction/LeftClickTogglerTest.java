package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.platform.tree.display.NodeInteractionListener;
import net.sf.anathema.platform.tree.view.SwingPolygonPanel;
import net.sf.anathema.platform.tree.view.container.Cascade;
import net.sf.anathema.platform.tree.view.container.DefaultContainerCascade;
import net.sf.anathema.platform.tree.view.draw.InteractiveGraphicsElement;
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;
import java.awt.event.MouseEvent;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class LeftClickTogglerTest {

  public static final Point AnyPoint = new Point(1, 2);
  public static final Coordinate AnyCoordinate = new Coordinate(1, 2);

  private InteractiveGraphicsElement element = mock(InteractiveGraphicsElement.class);
  private SwingPolygonPanel panel = mock(SwingPolygonPanel.class);
  private MouseEvent event = mock(MouseEvent.class);
  private NodeInteractionListener listener = mock(NodeInteractionListener.class);
  private Cascade cascade = new DefaultContainerCascade();
  private LeftClickSelector selector = new LeftClickSelector(cascade, panel, listener);

  @Before
  public void setUp() throws Exception {
    when(event.getPoint()).thenReturn(AnyPoint);
    when(panel.onElementAtPoint(AnyCoordinate)).thenReturn(new ElementExecutor(element));
  }

  @Test
  public void informsListenerOfToggledElement() throws Exception {
    when(event.getModifiers()).thenReturn(MouseEvent.BUTTON1_MASK);
    selector.mouseClicked(event);
    verify(element).toggle();
    verify(panel).refresh();
  }

  @Test
  public void doesNothingOnRightClick() throws Exception {
    when(event.getModifiers()).thenReturn(MouseEvent.BUTTON3_MASK);
    selector.mouseClicked(event);
    verifyZeroInteractions(panel);
  }

  @Test
  public void doesNothingOnLeftClickWithControlPressed() throws Exception {
    when(event.getModifiers()).thenReturn(MouseEvent.BUTTON1_MASK);
    when(event.isControlDown()).thenReturn(true);
    selector.mouseClicked(event);
    verifyZeroInteractions(panel);
  }
}
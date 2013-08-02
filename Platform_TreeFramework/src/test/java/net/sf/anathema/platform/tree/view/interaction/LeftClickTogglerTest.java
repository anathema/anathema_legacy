package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.platform.tree.display.NodeInteractionListener;
import net.sf.anathema.platform.tree.view.container.Cascade;
import net.sf.anathema.platform.tree.view.container.DefaultContainerCascade;
import net.sf.anathema.platform.tree.view.draw.InteractiveGraphicsElement;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class LeftClickTogglerTest {

  public static final Coordinate AnyCoordinate = new Coordinate(1, 2);

  private InteractiveGraphicsElement element = mock(InteractiveGraphicsElement.class);
  private PolygonPanel panel = mock(PolygonPanel.class);
  private NodeInteractionListener listener = mock(NodeInteractionListener.class);
  private Cascade cascade = new DefaultContainerCascade();
  private LeftClickSelector selector = new LeftClickSelector(cascade, panel, listener);

  @Before
  public void setUp() throws Exception {
    when(panel.onElementAtPoint(AnyCoordinate)).thenReturn(new ElementExecutor(element));
  }

  @Test
  public void informsListenerOfToggledElement() throws Exception {
    selector.mouseClicked(MouseButton.Primary, MetaKey.NONE, AnyCoordinate, 1);
    verify(element).toggle();
    verify(panel).refresh();
  }

  @Test
  public void doesNothingOnRightClick() throws Exception {
    selector.mouseClicked(MouseButton.Secondary, MetaKey.NONE, AnyCoordinate, 1);
    verifyZeroInteractions(panel);
  }

  @Test
  public void doesNothingOnLeftClickWithControlPressed() throws Exception {
    selector.mouseClicked(MouseButton.Primary, MetaKey.CTRL, AnyCoordinate, 1);
    verifyZeroInteractions(panel);
  }
}
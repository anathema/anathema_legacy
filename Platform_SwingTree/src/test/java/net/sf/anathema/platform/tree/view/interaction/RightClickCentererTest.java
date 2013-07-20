package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.SwingPolygonPanel;
import org.junit.Test;

import static net.sf.anathema.platform.tree.view.interaction.LeftClickTogglerTest.AnyCoordinate;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class RightClickCentererTest {

  PolygonPanel panel = mock(SwingPolygonPanel.class);
  RightClickCenterer centerer = new RightClickCenterer(panel);

  @Test
  public void centersPanelOnCursorPositionOnRightClick() throws Exception {
    centerer.mouseClicked(MouseButton.Right, MetaKey.NONE, AnyCoordinate, 1);
    verify(panel).centerOn(AnyCoordinate);
  }

  @Test
  public void doesNotReactOnLeftClick() throws Exception {
    centerer.mouseClicked(MouseButton.Left, MetaKey.NONE, AnyCoordinate, 1);
    verifyZeroInteractions(panel);
  }

  @Test
  public void doesNotReactOnDoubleClick() throws Exception {
    centerer.mouseClicked(MouseButton.Right, MetaKey.NONE, AnyCoordinate, 2);
    verifyZeroInteractions(panel);
  }
}
package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.PolygonPanel;
import net.sf.anathema.platform.tree.view.SwingPolygonPanel;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static net.sf.anathema.platform.tree.view.interaction.LeftClickTogglerTest.AnyCoordinate;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

public class RightClickResetterTest {

  private PolygonPanel panel = mock(SwingPolygonPanel.class);
  private RightClickResetter resetter = new RightClickResetter(panel);

  @Test
  public void resetsTransformationAndZoomsOutALittleOnRightDoubleClick() throws Exception {
    resetter.mouseClicked(MouseButton.Right, MetaKey.NONE, AnyCoordinate,2);
    InOrder inOrder = Mockito.inOrder(panel);
    inOrder.verify(panel).resetTransformation();
    inOrder.verify(panel).scale(0.75);
  }

  @Test
  public void doesNothingOnSingleClick() throws Exception {
    resetter.mouseClicked(MouseButton.Right, MetaKey.NONE, AnyCoordinate, 1);
    verifyZeroInteractions(panel);
  }

  @Test
  public void doesNothingOnLeftClick() throws Exception {
    resetter.mouseClicked(MouseButton.Left, MetaKey.NONE, AnyCoordinate, 2);
    verifyZeroInteractions(panel);
  }
}

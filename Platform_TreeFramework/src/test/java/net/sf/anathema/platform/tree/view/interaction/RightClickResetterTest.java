package net.sf.anathema.platform.tree.view.interaction;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static net.sf.anathema.platform.tree.view.interaction.LeftClickTogglerTest.AnyCoordinate;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

public class RightClickResetterTest {

  private PolygonPanel panel = mock(PolygonPanel.class);
  private RightClickResetter resetter = new RightClickResetter(panel);

  @Test
  public void resetsTransformationAndZoomsOutALittleOnRightClickWithCtrl() throws Exception {
    resetter.mouseClicked(MouseButton.Secondary, MetaKey.CTRL, AnyCoordinate,1);
    InOrder inOrder = Mockito.inOrder(panel);
    inOrder.verify(panel).resetTransformation();
    inOrder.verify(panel).scale(0.75);
  }

  @Test
  public void doesNothingOnClickWithoutCtrl() throws Exception {
    resetter.mouseClicked(MouseButton.Secondary, MetaKey.NONE, AnyCoordinate, 1);
    verifyZeroInteractions(panel);
  }

  @Test
  public void doesNothingOnLeftClickWithCtrl() throws Exception {
    resetter.mouseClicked(MouseButton.Primary, MetaKey.CTRL, AnyCoordinate, 2);
    verifyZeroInteractions(panel);
  }
}

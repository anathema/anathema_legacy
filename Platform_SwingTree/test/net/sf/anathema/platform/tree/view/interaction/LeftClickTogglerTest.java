package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.PolygonPanel;
import org.junit.Before;
import org.junit.Test;

import javax.swing.event.MenuEvent;

import java.awt.Point;
import java.awt.event.MouseEvent;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class LeftClickTogglerTest {

  public static final Point AnyPoint = new Point(1, 2);

  private PolygonPanel panel = mock(PolygonPanel.class);
  private MouseEvent event = mock(MouseEvent.class);
  private LeftClickToggler toggler = new LeftClickToggler(panel);


  @Before
  public void setUp() throws Exception {
    when(event.getPoint()).thenReturn(AnyPoint);
  }

  @Test
  public void togglesPointOfLeftClickOnPanel() throws Exception {
    when(event.getModifiers()).thenReturn(MouseEvent.BUTTON1_MASK);
    toggler.mouseClicked(event);
    verify(panel).togglePolygonAtPoint(AnyPoint);
  }

  @Test
  public void doesNothingOnRightClick() throws Exception {
    when(event.getModifiers()).thenReturn(MouseEvent.BUTTON3_MASK);
    toggler.mouseClicked(event);
    verifyZeroInteractions(panel);
  }
}
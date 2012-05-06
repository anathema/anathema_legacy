package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.tree.view.interaction.LeftClickPanner;
import net.sf.anathema.platform.tree.view.interaction.LeftClickToggler;
import net.sf.anathema.platform.tree.view.interaction.RightClickResetter;
import net.sf.anathema.platform.tree.view.interaction.WheelScaler;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SwingTreeListeningTest {

  PolygonPanel panel = mock(PolygonPanel.class);
  SwingTreeListening listening = new SwingTreeListening(panel);

  @Test
  public void addsPanListenerForDragAndClick() throws Exception {
    listening.initialize();
    ArgumentCaptor<LeftClickPanner> captor = ArgumentCaptor.forClass(LeftClickPanner.class);
    verify(panel).addMouseListener(captor.capture());
    verify(panel).addMouseMotionListener(captor.getValue());
  }

  @Test
  public void addsToggleListenerForLeftClick() throws Exception {
    listening.initialize();
    verify(panel).addMouseListener(isA(LeftClickToggler.class));
  }

  @Test
  public void addsResetListenerForRightClick() throws Exception {
    listening.initialize();
    verify(panel).addMouseListener(isA(RightClickResetter.class));
  }

  @Test
  public void addsScalerForWheel() throws Exception {
    listening.initialize();
    verify(panel).addMouseWheelListener(isA(WheelScaler.class));
  }
}

package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.tree.display.NodeInteractionListener;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AggregatingInteractionListenerTest {
  NodeInteractionListener innerListener = mock(NodeInteractionListener.class);
  AggregatingInteractionListener listener = new AggregatingInteractionListener();

  @Test
  public void notifiesCollectedListenersOfSelection() throws Exception {
    listener.addNodeInteractionListener(innerListener);
    listener.nodeSelected("X");
    verify(innerListener).nodeSelected("X");
  }

  @Test
  public void notifiesCollectedListenersOfDetailRequest() throws Exception {
    listener.addNodeInteractionListener(innerListener);
    listener.nodeDetailsDemanded("X");
    verify(innerListener).nodeDetailsDemanded("X");
  }
}

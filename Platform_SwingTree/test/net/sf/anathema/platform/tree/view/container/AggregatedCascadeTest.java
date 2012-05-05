package net.sf.anathema.platform.tree.view.container;

import net.sf.anathema.platform.svgtree.presenter.view.NodeInteractionListener;
import net.sf.anathema.platform.tree.view.PolygonPanel;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AggregatedCascadeTest {

  private AggregatedCascade cascade = new AggregatedCascade();
  private ContainerCascade child = mock(ContainerCascade.class);

  @Before
  public void setUp() throws Exception {
    cascade.add(child);
  }

  @Test
  public void addsAllChildrenToPanel() throws Exception {
    PolygonPanel panel = mock(PolygonPanel.class);
    cascade.addTo(panel);
    verify(child).addTo(panel);
  }

  @Test
  public void addsListenersToAllChildren() throws Exception {
    NodeInteractionListener listener = mock(NodeInteractionListener.class);
    cascade.addInteractionListener(listener);
    verify(child).addInteractionListener(listener);
  }

  @Test
  public void removesListenersFromAllChildren() throws Exception {
    NodeInteractionListener listener = mock(NodeInteractionListener.class);
    cascade.removeInteractionListener(listener);
    verify(child).removeInteractionListener(listener);
  }

  @Test
  public void forwardsColoringToContainer() throws Exception {
    when(child.hasNode("X")).thenReturn(true);
    cascade.colorNode("X", Color.PINK);
    verify(child).colorNode("X", Color.PINK);
  }

  @Test
  public void doesNotForwardColoringToDifferentChild() throws Exception {
    cascade.colorNode("X", Color.PINK);
    verify(child, never()).colorNode("X", Color.PINK);
  }

  @Test
  public void forwardsAlphaChange() throws Exception {
    when(child.hasNode("X")).thenReturn(true);
    cascade.setNodeAlpha("X", 17);
    verify(child).setNodeAlpha("X", 17);
  }

  @Test
  public void doesNotForwardAlphaChangeToDifferentChild() throws Exception {
    cascade.setNodeAlpha("X", 17);
    verify(child, never()).setNodeAlpha("X", 17);
  }
}
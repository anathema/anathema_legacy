package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.svgtree.presenter.view.CascadeLoadedListener;
import net.sf.anathema.platform.svgtree.presenter.view.NodeInteractionListener;
import net.sf.anathema.platform.svgtree.presenter.view.NodeProperties;
import net.sf.anathema.platform.tree.view.container.Cascade;
import net.sf.anathema.platform.tree.view.interaction.LeftClickPanner;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Matchers;

import javax.swing.JComponent;
import java.awt.Color;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class SwingTreeViewTest {

  private final PolygonPanel panel = mock(PolygonPanel.class);
  private final SwingTreeView swingTreeView = new SwingTreeView(panel);
  Cascade cascade = mock(Cascade.class);

  @Test
  public void hasPolygonPanelAsComponent() throws Exception {
    JComponent component = swingTreeView.getComponent();
    assertThat(component, is(instanceOf(PolygonPanel.class)));
  }

  @Test
  public void setsBackgroundColorOnCorrespondingNode() throws Exception {
    swingTreeView.loadCascade(cascade, true);
    swingTreeView.setNodeBackgroundColor("ID", Color.WHITE);
    verify(cascade).colorNode("ID", Color.WHITE);
  }

  @Test
  public void triggersRepaintAfterColoring() throws Exception {
    swingTreeView.loadCascade(cascade, true);
    swingTreeView.setNodeBackgroundColor("ID", Color.WHITE);
    InOrder inOrder = inOrder(cascade, panel);
    inOrder.verify(cascade).colorNode(anyString(), Matchers.any(Color.class));
    inOrder.verify(panel).repaint();
  }

  @Test
  public void setsBackgroundFillOnPolygonPanel() throws Exception {
    swingTreeView.setCanvasBackground(Color.RED);
    verify(panel).setBackground(Color.RED);
  }

  @Test
  public void setAlphaOnCorrespondingNode() throws Exception {
    swingTreeView.loadCascade(cascade, true);
    swingTreeView.setNodeAlpha("ID", 120);
    verify(cascade).setNodeAlpha("ID", 120);
  }

  @Test
  public void triggersRepaintAfterChangingTransparency() throws Exception {
    swingTreeView.loadCascade(cascade, true);
    swingTreeView.setNodeAlpha("ID", 100);
    InOrder inOrder = inOrder(cascade, panel);
    inOrder.verify(cascade).setNodeAlpha(anyString(), anyInt());
    inOrder.verify(panel).repaint();
  }

  @Test
  public void notifiesListenersOfLoading() throws Exception {
    CascadeLoadedListener listener = mock(CascadeLoadedListener.class);
    swingTreeView.addCascadeLoadedListener(listener);
    swingTreeView.loadCascade(cascade, true);
    verify(listener).cascadeLoaded();
  }

  @Test
  public void paintsCascadeOnCanvas() throws Exception {
    swingTreeView.loadCascade(cascade, true);
    verify(cascade).addTo(panel);
  }


  @Test
  public void clearsCanvasBeforeAdding() throws Exception {
    swingTreeView.loadCascade(cascade, true);
    InOrder inOrder = inOrder(panel, cascade);
    inOrder.verify(panel).clear();
    inOrder.verify(cascade).addTo(panel);
  }

  @Test
  public void addsInteractionListenerToCascade() throws Exception {
    swingTreeView.loadCascade(cascade, true);
    verify(cascade).addInteractionListener(isA(NodeInteractionListener.class));
  }

  @Test
  public void removesInteractionListenerFromOldCascadePriorToChanging() throws Exception {
    Cascade oldCascade = mock(Cascade.class);
    swingTreeView.loadCascade(oldCascade, true);
    swingTreeView.loadCascade(cascade, true);
    ArgumentCaptor<NodeInteractionListener> captor = ArgumentCaptor.forClass(NodeInteractionListener.class);
    verify(oldCascade).addInteractionListener(captor.capture());
    verify(oldCascade).removeInteractionListener(captor.getValue());
  }

  @Test
  public void isEmptyAfterClear() throws Exception {
    swingTreeView.clear();
    verify(panel).clear();
  }

  @Test
  public void removesListenersOnClear() throws Exception {
    swingTreeView.loadCascade(cascade, true);
    swingTreeView.clear();
    verify(cascade).removeInteractionListener(isA(NodeInteractionListener.class));
  }

  @Test
  public void forgetsCascadeOnClear() throws Exception {
    swingTreeView.loadCascade(cascade, true);
    swingTreeView.clear();
    reset(cascade);
    swingTreeView.setNodeAlpha("X", 124);
    verifyZeroInteractions(cascade);
  }

  @Test
  public void initializesNodeNamesOnCascadesAndRepaints() throws Exception {
    NodeProperties properties = mock(NodeProperties.class);
    swingTreeView.loadCascade(cascade, true);
    swingTreeView.initNodeNames(properties);
    InOrder inOrder = inOrder(cascade, panel);
    inOrder.verify(cascade).initNodeNames(properties);
    inOrder.verify(panel).repaint();
  }

  @Test
  public void registersListenersOnPanel() throws Exception {
    verify(panel).addMouseListener(isA(LeftClickPanner.class));
  }
}

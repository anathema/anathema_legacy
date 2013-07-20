package net.sf.anathema.platform.tree.view;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.tree.display.CascadeLoadedListener;
import net.sf.anathema.platform.tree.display.NodeProperties;
import net.sf.anathema.platform.tree.display.ToolTipProperties;
import net.sf.anathema.platform.tree.view.container.Cascade;
import net.sf.anathema.platform.tree.view.interaction.LeftClickPanner;
import net.sf.anathema.platform.tree.view.interaction.ToolTipListener;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Matchers;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class SwingTreeViewTest {

  private final SwingPolygonPanel panel = mock(SwingPolygonPanel.class);
  private final SwingTreeView swingTreeView = new SwingTreeView(panel);
  Cascade cascade = mock(Cascade.class);

  @Test
  public void hasPolygonPanelAsComponent() throws Exception {
    swingTreeView.getComponent();
    verify(panel).getComponent();
  }

  @Test
  public void setsBackgroundColorOnCorrespondingNode() throws Exception {
    swingTreeView.loadCascade(cascade, true);
    swingTreeView.setNodeBackgroundColor("ID", RGBColor.White);
    verify(cascade).colorNode("ID", RGBColor.White);
  }

  @Test
  public void triggersRepaintAfterColoring() throws Exception {
    swingTreeView.loadCascade(cascade, true);
    swingTreeView.setNodeBackgroundColor("ID", RGBColor.White);
    InOrder inOrder = inOrder(cascade, panel);
    inOrder.verify(cascade).colorNode(anyString(), Matchers.any(RGBColor.class));
    inOrder.verify(panel).refresh();
  }

  @Test
  public void setsBackgroundFillOnPolygonPanel() throws Exception {
    swingTreeView.setCanvasBackground(RGBColor.Red);
    verify(panel).setBackground(RGBColor.Red);
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
    inOrder.verify(panel).refresh();
  }

  @Test
  public void notifiesListenersOfLoading() throws Exception {
    CascadeLoadedListener listener = mock(CascadeLoadedListener.class);
    swingTreeView.addCascadeLoadedListener(listener);
    swingTreeView.loadCascade(cascade, true);
    verify(listener).cascadeLoaded();
  }

  @Test
  public void zoomsOutALittleAfterNotificationSoAllSpecialControlsAreInitializedWhenItHappens() throws Exception {
    CascadeLoadedListener listener = mock(CascadeLoadedListener.class);
    swingTreeView.addCascadeLoadedListener(listener);
    swingTreeView.loadCascade(cascade, true);
    InOrder inOrder = inOrder(listener, panel);
    inOrder.verify(listener).cascadeLoaded();
    inOrder.verify(panel).scale(0.75);
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
  public void isEmptyAfterClear() throws Exception {
    swingTreeView.clear();
    verify(panel).clear();
  }

  @Test
  public void resetsPanelDuringClear() throws Exception {
    swingTreeView.clear();
    verify(panel).resetTransformation();
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
    inOrder.verify(panel).refresh();
  }

  @Test
  public void registersToolTipListener() {
    ToolTipProperties properties = mock(ToolTipProperties.class);
    swingTreeView.initToolTips(properties);
    verify(panel).addMouseMotionListener(isA(ToolTipListener.class));
  }

  @Test
  public void registersListenersOnPanel() throws Exception {
    verify(panel).addMousePressListener(isA(LeftClickPanner.class));
  }
}
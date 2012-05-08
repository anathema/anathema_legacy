package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.svgtree.presenter.view.CascadeLoadException;
import net.sf.anathema.platform.svgtree.presenter.view.CascadeLoadedListener;
import net.sf.anathema.platform.svgtree.presenter.view.ITreeView;
import net.sf.anathema.platform.svgtree.presenter.view.NodeInteractionListener;
import net.sf.anathema.platform.svgtree.presenter.view.NodeProperties;
import net.sf.anathema.platform.svgtree.presenter.view.ToolTipProperties;
import net.sf.anathema.platform.tree.view.container.Cascade;
import net.sf.anathema.platform.tree.view.container.ProxyCascade;
import org.jmock.example.announcer.Announcer;

import javax.swing.JComponent;
import java.awt.Color;

public class SwingTreeView implements ITreeView<Cascade> {

  private final PolygonPanel polygonPanel;
  private final Announcer<CascadeLoadedListener> loadListeners = Announcer.to(CascadeLoadedListener.class);
  private final AggregatingInteractionListener currentCascadeInteractionListener = new AggregatingInteractionListener();
  private final ProxyCascade cascade = new ProxyCascade();

  public SwingTreeView() {
    this(new PolygonPanel());
  }

  public SwingTreeView(final PolygonPanel polygonPanel) {
    this.polygonPanel = polygonPanel;
    new InteractionTreeListening(polygonPanel).initialize();
  }

  @Override
  public void addNodeInteractionListener(NodeInteractionListener listener) {
    currentCascadeInteractionListener.addNodeInteractionListener(listener);
  }

  @Override
  public void setNodeBackgroundColor(String nodeId, Color color) {
    cascade.colorNode(nodeId, color);
    polygonPanel.repaint();
  }

  @Override
  public void setNodeAlpha(String nodeId, int alpha) {
    cascade.setNodeAlpha(nodeId, alpha);
    polygonPanel.repaint();
  }

  @Override
  public void addCascadeLoadedListener(CascadeLoadedListener listener) {
    loadListeners.addListener(listener);
  }

  @Override
  public void setCanvasBackground(Color color) {
    polygonPanel.setBackground(color);
  }

  @Override
  public void loadCascade(Cascade newCascade, boolean resetView) throws CascadeLoadException {
    clear();
    cascade.setDelegate(newCascade);
    cascade.addTo(polygonPanel);
    cascade.addInteractionListener(currentCascadeInteractionListener);
    loadListeners.announce().cascadeLoaded();
  }

  @Override
  public void initNodeNames(NodeProperties properties) {
    cascade.initNodeNames(properties);
    polygonPanel.repaint();
  }

  public void initToolTips(ToolTipProperties properties){
    polygonPanel.addMouseMotionListener(new ToolTipListener(properties, polygonPanel, cascade));
  }

  @Override
  public void clear() {
    cascade.removeInteractionListener(currentCascadeInteractionListener);
    cascade.clear();
    polygonPanel.clear();
  }

  @Override
  public void dispose() {
    //nothing to do
  }

  @Override
  public JComponent getComponent() {
    return polygonPanel;
  }
}
package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.svgtree.presenter.view.CascadeLoadException;
import net.sf.anathema.platform.svgtree.presenter.view.CascadeLoadedListener;
import net.sf.anathema.platform.svgtree.presenter.view.ContentFactory;
import net.sf.anathema.platform.svgtree.presenter.view.ITreeView;
import net.sf.anathema.platform.svgtree.presenter.view.NodeInteractionListener;
import net.sf.anathema.platform.svgtree.presenter.view.NodeProperties;
import net.sf.anathema.platform.svgtree.presenter.view.SpecialControl;
import net.sf.anathema.platform.svgtree.presenter.view.ToolTipProperties;
import net.sf.anathema.platform.tree.view.container.Cascade;
import net.sf.anathema.platform.tree.view.container.ProxyCascade;
import net.sf.anathema.platform.tree.view.interaction.ButtonSpecialControl;
import net.sf.anathema.platform.tree.view.interaction.SpecialContentMap;
import net.sf.anathema.platform.tree.view.interaction.ToolTipListener;
import org.jmock.example.announcer.Announcer;

import javax.swing.JComponent;
import java.awt.Color;

public class SwingTreeView implements ITreeView<Cascade> {

  private final PolygonPanel polygonPanel;
  private final Announcer<CascadeLoadedListener> loadListeners = Announcer.to(CascadeLoadedListener.class);
  private final AggregatingInteractionListener allInteractionListeners = new AggregatingInteractionListener();
  private final ProxyCascade cascade = new ProxyCascade();
  private final SpecialContentMap specialContent = new SpecialContentMap();

  public SwingTreeView() {
    this(new PolygonPanel());
  }

  public SwingTreeView(PolygonPanel polygonPanel) {
    this.polygonPanel = polygonPanel;
    new InteractionTreeListening(cascade, polygonPanel, allInteractionListeners).initialize();
  }

  @Override
  public void addNodeInteractionListener(NodeInteractionListener listener) {
    allInteractionListeners.addNodeInteractionListener(listener);
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
    polygonPanel.scale(0.75);
    loadListeners.announce().cascadeLoaded();
  }

  @Override
  public void addSpecialControl(String nodeId, SpecialControl specialControl){
    ButtonSpecialControl control = new ButtonSpecialControl("Special", specialContent);
    control.whenTriggeredShow(specialControl);
    cascade.determinePositionFor(nodeId, control);
    polygonPanel.add(control);
  }

  @Override
  public void initNodeNames(NodeProperties properties) {
    cascade.initNodeNames(properties);
    polygonPanel.repaint();
  }

  public void initToolTips(ToolTipProperties properties) {
    polygonPanel.addMouseMotionListener(new ToolTipListener(properties, polygonPanel, cascade));
  }

  @Override
  public void clear() {
    cascade.clear();
    polygonPanel.clear();
    polygonPanel.resetTransformation();
  }

  @Override
  public void registerSpecialType(Class contentClass, ContentFactory factory) {
    specialContent.put(contentClass, factory);
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
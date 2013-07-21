package net.sf.anathema.platform.tree.swing;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.tree.display.CascadeLoadException;
import net.sf.anathema.platform.tree.display.CascadeLoadedListener;
import net.sf.anathema.platform.tree.display.ContentFactory;
import net.sf.anathema.platform.tree.display.NodeInteractionListener;
import net.sf.anathema.platform.tree.display.NodeProperties;
import net.sf.anathema.platform.tree.display.SpecialControl;
import net.sf.anathema.platform.tree.display.ToolTipProperties;
import net.sf.anathema.platform.tree.display.TreeView;
import net.sf.anathema.platform.tree.view.AggregatingInteractionListener;
import net.sf.anathema.platform.tree.view.InteractionTreeListening;
import net.sf.anathema.platform.tree.view.container.Cascade;
import net.sf.anathema.platform.tree.view.container.ProxyCascade;
import net.sf.anathema.platform.tree.view.interaction.DefaultScaler;
import net.sf.anathema.platform.tree.view.interaction.SpecialContentMap;
import net.sf.anathema.platform.tree.view.interaction.SpecialControlTrigger;
import net.sf.anathema.platform.tree.view.interaction.ToolTipListener;
import org.jmock.example.announcer.Announcer;

import javax.swing.JComponent;

public class SwingTreeView implements TreeView {

  private final SwingPolygonPanel polygonPanel;
  private final Announcer<CascadeLoadedListener> loadListeners = Announcer.to(CascadeLoadedListener.class);
  private final AggregatingInteractionListener allInteractionListeners = new AggregatingInteractionListener();
  private final ProxyCascade cascade = new ProxyCascade();
  private final SpecialContentMap specialContent = new SpecialContentMap();

  public SwingTreeView() {
    this(new SwingPolygonPanel());
  }

  public SwingTreeView(SwingPolygonPanel polygonPanel) {
    this.polygonPanel = polygonPanel;
    new InteractionTreeListening(cascade, polygonPanel, allInteractionListeners).initialize();
  }

  @Override
  public void addNodeInteractionListener(NodeInteractionListener listener) {
    allInteractionListeners.addNodeInteractionListener(listener);
  }

  @Override
  public void setNodeBackgroundColor(String nodeId, RGBColor color) {
    cascade.colorNode(nodeId, color);
    polygonPanel.refresh();
  }

  @Override
  public void setNodeAlpha(String nodeId, int alpha) {
    cascade.setNodeAlpha(nodeId, alpha);
    polygonPanel.refresh();
  }

  @Override
  public void addCascadeLoadedListener(CascadeLoadedListener listener) {
    loadListeners.addListener(listener);
  }

  @Override
  public void setCanvasBackground(RGBColor color) {
    polygonPanel.setBackground(color);
  }

  @Override
  public void loadCascade(Cascade newCascade, boolean resetView) throws CascadeLoadException {
    clear();
    cascade.setDelegate(newCascade);
    cascade.addTo(polygonPanel);
    loadListeners.announce().cascadeLoaded();
    new DefaultScaler(polygonPanel).scale();
  }

  @Override
  public void addSpecialControl(String nodeId, SpecialControl specialControl) {
    SpecialControlTrigger trigger = polygonPanel.addSpecialControl();
    trigger.init("Special", specialContent);
    trigger.whenTriggeredShow(specialControl);
    cascade.determinePositionFor(nodeId, trigger);
  }

  @Override
  public void initNodeNames(NodeProperties properties) {
    cascade.initNodeNames(properties);
    polygonPanel.refresh();
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

  public JComponent getComponent() {
    return polygonPanel.getComponent();
  }
}
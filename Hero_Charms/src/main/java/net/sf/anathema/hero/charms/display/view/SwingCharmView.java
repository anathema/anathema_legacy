package net.sf.anathema.hero.charms.display.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.platform.tree.display.AgnosticTreeView;
import net.sf.anathema.platform.tree.display.CascadeLoadedListener;
import net.sf.anathema.platform.tree.display.ContentFactory;
import net.sf.anathema.platform.tree.display.GenericCascadeRenderer;
import net.sf.anathema.platform.tree.display.ISpecialNodeView;
import net.sf.anathema.platform.tree.display.NodeInteractionListener;
import net.sf.anathema.platform.tree.display.NodePresentationProperties;
import net.sf.anathema.platform.tree.display.ToolTipProperties;
import net.sf.anathema.platform.tree.display.TreeRenderer;
import net.sf.anathema.platform.tree.display.TreeView;
import net.sf.anathema.platform.tree.document.GenericCascadeFactory;
import net.sf.anathema.platform.tree.swing.SwingPolygonPanel;
import net.sf.anathema.platform.tree.view.AgnosticCascadeStrategy;
import net.sf.anathema.platform.tree.view.MouseBorderClosure;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;
import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class SwingCharmView implements CharmView, IView {

  private final JPanel selectionPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(4).fillX()));
  private final JPanel content = new JPanel(new MigLayout(fillWithoutInsets().wrapAfter(1)));
  private final SwingPolygonPanel viewComponent = new SwingPolygonPanel();
  private final AgnosticTreeView treeView = new AgnosticTreeView(viewComponent);

  public SwingCharmView() {
    content.add(selectionPanel, new CC().growX());
    content.add(viewComponent.getComponent(), new CC().grow().push());
  }

  public TreeView addTreeView(final ToolTipProperties treeProperties, final NodePresentationProperties properties) {
    CascadeLoadedListener listener = new CascadeLoadedListener() {
      @Override
      public void cascadeLoaded() {
        treeView.initNodeNames(properties);
      }
    };
    treeView.initToolTips(treeProperties);
    treeView.addCascadeLoadedListener(listener);
    treeView.setCanvasBackground(RGBColor.White);
    return treeView;
  }

  @Override
  public JComponent getComponent() {
    return content;
  }

  @Override
  public SwingComboboxSelectionView addSelectionView(String title, AgnosticUIConfiguration<Identifier> uiConfig) {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(new TitledBorder(title));
    SwingComboboxSelectionView selectionView = new SwingComboboxSelectionView(uiConfig);
    panel.add(selectionView.getComponent(), BorderLayout.CENTER);
    selectionPanel.add(panel);
    return selectionView;
  }

  @Override
  public ObjectSelectionView<Identifier> addSelectionViewAndSizeItFor(String title,
                                                                      AgnosticUIConfiguration<Identifier> uiConfig,
                                                                      Identifier[] objects) {
    SwingComboboxSelectionView selectionView = addSelectionView(title, uiConfig);
    selectionView.sizeComboboxFor(objects);
    return selectionView;
  }

  @Override
  public void addCharmCascadeHelp(String helpText) {
    JLabel help = new JLabel(helpText);
    selectionPanel.add(help, new CC().growX().pushX());
  }

  @Override
  public TreeRenderer getCharmTreeRenderer() {
    return new GenericCascadeRenderer(treeView, new GenericCascadeFactory(new AgnosticCascadeStrategy()));
  }

  @Override
  public final void addCascadeLoadedListener(CascadeLoadedListener cascadeListener) {
    treeView.addCascadeLoadedListener(cascadeListener);
  }

  @Override
  public void colorNode(String charmId, RGBColor color) {
    treeView.colorNode(charmId, color);
  }

  @Override
  public void addCharmInteractionListener(NodeInteractionListener listener) {
    treeView.addNodeInteractionListener(listener);
  }

  @Override
  public void setSpecialCharmViewVisible(ISpecialNodeView charmView, boolean visible) {
    if (visible) {
      treeView.addSpecialControl(charmView.getNodeId(), charmView);
    }
  }

  @Override
  public void setBackgroundColor(RGBColor color) {
    treeView.setCanvasBackground(color);
  }

  @Override
  public void registerSpecialType(Class contentClass, ContentFactory factory) {
    treeView.registerSpecialType(contentClass, factory);
  }

  @Override
  public void whenCursorLeavesCharmAreaResetAllPopups() {
    viewComponent.addMouseBorderListener(new MouseBorderClosure() {
      @Override
      public void mouseEntered() {
        //nothing to do
      }

      @Override
      public void mouseExited() {
        viewComponent.resetAllTooltips();
      }
    });
  }
}
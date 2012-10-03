package net.sf.anathema.campaign.view.plot;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.campaign.presenter.view.IPlotViewListener;
import net.sf.anathema.campaign.presenter.view.plot.IPlotView;
import net.sf.anathema.campaign.presenter.view.plot.IPlotViewProperties;
import net.sf.anathema.campaign.presenter.view.plot.ITreeView;
import net.sf.anathema.campaign.view.BasicItemDescriptionView;
import net.sf.anathema.campaign.view.util.DefaultTreeView;
import net.sf.anathema.framework.itemdata.view.IBasicItemDescriptionView;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.util.TreeUtilities;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.dnd.DnDConstants;
import java.awt.event.MouseMotionAdapter;

public class PlotView implements IPlotView {

  private final JPanel content = new JPanel();
  private final PlotViewListenerControl listenerControl = new PlotViewListenerControl();
  private final JSplitPane splitPane = createSplitPane(0.3);
  private JTree tree;
  private JPanel treePanel;
  private BasicItemDescriptionView itemDescriptionView;
  private JButton addButton;
  private JButton removeButton;
  private JButton upButton;
  private JButton downButton;
  private DefaultMutableTreeNode selectedNode;

  @Override
  public final void initGui(IPlotViewProperties properties) {
    createContent(content, properties);
  }

  @Override
  public void addPlotViewListener(IPlotViewListener listener) {
    listenerControl.addPlotViewListener(listener);
  }

  @Override
  public void collapseNode(DefaultMutableTreeNode node) {
    tree.collapsePath(new TreePath(node.getPath()));
  }

  private void createContent(JPanel panel, IPlotViewProperties properties) {
    panel.setLayout(new BorderLayout());
    initTreePanelGui(properties);
    splitPane.setLeftComponent(treePanel);
    JComponent component = itemDescriptionView.getComponent();
    component.setBorder(BorderFactory.createTitledBorder(properties.getBorderTitle()));
    splitPane.setRightComponent(component);
    panel.add(splitPane, BorderLayout.CENTER);
  }

  @Override
  public void expandNode(DefaultMutableTreeNode node) {
    tree.expandPath(new TreePath(node.getPath()));
  }

  @Override
  public void setSelectedHierarchyNode(DefaultMutableTreeNode node) {
    tree.setSelectionPath(new TreePath(node.getPath()));
  }

  @Override
  public IBasicItemDescriptionView initBasicItemDescriptionView() {
    this.itemDescriptionView = new BasicItemDescriptionView();
    return itemDescriptionView;
  }

  private void initHierarchyButtons(IPlotViewProperties properties) {
    SmartAction addAction = new AddAction();
    properties.initHierarchyAddAction(addAction);
    addButton = new JButton(addAction);

    SmartAction removeAction = new RemoveAction();
    properties.initHierarchyRemoveAction(removeAction);
    removeButton = new JButton(removeAction);

    SmartAction upAction = new UpAction();
    properties.initHierarchyUpAction(upAction);
    upButton = new JButton(upAction);

    SmartAction downAction = new DownAction();
    properties.initHierarchyDownAction(downAction);
    downButton = new JButton(downAction);
  }

  @Override
  public void initSeriesHierarchyView(TreeModel model, TreeCellRenderer renderer, String title) {
    tree = new JTree(model);
    tree.setCellRenderer(renderer);
    new PlotDnD(createHierarchyTreeView(), listenerControl).initDragAndDrop();
    tree.addTreeSelectionListener(new TreeSelectionListener() {
      @Override
      public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode currentSelection = TreeUtilities.getSelectedHierachyNode(tree);
        if (selectedNode == currentSelection) {
          return;
        }
        selectedNode = currentSelection;
        listenerControl.fireSelectionChangedTo(currentSelection);
      }
    });
    tree.addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseDragged(java.awt.event.MouseEvent e) {
        tree.getTransferHandler().exportAsDrag(tree, e, DnDConstants.ACTION_MOVE);
      }
    });
    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    treePanel = new JPanel();
    treePanel.setBorder(new TitledBorder(title));
  }

  private void initTreePanelGui(IPlotViewProperties properties) {
    treePanel.setLayout(
            new MigLayout(new LC().wrapAfter(1).insets("0"), new AC().grow(100,0).fill(0), new AC().grow(100, 0).fill(0)));
    treePanel.add(new JScrollPane(tree), new CC().grow().pushY());
    treePanel.add(createButtonPanel(properties), new CC().dockSouth());
  }

  private Component createButtonPanel(IPlotViewProperties properties) {
    initHierarchyButtons(properties);
    JPanel panel = new JPanel(new MigLayout(new LC().wrapAfter(4).insets("2"), new AC().grow().fill()));
    panel.add(removeButton);
    panel.add(addButton);
    panel.add(upButton);
    panel.add(downButton);
    return panel;
  }

  @Override
  public void setAddButtonEnabled(boolean enabled) {
    addButton.setEnabled(enabled);
  }

  @Override
  public void setRemoveButtonEnabled(boolean enabled) {
    removeButton.setEnabled(enabled);
  }

  @Override
  public void setHierarchieTreeCellRenderer(TreeCellRenderer renderer) {
    tree.setCellRenderer(renderer);
  }

  @Override
  public void setUpButtonEnabled(boolean enabled) {
    upButton.setEnabled(enabled);
  }

  @Override
  public void setDownButtonEnabled(boolean enabled) {
    downButton.setEnabled(enabled);
  }

  @Override
  public ITreeView createHierarchyTreeView() {
    return new DefaultTreeView(tree);
  }

  @Override
  public JComponent getComponent() {
    return content;
  }

  private class DownAction extends SmartAction {

    @Override
    protected void execute(Component parentComponent) {
      DefaultMutableTreeNode node = TreeUtilities.getSelectedHierachyNode(tree);
      DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
      int originalIndex = parentNode.getIndex(node);
      listenerControl.fireMoveToRequested(node, originalIndex + 1);
    }
  }

  private class UpAction extends SmartAction {

    @Override
    protected void execute(Component parentComponent) {
      DefaultMutableTreeNode node = TreeUtilities.getSelectedHierachyNode(tree);
      DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
      int originalIndex = parentNode.getIndex(node);
      listenerControl.fireMoveToRequested(node, originalIndex - 1);
    }
  }

  private class RemoveAction extends SmartAction {

    @Override
    protected void execute(Component parentComponent) {
      listenerControl.fireRemoveRequested(TreeUtilities.getSelectedHierachyNode(tree));
    }
  }

  private class AddAction extends SmartAction {

    @Override
    protected void execute(Component parentComponent) {
      listenerControl.fireAddRequested(TreeUtilities.getSelectedHierachyNode(tree));
    }
  }

  public static JSplitPane createSplitPane(double dividerLocation) {
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    splitPane.setOneTouchExpandable(true);
    splitPane.setDividerLocation(dividerLocation);
    splitPane.setDividerSize(7);
    return splitPane;
  }
}
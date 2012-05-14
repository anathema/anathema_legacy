package net.sf.anathema.campaign.view.plot;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.campaign.presenter.view.IPlotViewListener;
import net.sf.anathema.campaign.presenter.view.plot.IPlotView;
import net.sf.anathema.campaign.presenter.view.plot.IPlotViewProperties;
import net.sf.anathema.campaign.presenter.view.plot.ITreeView;
import net.sf.anathema.campaign.view.BasicItemDescriptionView;
import net.sf.anathema.campaign.view.util.DefaultTreeView;
import net.sf.anathema.framework.itemdata.view.IBasicItemDescriptionView;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.layout.AnathemaLayoutUtilities;
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
  private final JSplitPane splitPane = AnathemaLayoutUtilities.createSplitPane(0.3);
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
    SmartAction addAction = new SmartAction() {
      private static final long serialVersionUID = 1112372805471188433L;

      @Override
      protected void execute(Component parentComponent) {
        listenerControl.fireAddRequested(TreeUtilities.getSelectedHierachyNode(tree));
      }
    };
    properties.initHierarchyAddAction(addAction);
    addButton = new JButton(addAction);

    SmartAction removeAction = new SmartAction() {
      private static final long serialVersionUID = 167702035567735387L;

      @Override
      protected void execute(Component parentComponent) {
        listenerControl.fireRemoveRequested(TreeUtilities.getSelectedHierachyNode(tree));
      }
    };
    properties.initHierarchyRemoveAction(removeAction);
    removeButton = new JButton(removeAction);

    SmartAction upAction = new SmartAction() {
      private static final long serialVersionUID = -6261555855649984975L;

      @Override
      protected void execute(Component parentComponent) {
        DefaultMutableTreeNode node = TreeUtilities.getSelectedHierachyNode(tree);
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
        int originalIndex = parentNode.getIndex(node);
        listenerControl.fireMoveToRequested(node, originalIndex - 1);
      }
    };
    properties.initHierarchyUpAction(upAction);
    upButton = new JButton(upAction);

    SmartAction downAction = new SmartAction() {
      private static final long serialVersionUID = 8767113776923146733L;

      @Override
      protected void execute(Component parentComponent) {
        DefaultMutableTreeNode node = TreeUtilities.getSelectedHierachyNode(tree);
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
        int originalIndex = parentNode.getIndex(node);
        listenerControl.fireMoveToRequested(node, originalIndex + 1);
      }
    };
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
    treePanel.setLayout(new GridDialogLayout(1, false));
    treePanel.add(new JScrollPane(tree), GridDialogLayoutData.FILL_BOTH);
    treePanel.add(createButtonPanel(properties));
  }

  private Component createButtonPanel(IPlotViewProperties properties) {
    initHierarchyButtons(properties);
    JPanel panel = new JPanel(new GridDialogLayout(4, false));
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
}
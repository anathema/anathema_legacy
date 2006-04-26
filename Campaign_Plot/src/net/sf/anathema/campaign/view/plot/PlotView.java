package net.sf.anathema.campaign.view.plot;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.dnd.DnDConstants;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
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

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.campaign.presenter.view.IPlotViewListener;
import net.sf.anathema.campaign.presenter.view.plot.IPlotView;
import net.sf.anathema.campaign.presenter.view.plot.IPlotViewProperties;
import net.sf.anathema.campaign.presenter.view.plot.ITreeView;
import net.sf.anathema.campaign.view.BasicItemDescriptionView;
import net.sf.anathema.campaign.view.util.DefaultTreeView;
import net.sf.anathema.framework.itemdata.view.IBasicItemDescriptionView;
import net.sf.anathema.framework.presenter.view.AbstractTabView;
import net.sf.anathema.lib.gui.layout.AnathemaLayoutUtilities;

public class PlotView extends AbstractTabView<IPlotViewProperties> implements IPlotView {

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

  public PlotView(String header) {
    super(header, false);
  }

  public synchronized void addPlotViewListener(IPlotViewListener listener) {
    listenerControl.addPlotViewListener(listener);
  }

  public void collapseNode(DefaultMutableTreeNode node) {
    tree.collapsePath(new TreePath(node.getPath()));
  }

  @Override
  protected void createContent(JPanel panel, IPlotViewProperties properties) {
    panel.setLayout(new BorderLayout());
    initTreePanelGui(properties);
    splitPane.setLeftComponent(treePanel);
    splitPane.setRightComponent(itemDescriptionView.getComponent());
    panel.add(splitPane, BorderLayout.CENTER);
  }

  public void expandNode(DefaultMutableTreeNode node) {
    tree.expandPath(new TreePath(node.getPath()));
  }

  public DefaultMutableTreeNode getSelectedHierachyNode() {
    TreePath selectionPath = tree.getSelectionPath();
    if (selectionPath == null) {
      return null;
    }
    return (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
  }

  public void setSelectedHierarchyNode(DefaultMutableTreeNode node) {
    tree.setSelectionPath(new TreePath(node.getPath()));
  }

  public IBasicItemDescriptionView initBasicItemDescriptionView(String title) {
    this.itemDescriptionView = new BasicItemDescriptionView(title);
    return itemDescriptionView;
  }

  private void initHierarchyButtons(IPlotViewProperties properties) {
    SmartAction addAction = new SmartAction() {
      @Override
      protected void execute(Component parentComponent) {
        listenerControl.fireAddRequested(getSelectedHierachyNode());
      }
    };
    Dimension preferredAddSize = properties.initHierarchyAddAction(addAction);
    addButton = new JButton(addAction);
    addButton.setPreferredSize(preferredAddSize);
    SmartAction removeAction = new SmartAction() {
      @Override
      protected void execute(Component parentComponent) {
        listenerControl.fireRemoveRequested(getSelectedHierachyNode());
      }
    };
    Dimension preferredRemoveSize = properties.initHierarchyRemoveAction(removeAction);
    removeButton = new JButton(removeAction);
    removeButton.setPreferredSize(preferredRemoveSize);
    SmartAction upAction = new SmartAction() {
      @Override
      protected void execute(Component parentComponent) {
        DefaultMutableTreeNode node = getSelectedHierachyNode();
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
        int originalIndex = parentNode.getIndex(node);
        listenerControl.fireMoveToRequested(node, originalIndex - 1);
      }
    };
    Dimension upButtonSize = properties.initHierarchyUpAction(upAction);
    upButton = new JButton(upAction);
    upButton.setPreferredSize(upButtonSize);

    SmartAction downAction = new SmartAction() {
      @Override
      protected void execute(Component parentComponent) {
        DefaultMutableTreeNode node = getSelectedHierachyNode();
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
        int originalIndex = parentNode.getIndex(node);
        listenerControl.fireMoveToRequested(node, originalIndex + 1);
      }
    };
    Dimension downButtonSize = properties.initHierarchyDownAction(downAction);
    downButton = new JButton(downAction);
    downButton.setPreferredSize(downButtonSize);
  }

  public void initSeriesHierarchyView(TreeModel model, TreeCellRenderer renderer, String title) {
    tree = new JTree(model);
    tree.setCellRenderer(renderer);
    new PlotDnD(getHierarchyTreeView(), listenerControl).initDragAndDrop();
    tree.addTreeSelectionListener(new TreeSelectionListener() {
      public void valueChanged(TreeSelectionEvent e) {
        if (selectedNode == getSelectedHierachyNode()) {
          return;
        }
        selectedNode = getSelectedHierachyNode();        
        listenerControl.fireSelectionChangedTo(getSelectedHierachyNode());        
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

  public void setAddButtonEnabled(boolean enabled) {
    addButton.setEnabled(enabled);
  }

  public void setRemoveButtonEnabled(boolean enabled) {
    removeButton.setEnabled(enabled);
  }

  public void updateHierarchieTreeCellRenderer(TreeCellRenderer renderer) {
    tree.setCellRenderer(renderer);
  }

  public void setUpButtonEnabled(boolean enabled) {
    upButton.setEnabled(enabled);
  }

  public void setDownButtonEnabled(boolean enabled) {
    downButton.setEnabled(enabled);
  }

  public ITreeView getHierarchyTreeView() {
    return new DefaultTreeView(tree);
  }
}
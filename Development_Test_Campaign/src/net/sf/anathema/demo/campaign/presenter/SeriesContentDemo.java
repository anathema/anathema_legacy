package net.sf.anathema.demo.campaign.presenter;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.campaign.concrete.SeriesContentModel;
import net.sf.anathema.framework.repository.tree.IRepositoryTreeView;
import net.sf.anathema.framework.repository.tree.RepositoryTreePresenter;
import net.sf.anathema.framework.repository.tree.RepositoryTreeView;
import net.sf.anathema.framework.repository.tree.demo.DemoRepositoryTreeModel;
import net.sf.anathema.framework.repository.tree.demo.DemoResources;
import net.sf.anathema.framework.view.PrintNameFile;
import de.jdemo.extensions.SwingDemoCase;

public class SeriesContentDemo extends SwingDemoCase {

  private DemoRepositoryTreeModel repositoryModel;
  private JPanel panel;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.repositoryModel = new DemoRepositoryTreeModel();
    this.panel = new JPanel();
  }

  public void demoMoveTrees() {
    final IRepositoryTreeView repositoryTreeView = new RepositoryTreeView();
    new RepositoryTreePresenter(
        new DemoResources(),
        repositoryModel,
        repositoryTreeView,
        new DefaultTreeCellRenderer(),
        "#DemoRepository").initPresentation(); //$NON-NLS-1$
    panel.add(repositoryTreeView.getComponent());
    final IRepositoryTreeView contentTreeView = new RepositoryTreeView();
    final SeriesContentModel contentModel = new SeriesContentModel(repositoryModel.getAllItemTypes());
    new RepositoryTreePresenter(
        new DemoResources(),
        contentModel,
        contentTreeView,
        new DefaultTreeCellRenderer(),
        "#DemoContent").initPresentation(); //$NON-NLS-1$
    panel.add(contentTreeView.getComponent());
    JButton moveButton = new JButton(new SmartAction("Add to Content") { //$NON-NLS-1$
          @Override
          protected void execute(Component parentComponent) {
            JTree tree = (JTree) (repositoryTreeView.getComponent());
            int[] selectionRows = tree.getSelectionRows();
            if (selectionRows != null && selectionRows.length != 0) {
              DefaultMutableTreeNode node = (DefaultMutableTreeNode) (tree.getSelectionModel().getSelectionPath().getLastPathComponent());
              PrintNameFile item = (PrintNameFile) (node.getUserObject());
              contentModel.addItem(item);
            }
          }
        });
    panel.add(moveButton);
    show(panel);
  }

}
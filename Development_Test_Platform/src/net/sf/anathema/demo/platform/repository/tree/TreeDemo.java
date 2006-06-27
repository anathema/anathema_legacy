package net.sf.anathema.demo.platform.repository.tree;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultTreeCellRenderer;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.framework.repository.tree.IRepositoryTreeView;
import net.sf.anathema.framework.repository.tree.RepositoryTreePresenter;
import net.sf.anathema.framework.repository.tree.RepositoryTreeView;
import net.sf.anathema.framework.view.PrintNameFile;
import de.jdemo.extensions.SwingDemoCase;

public class TreeDemo extends SwingDemoCase {

  private DemoRepositoryTreeModel repositoryModel;
  private JPanel panel;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.repositoryModel = new DemoRepositoryTreeModel();
    this.panel = new JPanel();
  }

  public void demoTrees() {
    RepositoryTreeView repositoryTreeView = new RepositoryTreeView();
    new RepositoryTreePresenter(
        new DemoResources(),
        repositoryModel,
        repositoryTreeView,
        new DefaultTreeCellRenderer(),
        "#Demo").initPresentation(); //$NON-NLS-1$
    panel.add(createJScrollPane(repositoryTreeView));
    JButton charbutton = new JButton(new SmartAction("Add Character") { //$NON-NLS-1$
          @Override
          protected void execute(Component parentComponent) {
            repositoryModel.addPrintNameFile(new PrintNameFile(null, "New Character", //$NON-NLS-1$
                "new", //$NON-NLS-1$
                DemoRepositoryTreeModel.CHARACTER));
          }
        });
    JButton notebutton = new JButton(new SmartAction("Add Note") { //$NON-NLS-1$
          @Override
          protected void execute(Component parentComponent) {
            repositoryModel.addPrintNameFile(new PrintNameFile(null, "New Note", "new", DemoRepositoryTreeModel.NOTE)); //$NON-NLS-1$ //$NON-NLS-2$
          }
        });
    panel.add(charbutton);
    panel.add(notebutton);
    show(panel);
  }

  private JComponent createJScrollPane(IRepositoryTreeView repositoryTreeView) {
    JScrollPane scrollPane = new JScrollPane(repositoryTreeView.getComponent());
    scrollPane.setPreferredSize(new Dimension(300, 300));
    return scrollPane;
  }
}
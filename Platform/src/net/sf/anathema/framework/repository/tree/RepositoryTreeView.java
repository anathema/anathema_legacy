package net.sf.anathema.framework.repository.tree;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;

public class RepositoryTreeView implements IRepositoryTreeView {

  private final JPanel panel = new JPanel(new GridDialogLayout(1, false));
  private final JPanel buttonPanel = new JPanel();

  public JTree addTree() {
    final JTree tree = new JTree();
    panel.add(new JScrollPane(tree), GridDialogLayoutData.FILL_BOTH);
    return tree;
  }

  public void addActionButton(Action action) {
    buttonPanel.add(new JButton(action));
  }

  public JComponent getComponent() {
    panel.add(buttonPanel);
    return panel;
  }
}
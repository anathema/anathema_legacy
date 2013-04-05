package net.sf.anathema.framework.repository.tree;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

public class RepositoryTreeView implements IRepositoryTreeView {
  private final JPanel panel = new JPanel(new MigLayout(new LC().wrapAfter(1).fill()));
  private final JPanel buttonPanel = new JPanel(new MigLayout(new LC().fill()));

  @Override
  public JTree addTree() {
    JTree tree = new JTree();
    panel.add(new JScrollPane(tree, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER), new CC().grow().push());
    return tree;
  }

  @Override
  public void addActionButton(Action action) {
    buttonPanel.add(new JButton(action), new CC().growX());
  }

  @Override
  public JComponent getComponent() {
    panel.add(buttonPanel);
    return panel;
  }
}
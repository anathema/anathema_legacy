package net.sf.anathema.framework.repository.tree;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.util.TreeUtilities;

public class RepositoryTreeView implements IRepositoryTreeView {

  private final JPanel panel = new JPanel(new GridDialogLayout(1, false));
  private final GenericControl<IRepositoryTreeListener> control = new GenericControl<IRepositoryTreeListener>();

  public void initTree(TreeModel treeModel, TreeCellRenderer renderer) {
    final JTree tree = new JTree(treeModel);
    tree.setCellRenderer(renderer);
    tree.addTreeSelectionListener(new TreeSelectionListener() {
      public void valueChanged(TreeSelectionEvent e) {
        control.forAllDo(new IClosure<IRepositoryTreeListener>() {
          public void execute(IRepositoryTreeListener input) {
            input.nodeSelected(TreeUtilities.getSelectedHierachyNode(tree));
          }
        });
      }
    });
    panel.add(new JScrollPane(tree), GridDialogLayoutData.FILL_BOTH);
  }

  public void addActionButton(Action action) {
    panel.add(new JButton(action));
  }

  public JComponent getComponent() {
    return panel;
  }

  public void addRepositoryTreeListener(IRepositoryTreeListener listener) {
    control.addListener(listener);
  }
}
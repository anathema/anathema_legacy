package net.sf.anathema.development;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JScrollPane;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.repository.tree.RepositoryTreeCellRenderer;
import net.sf.anathema.framework.repository.tree.RepositoryTreeModel;
import net.sf.anathema.framework.repository.tree.RepositoryTreePresenter;
import net.sf.anathema.framework.repository.tree.RepositoryTreeView;
import net.sf.anathema.lib.gui.GuiUtilities;
import net.sf.anathema.lib.resources.IResources;

public final class RepositoryViewAction extends SmartAction {
  private final IAnathemaModel model;
  private final IResources resources;

  public RepositoryViewAction(String name, IAnathemaModel model, IResources resources) {
    super(name);
    this.model = model;
    this.resources = resources;
  }

  @Override
  protected void execute(Component parentComponent) {
    JDialog dialog = GuiUtilities.createDialog(parentComponent, "RepositoryView", true); //$NON-NLS-1$
    JScrollPane scrollPane = new JScrollPane(createRepositoryTreeComponent());
    scrollPane.setPreferredSize(new Dimension(500, 700));
    dialog.getContentPane().add(scrollPane);
    GuiUtilities.show(dialog);
  }

  private JComponent createRepositoryTreeComponent() {
    RepositoryTreeView treeView = new RepositoryTreeView();
    new RepositoryTreePresenter(resources, new RepositoryTreeModel(
        model.getRepository().getPrintNameFileAccess(),
        model.getItemTypeRegistry()), treeView, new RepositoryTreeCellRenderer(resources), "Repository").initPresentation(); //$NON-NLS-1$
    return treeView.getComponent();
  }
}
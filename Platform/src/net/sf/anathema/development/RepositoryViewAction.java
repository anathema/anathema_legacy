package net.sf.anathema.development;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JScrollPane;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.presenter.item.ItemTypeCreationViewPropertiesExtensionPoint;
import net.sf.anathema.framework.repository.tree.RepositoryTreeModel;
import net.sf.anathema.framework.repository.tree.RepositoryTreePresenter;
import net.sf.anathema.framework.repository.tree.RepositoryTreeView;
import net.sf.anathema.lib.gui.GuiUtilities;
import net.sf.anathema.lib.resources.IResources;

public final class RepositoryViewAction extends SmartAction {
  private final IAnathemaModel model;
  private final IResources resources;

  public static Action createMenuAction(IResources resources, IAnathemaModel anathemaModel) {
    SmartAction action = new RepositoryViewAction(anathemaModel, resources);
    action.setName(resources.getString("AnathemaCore.Tools.RepositoryView.ActionTitle") + "\u2026"); //$NON-NLS-1$ //$NON-NLS-2$
    return action;
  }

  public RepositoryViewAction(IAnathemaModel model, IResources resources) {
    this.model = model;
    this.resources = resources;
  }

  @Override
  protected void execute(Component parentComponent) {
    JDialog dialog = GuiUtilities.createDialog(
        parentComponent,
        resources.getString("AnathemaCore.Tools.RepositoryView.DialogTitle"), true); //$NON-NLS-1$
    JScrollPane scrollPane = new JScrollPane(createRepositoryTreeComponent());
    scrollPane.setPreferredSize(new Dimension(500, 700));
    dialog.getContentPane().add(scrollPane);
    GuiUtilities.show(dialog);
  }

  private JComponent createRepositoryTreeComponent() {
    RepositoryTreeView treeView = new RepositoryTreeView();
    ItemTypeCreationViewPropertiesExtensionPoint extension = (ItemTypeCreationViewPropertiesExtensionPoint) model.getExtensionPointRegistry()
        .get(ItemTypeCreationViewPropertiesExtensionPoint.ID);
    ObjectUiTreeCellRenderer renderer = new ObjectUiTreeCellRenderer(new ItemTypeTreeUi(resources, extension));
    new RepositoryTreePresenter(resources, new RepositoryTreeModel(
        model.getRepository(),
        model.getItemManagement(),
        model.getItemTypeRegistry()), treeView, renderer, "AnathemaCore.Tools.RepositoryView.TreeRoot").initPresentation(); //$NON-NLS-1$
    return treeView.getComponent();
  }
}
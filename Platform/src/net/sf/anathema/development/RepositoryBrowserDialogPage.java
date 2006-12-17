package net.sf.anathema.development;

import javax.swing.JComponent;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.userdialog.AbstractDialogPage;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.presenter.item.ItemTypeCreationViewPropertiesExtensionPoint;
import net.sf.anathema.framework.repository.tree.RepositoryTreeModel;
import net.sf.anathema.framework.repository.tree.RepositoryTreePresenter;
import net.sf.anathema.framework.repository.tree.RepositoryTreeView;
import net.sf.anathema.lib.resources.IResources;

public class RepositoryBrowserDialogPage extends AbstractDialogPage {

  private final IResources resources;
  private final IAnathemaModel model;

  public RepositoryBrowserDialogPage(IResources resources, IAnathemaModel model) {
    super(new BasicMessage(resources.getString("AnathemaCore.Tools.RepositoryView.DialogMessage"))); //$NON-NLS-1$
    this.resources = resources;
    this.model = model;
  }

  public JComponent createContent() {
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

  public IBasicMessage createCurrentMessage() {
    return new BasicMessage(resources.getString("AnathemaCore.Tools.RepositoryView.DialogMessage")); //$NON-NLS-1$
  }

  public String getTitle() {
    return resources.getString("AnathemaCore.Tools.RepositoryView.DialogTitle"); //$NON-NLS-1$
  }
}
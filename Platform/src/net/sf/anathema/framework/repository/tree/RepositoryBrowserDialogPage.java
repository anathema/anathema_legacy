package net.sf.anathema.framework.repository.tree;

import javax.swing.JComponent;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.messaging.IAnathemaMessaging;
import net.sf.anathema.framework.presenter.item.ItemTypeCreationViewPropertiesExtensionPoint;
import net.sf.anathema.lib.resources.IResources;

public class RepositoryBrowserDialogPage extends AbstractDialogPage {

  private final IResources resources;
  private final IAnathemaModel model;

  public RepositoryBrowserDialogPage(IResources resources, IAnathemaModel model) {
    super(resources.getString("AnathemaCore.Tools.RepositoryView.DialogMessage")); //$NON-NLS-1$
    this.resources = resources;
    this.model = model;
  }

  public JComponent createContent() {
    RepositoryTreeView treeView = new RepositoryTreeView();
    ItemTypeCreationViewPropertiesExtensionPoint extension = (ItemTypeCreationViewPropertiesExtensionPoint) model.getExtensionPointRegistry()
        .get(ItemTypeCreationViewPropertiesExtensionPoint.ID);
    ObjectUiTreeCellRenderer renderer = new ObjectUiTreeCellRenderer(new ItemTypeTreeUi(resources, extension));
    RepositoryTreeModel repositoryTreeModel = new RepositoryTreeModel(
        model.getRepository(),
        model.getItemManagement(),
        model.getItemTypeRegistry());
    new RepositoryTreePresenter(
        resources,
        repositoryTreeModel,
        treeView,
        renderer,
        "AnathemaCore.Tools.RepositoryView.TreeRoot").initPresentation(); //$NON-NLS-1$
    IAnathemaMessaging messaging = model.getMessaging();
    AmountMessaging fileCountMessaging = new AmountMessaging(messaging, resources);
    new RepositoryItemDeletionPresenter(resources, repositoryTreeModel, treeView, fileCountMessaging).initPresentation();
    new RepositoryItemExportPresenter(resources, repositoryTreeModel, treeView, fileCountMessaging).initPresentation();
    new RepositoryItemImportPresenter(resources, repositoryTreeModel, treeView, fileCountMessaging).initPresentation();
    new RepositoryItemDuplicationPresenter(resources, repositoryTreeModel, treeView, messaging).initPresentation();
    new RepositoryMessagingPresenter(repositoryTreeModel, messaging).initPresentation();
    return treeView.getComponent();
  }

  public IBasicMessage createCurrentMessage() {
    return new BasicMessage(resources.getString("AnathemaCore.Tools.RepositoryView.DialogMessage")); //$NON-NLS-1$
  }

  public String getTitle() {
    return resources.getString("AnathemaCore.Tools.RepositoryView.DialogTitle"); //$NON-NLS-1$
  }
}
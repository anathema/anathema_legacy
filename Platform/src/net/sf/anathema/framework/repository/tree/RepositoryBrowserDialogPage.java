package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.framework.presenter.item.ItemTypeCreationViewPropertiesExtensionPoint;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JComponent;

public class RepositoryBrowserDialogPage extends AbstractDialogPage {

  private final IResources resources;
  private final IAnathemaModel model;

  public RepositoryBrowserDialogPage(IResources resources, IAnathemaModel model) {
    super(resources.getString("AnathemaCore.Tools.RepositoryView.DialogMessage")); //$NON-NLS-1$
    this.resources = resources;
    this.model = model;
  }

  @Override
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
    IMessaging messaging = model.getMessaging();
    AmountMessaging fileCountMessaging = new AmountMessaging(messaging, resources);
    new RepositoryItemDeletionPresenter(resources, repositoryTreeModel, treeView, fileCountMessaging).initPresentation();
    new RepositoryItemExportPresenter(resources, repositoryTreeModel, treeView, fileCountMessaging).initPresentation();
    new RepositoryItemImportPresenter(resources, repositoryTreeModel, treeView, fileCountMessaging).initPresentation();
    new RepositoryItemDuplicationPresenter(resources, repositoryTreeModel, treeView, messaging).initPresentation();
    new RepositoryMessagingPresenter(repositoryTreeModel, messaging).initPresentation();
    return treeView.getComponent();
  }

  @Override
  public IBasicMessage createCurrentMessage() {
    return new BasicMessage(resources.getString("AnathemaCore.Tools.RepositoryView.DialogMessage")); //$NON-NLS-1$
  }

  @Override
  public String getTitle() {
    return resources.getString("AnathemaCore.Tools.RepositoryView.DialogTitle"); //$NON-NLS-1$
  }
}
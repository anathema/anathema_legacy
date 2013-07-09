package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.framework.module.ItemTypePresentationFactory;
import net.sf.anathema.framework.presenter.view.IItemTypeViewProperties;
import net.sf.anathema.initialization.RegisteredItemTypePresentation;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.JComponent;
import java.util.Collection;

public class RepositoryBrowserDialogPage extends AbstractDialogPage {

  private final Resources resources;
  private final IApplicationModel model;

  public RepositoryBrowserDialogPage(Resources resources, IApplicationModel model) {
    super(resources.getString("AnathemaCore.Tools.RepositoryView.DialogMessage"));
    this.resources = resources;
    this.model = model;
  }

  @Override
  public JComponent createContent() {
    RepositoryTreeView treeView = new RepositoryTreeView();
    ItemTypePropertiesMap propertiesMap = registerItemTypePresentations();
    ObjectUiTreeCellRenderer renderer = new ObjectUiTreeCellRenderer(new ItemTypeTreeUi(resources, propertiesMap));
    RepositoryTreeModel repositoryTreeModel = new RepositoryTreeModel(model.getRepository(), model.getItemTypeRegistry());
    new RepositoryTreePresenter(resources, repositoryTreeModel, treeView, renderer, "AnathemaCore.Tools.RepositoryView.TreeRoot")
            .initPresentation();
    IMessaging messaging = model.getMessaging();
    AmountMessaging fileCountMessaging = new AmountMessaging(messaging, resources);
    new RepositoryItemDeletionPresenter(resources, repositoryTreeModel, treeView, fileCountMessaging).initPresentation();
    new RepositoryItemExportPresenter(resources, repositoryTreeModel, treeView, fileCountMessaging).initPresentation();
    new RepositoryItemImportPresenter(resources, repositoryTreeModel, treeView, fileCountMessaging).initPresentation();
    new RepositoryItemDuplicationPresenter(resources, repositoryTreeModel, treeView, messaging).initPresentation();
    new RepositoryMessagingPresenter(repositoryTreeModel, messaging).initPresentation();
    return treeView.getComponent();
  }


  private ItemTypePropertiesMap registerItemTypePresentations() {
    Collection<ItemTypePresentationFactory> presentationFactories = model.getObjectFactory().instantiateAll(RegisteredItemTypePresentation.class);
    ItemTypePropertiesMap map = new ItemTypePropertiesMap();
    for (ItemTypePresentationFactory factory : presentationFactories) {
      IItemTypeViewProperties properties = factory.createItemTypeCreationProperties(model, resources);
      String itemType = factory.getClass().getAnnotation(RegisteredItemTypePresentation.class).itemType();
      map.put(model.getItemTypeRegistry().getById(itemType), properties);
    }
    return map;
  }

  @Override
  public IBasicMessage createCurrentMessage() {
    return new BasicMessage(resources.getString("AnathemaCore.Tools.RepositoryView.DialogMessage"));
  }

  @Override
  public String getTitle() {
    return resources.getString("AnathemaCore.Tools.RepositoryView.DialogTitle");
  }
}
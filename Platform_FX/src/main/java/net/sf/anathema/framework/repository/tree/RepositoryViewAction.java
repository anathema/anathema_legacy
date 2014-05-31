package net.sf.anathema.framework.repository.tree;

import javafx.scene.Node;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.fx.UiEnvironment;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.initialization.ItemTypeCollection;
import net.sf.anathema.interaction.Command;
import org.controlsfx.dialog.Dialog;

public class RepositoryViewAction implements Command {
  private final IApplicationModel model;
  private final Environment environment;
  private final UiEnvironment uiEnvironment;

  public RepositoryViewAction(IApplicationModel model, Environment environment, UiEnvironment uiEnvironment) {
    this.model = model;
    this.environment = environment;
    this.uiEnvironment = uiEnvironment;
  }

  @Override
  public void execute() {
    Dialog dialog = uiEnvironment.createDialog(getTitle());
    dialog.setMasthead(createCurrentMessage());
    dialog.getActions().setAll(Dialog.Actions.OK);
    dialog.setContent(createContent());
    dialog.show();
  }


  private Node createContent() {
    RepositoryTreeView treeView = new RepositoryTreeView();
    ItemTypeCollection itemTypeCollection = new ItemTypeCollection(environment);
    RepositoryTreeModel repositoryTreeModel = new RepositoryTreeModel(model.getRepository(), itemTypeCollection);
    new RepositoryTreePresenter(environment, model,itemTypeCollection, repositoryTreeModel, treeView, "AnathemaCore.Tools.RepositoryView.TreeRoot")
            .initPresentation();
    IMessaging messaging = model.getMessaging();
    AmountMessaging fileCountMessaging = new AmountMessaging(messaging, environment);
    new RepositoryItemDeletionPresenter(environment, repositoryTreeModel, treeView, fileCountMessaging).initPresentation();
    new RepositoryItemExportPresenter(environment, uiEnvironment, repositoryTreeModel, treeView, fileCountMessaging).initPresentation();
    new RepositoryItemImportPresenter(environment, uiEnvironment, repositoryTreeModel, treeView, fileCountMessaging).initPresentation();
    new RepositoryItemDuplicationPresenter(environment, repositoryTreeModel, treeView, messaging).initPresentation();
    new RepositoryMessagingPresenter(repositoryTreeModel, messaging).initPresentation();
    return treeView.getNode();
  }

  private String createCurrentMessage() {
    return environment.getString("AnathemaCore.Tools.RepositoryView.DialogMessage");
  }

  private String getTitle() {
    return environment.getString("AnathemaCore.Tools.RepositoryView.DialogTitle");
  }
}
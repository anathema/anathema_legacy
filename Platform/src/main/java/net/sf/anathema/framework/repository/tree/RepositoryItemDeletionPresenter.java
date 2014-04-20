package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.gui.list.veto.Vetor;

public class RepositoryItemDeletionPresenter {

  private final IRepositoryTreeModel repositoryModel;
  private final IRepositoryTreeView treeView;
  private final AmountMessaging messaging;
  private Environment environment;

  public RepositoryItemDeletionPresenter(Environment environment, RepositoryTreeModel repositoryTreeModel,
                                         IRepositoryTreeView treeView, AmountMessaging fileCountMessaging) {
    this.environment = environment;
    this.repositoryModel = repositoryTreeModel;
    this.treeView = treeView;
    this.messaging = fileCountMessaging;
  }

  public void initPresentation() {
    Tool tool = treeView.addTool();
    tool.setTooltip(environment.getString("AnathemaCore.Tools.RepositoryView.DeleteToolTip"));
    tool.setText(environment.getString("AnathemaCore.Tools.RepositoryView.DeleteName"));
    tool.setIcon(new FileUi().getRemoveFilePath());
    tool.setCommand(new Command() {
      @Override
      public void execute() {
        String message = environment.getString("AnathemaCore.Tools.RepositoryView.DeleteMessage");
        String title = environment.getString("AnathemaCore.Tools.RepositoryView.ConfirmTitle");
        Vetor vetor = treeView.createVetor(message, title);
        vetor.requestPermissionFor(new Command() {
          @Override
          public void execute() {
            try {
              int itemCount = repositoryModel.getPrintNameFilesInSelection().length;
              repositoryModel.deleteSelection();
              messaging.addMessage("AnathemaCore.Tools.RepositoryView.DeleteDoneMessage", itemCount);
            } catch (RepositoryException e) {
              environment.handle(e, environment.getString("AnathemaCore.Tools.RepositoryView.RepositoryError"));
            }
          }
        });
      }
    });
    repositoryModel.addTreeSelectionChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        if (repositoryModel.canSelectionBeDeleted()) {
          tool.enable();
        } else {
          tool.disable();
        }
      }
    });
    tool.disable();
  }
}

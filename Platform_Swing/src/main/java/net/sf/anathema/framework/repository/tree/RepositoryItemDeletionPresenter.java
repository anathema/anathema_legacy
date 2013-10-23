package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.fx.ExceptionIndicator;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.initialization.FxApplicationFrame;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.ConfigurableVetor;
import net.sf.anathema.lib.message.Message;

import java.awt.Component;

public class RepositoryItemDeletionPresenter {

  private final Resources resources;
  private final IRepositoryTreeModel repositoryModel;
  private final RepositoryTreeView treeView;
  private final AmountMessaging messaging;

  public RepositoryItemDeletionPresenter(Resources resources, RepositoryTreeModel repositoryTreeModel,
                                         RepositoryTreeView treeView, AmountMessaging fileCountMessaging) {
    this.resources = resources;
    this.repositoryModel = repositoryTreeModel;
    this.treeView = treeView;
    this.messaging = fileCountMessaging;
  }

  public void initPresentation() {
    final SmartAction action = new SmartAction(resources.getString("AnathemaCore.Tools.RepositoryView.DeleteName"),
            new FileUi().getRemoveFileIcon()) {

      @Override
      protected void execute(final Component parentComponent) {
        String message = resources.getString("AnathemaCore.Tools.RepositoryView.DeleteMessage");
        String okButton = resources.getString("AnathemaCore.Tools.RepositoryView.DeleteOk");
        ConfigurableVetor vetor = new ConfigurableVetor(parentComponent, message, okButton);
        vetor.requestPermissionFor(new Command() {
          @Override
          public void execute() {
            try {
              int itemCount = repositoryModel.getPrintNameFilesInSelection().length;
              repositoryModel.deleteSelection();
              messaging.addMessage("AnathemaCore.Tools.RepositoryView.DeleteDoneMessage", itemCount);
            } catch (RepositoryException e) {
              Message errorMessage = new Message(resources.getString("AnathemaCore.Tools.RepositoryView.RepositoryError"), e);
              ExceptionIndicator.indicate(resources, FxApplicationFrame.getOwner(), errorMessage);
            }
          }
        });
      }
    };
    action.setToolTipText(resources.getString("AnathemaCore.Tools.RepositoryView.DeleteToolTip"));
    treeView.addActionButton(action);
    repositoryModel.addTreeSelectionChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        action.setEnabled(repositoryModel.canSelectionBeDeleted());
      }
    });
    action.setEnabled(false);
  }
}

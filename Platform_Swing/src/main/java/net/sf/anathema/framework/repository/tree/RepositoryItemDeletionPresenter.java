package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.presenter.resources.FileUi;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.message.MessageDialogFactory;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.ConfigurableVetor;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.resources.Resources;

import java.awt.Component;

public class RepositoryItemDeletionPresenter implements Presenter {

  private final Resources resources;
  private final IRepositoryTreeModel repositoryModel;
  private final RepositoryTreeView treeView;
  private final AmountMessaging messaging;

  public RepositoryItemDeletionPresenter(
      Resources resources,
      RepositoryTreeModel repositoryTreeModel,
      RepositoryTreeView treeView,
      AmountMessaging fileCountMessaging) {
    this.resources = resources;
    this.repositoryModel = repositoryTreeModel;
    this.treeView = treeView;
    this.messaging = fileCountMessaging;
  }

  @Override
  public void initPresentation() {
    final SmartAction action = new SmartAction(resources.getString("AnathemaCore.Tools.RepositoryView.DeleteName"),
        new FileUi().getRemoveFileIcon()) {

      @Override
      protected void execute(Component parentComponent) {
        String message = resources.getString("AnathemaCore.Tools.RepositoryView.DeleteMessage");
        String okButton = resources.getString("AnathemaCore.Tools.RepositoryView.DeleteOk");
        ConfigurableVetor vetor = new ConfigurableVetor(parentComponent, message, okButton);
        if (vetor.vetos()) {
          return;
        }
        try {
          int itemCount = repositoryModel.getPrintNameFilesInSelection().length;
          repositoryModel.deleteSelection();
          messaging.addMessage("AnathemaCore.Tools.RepositoryView.DeleteDoneMessage", itemCount);
        }
        catch (RepositoryException e) {
          MessageDialogFactory.showMessageDialog(parentComponent, new Message(
              resources.getString("AnathemaCore.Tools.RepositoryView.RepositoryError"), e));
          Logger.getLogger(getClass()).error(e);
        }
      }
    };
    action.setToolTipText(resources.getString("AnathemaCore.Tools.RepositoryView.DeleteToolTip"));
    treeView.addActionButton(action);
    repositoryModel.addTreeSelectionChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        action.setEnabled(repositoryModel.canSelectionBeDeleted());
      }
    });
    action.setEnabled(false);
  }
}

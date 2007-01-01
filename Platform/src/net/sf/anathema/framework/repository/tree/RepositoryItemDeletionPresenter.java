package net.sf.anathema.framework.repository.tree;

import java.awt.Component;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.message.MessageDialogFactory;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.gui.dialog.ConfigurableVetor;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;

public class RepositoryItemDeletionPresenter implements IPresenter {

  private final IResources resources;
  private final IRepositoryTreeModel repositoryModel;
  private final RepositoryTreeView treeView;
  private final AmountMessaging messaging;

  public RepositoryItemDeletionPresenter(
      IResources resources,
      RepositoryTreeModel repositoryTreeModel,
      RepositoryTreeView treeView,
      AmountMessaging fileCountMessaging) {
    this.resources = resources;
    this.repositoryModel = repositoryTreeModel;
    this.treeView = treeView;
    this.messaging = fileCountMessaging;
  }

  public void initPresentation() {
    final SmartAction action = new SmartAction(new BasicUi(resources).getRemoveIcon()) {
      @Override
      protected void execute(Component parentComponent) {
        String message = resources.getString("AnathemaCore.Tools.RepositoryView.DeleteMessage"); //$NON-NLS-1$
        String okButton = resources.getString("AnathemaCore.Tools.RepositoryView.DeleteOk"); //$NON-NLS-1$
        ConfigurableVetor vetor = new ConfigurableVetor(parentComponent, message, okButton);
        if (vetor.vetos()) {
          return;
        }
        try {
          int itemCount = repositoryModel.getPrintNameFilesInSelection().length;
          repositoryModel.deleteSelection();
          messaging.addMessage("AnathemaCore.Tools.RepositoryView.DeleteDoneMessage", itemCount); //$NON-NLS-1$
        }
        catch (RepositoryException e) {
          MessageDialogFactory.showMessageDialog(parentComponent, new Message(
              resources.getString("AnathemaCore.Tools.RepositoryView.RepositoryError"), e)); //$NON-NLS-1$
          Logger.getLogger(getClass()).error(e);
        }
      }
    };
    action.setToolTipText(resources.getString("AnathemaCore.Tools.RepositoryView.DeleteToolTip")); //$NON-NLS-1$
    treeView.addActionButton(action);
    repositoryModel.addTreeSelectionChangeListener(new IChangeListener() {
      public void changeOccured() {
        action.setEnabled(repositoryModel.canSelectionBeDeleted());
      }
    });
    action.setEnabled(false);
  }
}
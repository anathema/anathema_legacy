package net.sf.anathema.framework.repository.tree;

import net.disy.commons.core.message.MessageType;
import net.sf.anathema.framework.messaging.IAnathemaMessaging;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.gui.IPresenter;

public class RepositoryMessagingPresenter implements IPresenter {

  private final RepositoryTreeModel repositoryTreeModel;
  private final IAnathemaMessaging messaging;

  public RepositoryMessagingPresenter(RepositoryTreeModel repositoryTreeModel, IAnathemaMessaging messaging) {
    this.repositoryTreeModel = repositoryTreeModel;
    this.messaging = messaging;
  }

  public void initPresentation() {
    repositoryTreeModel.addRepositoryTreeModelListener(new IRepositoryTreeModelListener() {
      public void printNameFileAdded(PrintNameFile file) {
        messaging.addMessage("AnathemaCore.Tools.RepositoryView.ItemAddedMessage", //$NON-NLS-1$
            MessageType.INFORMATION,
            file.getPrintName());
      }

      public void printNameFileRemoved(PrintNameFile file) {
        messaging.addMessage("AnathemaCore.Tools.RepositoryView.ItemRemovedMessage", //$NON-NLS-1$
            MessageType.INFORMATION,
            file.getPrintName());
      }
    });
  }
}
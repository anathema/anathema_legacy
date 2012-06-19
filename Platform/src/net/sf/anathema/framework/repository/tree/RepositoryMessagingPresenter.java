package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.message.MessageType;

public class RepositoryMessagingPresenter implements Presenter {

  private final RepositoryTreeModel repositoryTreeModel;
  private final IMessaging messaging;

  public RepositoryMessagingPresenter(RepositoryTreeModel repositoryTreeModel, IMessaging messaging) {
    this.repositoryTreeModel = repositoryTreeModel;
    this.messaging = messaging;
  }

  @Override
  public void initPresentation() {
    repositoryTreeModel.addRepositoryTreeModelListener(new IRepositoryTreeModelListener() {
      @Override
      public void printNameFileAdded(PrintNameFile file) {
        messaging.addMessage("AnathemaCore.Tools.RepositoryView.ItemAddedMessage", //$NON-NLS-1$
            MessageType.INFORMATION,
            file.getPrintName());
      }

      @Override
      public void printNameFileRemoved(PrintNameFile file) {
        messaging.addMessage("AnathemaCore.Tools.RepositoryView.ItemRemovedMessage", //$NON-NLS-1$
            MessageType.INFORMATION,
            file.getPrintName());
      }
    });
  }
}

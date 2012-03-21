package net.sf.anathema.framework.repository.tree;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.message.MessageDialogFactory;
import net.sf.anathema.framework.presenter.resources.FileUi;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.file.FileChoosingUtilities;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;

import java.awt.Component;
import java.io.File;
import java.io.IOException;

public class RepositoryItemExportPresenter implements Presenter {

  private final IResources resources;
  private final IRepositoryTreeModel model;
  private final RepositoryTreeView view;
  private final AmountMessaging messaging;
  private final FileExporter fileExporter;

  public RepositoryItemExportPresenter(
      IResources resources,
      RepositoryTreeModel repositoryTreeModel,
      RepositoryTreeView treeView,
      AmountMessaging fileCountMessaging) {
    this.resources = resources;
    this.model = repositoryTreeModel;
    this.view = treeView;
    this.messaging = fileCountMessaging;
    fileExporter = new FileExporter(new RepositoryZipPathCreator(model.getRepositoryPath()), model, resources);
  }

  public void initPresentation() {
    final SmartAction action = new SmartAction(resources.getString("AnathemaCore.Tools.RepositoryView.ExportName"), //$NON-NLS-1$
        new FileUi(resources).getExportFileIcon()) {
      private static final long serialVersionUID = 134179579435852345L;

      @Override
      protected void execute(Component parentComponent) {
        try {
          File saveFile = FileChoosingUtilities.selectSaveFile(parentComponent, "Export.zip"); //$NON-NLS-1$
          if (saveFile == null) {
            return;
          }
          PrintNameFile[] printNameFiles = fileExporter.exportToZip(saveFile);
          messaging.addMessage("AnathemaCore.Tools.RepositoryView.ExportDoneMessage", printNameFiles.length); //$NON-NLS-1$
        }
        catch (IOException e) {
          MessageDialogFactory.showMessageDialog(parentComponent, new Message(
              resources.getString("AnathemaCore.Tools.RepositoryView.FileError"), //$NON-NLS-1$
              e));
          Logger.getLogger(getClass()).error(e);
        }
      }

    };
    action.setToolTipText(resources.getString("AnathemaCore.Tools.RepositoryView.ExportToolTip")); //$NON-NLS-1$
    view.addActionButton(action);
    model.addTreeSelectionChangeListener(new IChangeListener() {
      public void changeOccurred() {
        action.setEnabled(model.canSelectionBeDeleted());
      }
    });
    action.setEnabled(false);
  }
}
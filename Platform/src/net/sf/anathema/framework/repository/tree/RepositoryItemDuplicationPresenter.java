package net.sf.anathema.framework.repository.tree;

import java.awt.Component;
import java.io.File;
import java.io.IOException;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.message.MessageDialogFactory;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.IRepositoryFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;

public class RepositoryItemDuplicationPresenter implements IPresenter {

  private final IResources resources;
  private final RepositoryTreeModel model;
  private final RepositoryTreeView view;

  public RepositoryItemDuplicationPresenter(
      IResources resources,
      RepositoryTreeModel repositoryTreeModel,
      RepositoryTreeView treeView) {
    this.resources = resources;
    this.model = repositoryTreeModel;
    this.view = treeView;
  }

  public void initPresentation() {
    final SmartAction action = new SmartAction("Copy") {
      @Override
      protected void execute(Component parentComponent) {
        try {
          PrintNameFile[] printNameFiles = model.getPrintNameFilesInSelection();
          for (PrintNameFile printNameFile : printNameFiles) {
            String id = printNameFile.getRepositoryId();
            IItemType type = printNameFile.getItemType();
            IRepositoryFileAccess readAccess = model.getFileAccess(printNameFile);
            String mainFilePath = model.getMainFilePath(type, id);
            RepositoryImportHandler handler = new RepositoryImportHandler(model, type, id);
            for (File file : readAccess.getFiles()) {
              handler.importStream(mainFilePath, readAccess.openInputStream(file), file.getPath());
            }
            model.refreshItem(type, handler.getNewId());
          }
        }
        catch (RepositoryException e) {
          MessageDialogFactory.showMessageDialog(parentComponent, new Message(
              resources.getString("AnathemaCore.Tools.RepositoryView.RepositoryError"), //$NON-NLS-1$
              e));
          Logger.getLogger(getClass()).error(e);
        }
        catch (IOException e) {
          MessageDialogFactory.showMessageDialog(parentComponent, new Message(
              resources.getString("AnathemaCore.Tools.RepositoryView.FileError"), //$NON-NLS-1$
              e));
          Logger.getLogger(getClass()).error(e);
        }
      }
    };
    view.addActionButton(action);
    model.addTreeSelectionChangeListener(new IChangeListener() {
      public void changeOccured() {
        action.setEnabled(model.getPrintNameFilesInSelection().length == 1);
      }
    });
    action.setEnabled(false);
  }
}
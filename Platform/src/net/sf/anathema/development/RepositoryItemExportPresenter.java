package net.sf.anathema.development;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.zip.ZipOutputStream;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.message.MessageDialogFactory;
import net.sf.anathema.framework.repository.tree.IRepositoryTreeModel;
import net.sf.anathema.framework.repository.tree.RepositoryTreeModel;
import net.sf.anathema.framework.repository.tree.RepositoryTreeView;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.gui.file.FileChoosingUtilities;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;

public class RepositoryItemExportPresenter implements IPresenter {

  private final IResources resources;
  private final IRepositoryTreeModel model;
  private final RepositoryTreeView view;

  public RepositoryItemExportPresenter(
      IResources resources,
      RepositoryTreeModel repositoryTreeModel,
      RepositoryTreeView treeView) {
    this.resources = resources;
    this.model = repositoryTreeModel;
    this.view = treeView;
  }

  public void initPresentation() {
    final SmartAction action = new SmartAction("Export") {
      @Override
      protected void execute(Component parentComponent) {
        try {
          File saveFile = FileChoosingUtilities.selectSaveFile(parentComponent, "Export.zip");
          ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(saveFile));
          // TODO: CREATE ZIPFILE AND WRITE SOMEWHERE
        }
        catch (FileNotFoundException e) {
          MessageDialogFactory.showMessageDialog(parentComponent, new Message(
              resources.getString("AnathemaCore.Tools.RepositoryView.DeleteError"), e)); //$NON-NLS-1$
          Logger.getLogger(getClass()).error(e);
        }
      }
    };
    view.addActionButton(action);
    model.addTreeSelectionChangeListener(new IChangeListener() {
      public void changeOccured() {
        action.setEnabled(model.canSelectionBeDeleted());
      }
    });
    action.setEnabled(false);

  }
}

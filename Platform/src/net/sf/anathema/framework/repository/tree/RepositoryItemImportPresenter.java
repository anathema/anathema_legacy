package net.sf.anathema.framework.repository.tree;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.message.MessageDialogFactory;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.resources.PlatformUI;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.gui.file.FileChoosingUtilities;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;

public class RepositoryItemImportPresenter implements IPresenter {

  private final IResources resources;
  private final IRepositoryTreeModel model;
  private final IRepositoryTreeView view;
  private final RepositoryZipPathCreator creator;

  public RepositoryItemImportPresenter(
      IResources resources,
      IRepositoryTreeModel repositoryTreeModel,
      IRepositoryTreeView treeView) {
    this.resources = resources;
    this.model = repositoryTreeModel;
    this.view = treeView;
    this.creator = new RepositoryZipPathCreator(model.getRepositoryPath());
  }

  public void initPresentation() {
    final SmartAction action = new SmartAction(new PlatformUI(resources).getImportIcon()) {
      @Override
      protected void execute(Component parentComponent) {
        try {
          File loadFile = FileChoosingUtilities.chooseFile(
              resources.getString("AnathemaCore.Tools.RepositoryView.ImportOk"), parentComponent, new ZipFileFilter()); //$NON-NLS-1$
          ZipFile importZipFile = new ZipFile(loadFile);
          MultiEntryMap<String, ZipEntry> entriesByItem = groupEntriesByItems(importZipFile);
          for (String comment : entriesByItem.keySet()) {
            String[] splitComment = comment.split("#", 3); //$NON-NLS-1$
            if (!splitComment[0].equals(resources.getString("Anathema.Version.Numeric"))) { //$NON-NLS-1$
              continue;
            }
            IItemType type = model.getItemTypeForId(splitComment[1]);
            String id = splitComment[2];
            String mainFilePath = creator.createZipPath(model.getMainFilePath(type, id));
            RepositoryImportHandler handler = new RepositoryImportHandler(model, type, id);
            for (ZipEntry entry : entriesByItem.get(comment)) {
              handler.importStream(mainFilePath, importZipFile.getInputStream(entry), entry.getName());
            }
            model.refreshItem(type, handler.getNewId());
          }
          importZipFile.close();
        }
        catch (IOException e) {
          MessageDialogFactory.showMessageDialog(parentComponent, new Message(
              resources.getString("AnathemaCore.Tools.RepositoryView.FileError"), //$NON-NLS-1$
              e));
          Logger.getLogger(getClass()).error(e);
        }
        catch (RepositoryException e) {
          MessageDialogFactory.showMessageDialog(parentComponent, new Message(
              resources.getString("AnathemaCore.Tools.RepositoryView.RepositoryError"), //$NON-NLS-1$
              e));
          Logger.getLogger(getClass()).error(e);
        }
      }

      private MultiEntryMap<String, ZipEntry> groupEntriesByItems(ZipFile importZipFile) {
        Enumeration< ? extends ZipEntry> entries = importZipFile.entries();
        MultiEntryMap<String, ZipEntry> entriesByComment = new MultiEntryMap<String, ZipEntry>();
        for (; entries.hasMoreElements();) {
          ZipEntry entry = entries.nextElement();
          entriesByComment.add(entry.getComment(), entry);
        }
        return entriesByComment;
      }

    };
    view.addActionButton(action);
  }
}
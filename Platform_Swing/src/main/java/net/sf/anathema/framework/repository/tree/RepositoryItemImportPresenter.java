package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.resources.FileUi;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.message.MessageDialogFactory;
import net.sf.anathema.lib.gui.file.FileChoosingUtilities;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.resources.Resources;

import java.awt.Component;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class RepositoryItemImportPresenter implements Presenter {

  private final Resources resources;
  private final IRepositoryTreeModel model;
  private final IRepositoryTreeView view;
  private final RepositoryZipPathCreator creator;
  private final AmountMessaging messaging;

  public RepositoryItemImportPresenter(
      Resources resources,
      IRepositoryTreeModel repositoryTreeModel,
      IRepositoryTreeView treeView,
      AmountMessaging fileCountMessaging) {
    this.resources = resources;
    this.model = repositoryTreeModel;
    this.view = treeView;
    this.messaging = fileCountMessaging;
    this.creator = new RepositoryZipPathCreator(model.getRepositoryPath());
  }

  @Override
  public void initPresentation() {
    SmartAction action = new SmartAction(
        resources.getString("AnathemaCore.Tools.RepositoryView.ImportName"), new FileUi().getImportFileIcon()) {

      @Override
      protected void execute(Component parentComponent) {
        try {
          Path loadFile = FileChoosingUtilities.chooseFile(
              resources.getString("AnathemaCore.Tools.RepositoryView.ImportOk"), parentComponent, new ZipFileFilter(resources));
          if (loadFile == null) {
            return;
          }
          ZipFile importZipFile = new ZipFile(loadFile.toFile());
          MultiEntryMap<String, ZipEntry> entriesByItem = groupEntriesByItems(importZipFile);
          for (String comment : entriesByItem.keySet()) {
            String[] splitComment = comment.split("#", 3);
            if (!splitComment[0].equals(resources.getString("Anathema.Version.Numeric"))) {
              continue;
            }
            IItemType type = model.getItemTypeForId(splitComment[1]);
            String id = splitComment[2];
            String mainFilePath = creator.createZipPath(model.getMainFilePath(type, id));
            RepositoryImportHandler handler = new RepositoryImportHandler(model, type, id);
            for (ZipEntry entry : entriesByItem.get(comment)) {
              InputStream inputStream = importZipFile.getInputStream(entry);
              handler.importStream(mainFilePath, inputStream, entry.getName());
              inputStream.close();
            }
            model.refreshItem(type, handler.getNewId());
          }
          importZipFile.close();
          messaging.addMessage("AnathemaCore.Tools.RepositoryView.ImportDoneMessage", entriesByItem.keySet().size());
        }
        catch (ZipException e) {
          MessageDialogFactory.showMessageDialog(parentComponent, new Message(
              resources.getString("AnathemaCore.Tools.RepositoryView.NoZipFileError"),
              e));
          Logger.getLogger(getClass()).error(e);
        }
        catch (IOException e) {
          MessageDialogFactory.showMessageDialog(parentComponent, new Message(
              resources.getString("AnathemaCore.Tools.RepositoryView.FileError"),
              e));
          Logger.getLogger(getClass()).error(e);
        }
        catch (RepositoryException e) {
          MessageDialogFactory.showMessageDialog(parentComponent, new Message(
              resources.getString("AnathemaCore.Tools.RepositoryView.RepositoryError"),
              e));
          Logger.getLogger(getClass()).error(e);
        }
      }
    };
    action.setToolTipText(resources.getString("AnathemaCore.Tools.RepositoryView.ImportToolTip"));
    view.addActionButton(action);
  }

  private MultiEntryMap<String, ZipEntry> groupEntriesByItems(ZipFile importZipFile) {
    Enumeration< ? extends ZipEntry> entries = importZipFile.entries();
    MultiEntryMap<String, ZipEntry> entriesByComment = new MultiEntryMap<>();
    for (; entries.hasMoreElements();) {
      ZipEntry entry = entries.nextElement();
      String comment = entry.getComment();
      if (comment == null) {
        continue;
      }
      entriesByComment.add(comment, entry);
    }
    return entriesByComment;
  }
}

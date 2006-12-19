package net.sf.anathema.development;

import java.awt.Component;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.message.MessageDialogFactory;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.framework.repository.tree.IRepositoryTreeModel;
import net.sf.anathema.framework.repository.tree.IRepositoryTreeView;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.gui.file.FileChoosingUtilities;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;

import org.apache.commons.io.IOUtils;

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
    final SmartAction action = new SmartAction("Import") {
      @Override
      protected void execute(Component parentComponent) {
        try {
          File loadFile = FileChoosingUtilities.chooseFile("Import", parentComponent, new ZipFileFilter());
          ZipFile importZipFile = new ZipFile(loadFile);
          MultiEntryMap<String, ZipEntry> entriesByItem = groupEntriesByItems(importZipFile);
          for (String comment : entriesByItem.keySet()) {
            String[] splitComment = comment.split("#", 3); //$NON-NLS-1$
            if (!splitComment[0].equals(resources.getString("Anathema.Version.Numeric"))) { //$NON-NLS-1$
              continue;
            }
            IItemType type = model.getItemTypeForId(splitComment[1]);
            String id = splitComment[2];
            String uniqueId = model.createUniqueId(type, id);
            IRepositoryWriteAccess access = model.getWriteAccess(type, uniqueId);
            String mainFilePath = creator.createZipPath(model.getMainFilePath(type, id));
            for (ZipEntry entry : entriesByItem.get(comment)) {
              InputStream inputStream = importZipFile.getInputStream(entry);
              String entryName = entry.getName();
              if (entryName.equals(mainFilePath)) {
                writeMainFile(access, inputStream, id, uniqueId);
              }
              else {
                writeSubFile(access, inputStream, entryName);
              }
            }
          }
          importZipFile.close();
        }
        catch (IOException e) {
          MessageDialogFactory.showMessageDialog(parentComponent, new Message(
              resources.getString("AnathemaCore.Tools.RepositoryView.ImportError"),
              e));
          Logger.getLogger(getClass()).error(e);
        }
        catch (RepositoryException e) {
          MessageDialogFactory.showMessageDialog(parentComponent, new Message(
              resources.getString("AnathemaCore.Tools.RepositoryView.ImportError"),
              e));
          Logger.getLogger(getClass()).error(e);
        }
      }

      private void writeSubFile(IRepositoryWriteAccess access, InputStream inputStream, String entryName)
          throws RepositoryException,
          IOException {
        String unextendedFileName = entryName.substring(entryName.lastIndexOf("/") + 1, entryName.lastIndexOf(".")); //$NON-NLS-1$ //$NON-NLS-2$
        OutputStream outputStream = access.createSubOutputStream(unextendedFileName);
        writeFileToRepository(inputStream, outputStream);
      }

      private void writeMainFile(IRepositoryWriteAccess access, InputStream inputStream, String oldId, String newId)
          throws RepositoryException,
          IOException {
        String string = IOUtils.toString(inputStream);
        inputStream.close();
        string.replaceFirst("repositoryId=\"" + oldId + "\"", "repositoryId=\"" + newId + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        OutputStream outputStream = access.createMainOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);
        writer.write(string);
        writer.close();
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

  private void writeFileToRepository(InputStream importStream, OutputStream repositoryStream) throws IOException {
    byte buffer[] = new byte[512];
    int lengthRead = 0;
    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(repositoryStream, 512);
    while ((lengthRead = importStream.read(buffer)) != -1) {
      bufferedOutputStream.write(buffer, 0, lengthRead);
    }
    bufferedOutputStream.close();
    importStream.close();
  }
}
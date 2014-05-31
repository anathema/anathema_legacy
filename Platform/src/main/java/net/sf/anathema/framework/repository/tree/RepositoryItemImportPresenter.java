package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.gui.file.Extension;
import net.sf.anathema.lib.gui.file.SingleFileChooser;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class RepositoryItemImportPresenter {

  private final SingleFileChooser fileChooser;
  private final IRepositoryTreeModel model;
  private final IRepositoryTreeView view;
  private final RepositoryZipPathCreator creator;
  private final AmountMessaging messaging;
  private final Environment environment;

  public RepositoryItemImportPresenter(
          Environment environment, SingleFileChooser fileChooser,
          IRepositoryTreeModel repositoryTreeModel,
          IRepositoryTreeView treeView,
          AmountMessaging fileCountMessaging) {
    this.environment = environment;
    this.fileChooser = fileChooser;
    this.model = repositoryTreeModel;
    this.view = treeView;
    this.messaging = fileCountMessaging;
    this.creator = new RepositoryZipPathCreator(model.getRepositoryPath());
  }

  public void initPresentation() {
    Tool tool = view.addTool();
    tool.setTooltip(environment.getString("AnathemaCore.Tools.RepositoryView.ImportToolTip"));
    tool.setText(environment.getString("AnathemaCore.Tools.RepositoryView.ImportName"));
    tool.setIcon(new FileUi().getImportFilePath());
    tool.setCommand(() -> {
      try {
        String description = environment.getString("Filetype.zip");
        Path loadFile = fileChooser.selectLoadFile(new Extension(description, "*.zip"));
        if (loadFile == null) {
          return;
        }
        ZipFile importZipFile = new ZipFile(loadFile.toFile());
        MultiEntryMap<String, ZipEntry> entriesByItem = groupEntriesByItems(importZipFile);
        for (String comment : entriesByItem.keySet()) {
          String[] splitComment = comment.split("#", 3);
          if (!splitComment[0].equals(environment.getString("Anathema.Version.Numeric"))) {
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
      } catch (ZipException e) {
        environment.handle(e, environment.getString("AnathemaCore.Tools.RepositoryView.NoZipFileError"));
      } catch (IOException e) {
        environment.handle(e, environment.getString("AnathemaCore.Tools.RepositoryView.FileError"));
      } catch (RepositoryException e) {
        environment.handle(e, environment.getString("AnathemaCore.Tools.RepositoryView.RepositoryError"));
      }
    });
  }

  private MultiEntryMap<String, ZipEntry> groupEntriesByItems(ZipFile importZipFile) {
    Enumeration<? extends ZipEntry> entries = importZipFile.entries();
    MultiEntryMap<String, ZipEntry> entriesByComment = new MultiEntryMap<>();
    for (; entries.hasMoreElements(); ) {
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
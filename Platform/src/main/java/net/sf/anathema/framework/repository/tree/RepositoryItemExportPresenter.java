package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.file.Extension;
import net.sf.anathema.lib.gui.file.FileChooserConfiguration;
import net.sf.anathema.lib.gui.file.SingleFileChooser;

import java.io.IOException;
import java.nio.file.Path;

public class RepositoryItemExportPresenter {

  private final IRepositoryTreeModel model;
  private final IRepositoryTreeView view;
  private final AmountMessaging messaging;
  private final FileExporter fileExporter;
  private final Environment environment;
  private SingleFileChooser fileChooser;

  public RepositoryItemExportPresenter(
          Environment environment, SingleFileChooser fileChooser,
          RepositoryTreeModel repositoryTreeModel,
          IRepositoryTreeView treeView,
          AmountMessaging fileCountMessaging) {
    this.environment = environment;
    this.fileChooser = fileChooser;
    this.model = repositoryTreeModel;
    this.view = treeView;
    this.messaging = fileCountMessaging;
    fileExporter = new FileExporter(new RepositoryZipPathCreator(model.getRepositoryPath()), model, environment);
  }

  public void initPresentation() {
    Tool tool = view.addTool();
    tool.setTooltip(environment.getString("AnathemaCore.Tools.RepositoryView.ExportToolTip"));
    tool.setText(environment.getString("AnathemaCore.Tools.RepositoryView.ExportName"));
    tool.setIcon(new FileUi().getExportFilePath());
    tool.setCommand(() -> {
      try {
        String description = environment.getString("Filetype.all");
        Path saveFile = fileChooser.selectSaveFile(new FileChooserConfiguration(new Extension(description, "*.*"), "Export.zip"));
        if (saveFile == null) {
          return;
        }
        PrintNameFile[] printNameFiles = fileExporter.exportToZip(saveFile);
        messaging.addMessage("AnathemaCore.Tools.RepositoryView.ExportDoneMessage", printNameFiles.length);
      } catch (IOException e) {
        environment.handle(e, environment.getString("AnathemaCore.Tools.RepositoryView.FileError"));
      }
    });
    model.addTreeSelectionChangeListener(() -> {
      if (model.canSelectionBeDeleted()) {
        tool.enable();
      } else {
        tool.disable();
      }
    });
    tool.disable();
  }
}
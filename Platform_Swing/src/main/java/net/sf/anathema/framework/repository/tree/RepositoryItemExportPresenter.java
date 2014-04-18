package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.fx.ExceptionIndicator;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.initialization.FxApplicationFrame;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.gui.file.Extension;
import net.sf.anathema.lib.message.Message;

import java.io.IOException;
import java.nio.file.Path;

public class RepositoryItemExportPresenter {

  private final Resources resources;
  private final IRepositoryTreeModel model;
  private final IRepositoryTreeView view;
  private final AmountMessaging messaging;
  private final FileExporter fileExporter;

  public RepositoryItemExportPresenter(
          Resources resources,
          RepositoryTreeModel repositoryTreeModel,
          IRepositoryTreeView treeView,
          AmountMessaging fileCountMessaging) {
    this.resources = resources;
    this.model = repositoryTreeModel;
    this.view = treeView;
    this.messaging = fileCountMessaging;
    fileExporter = new FileExporter(new RepositoryZipPathCreator(model.getRepositoryPath()), model, resources);
  }

  public void initPresentation() {
    Tool tool = view.addTool();
    tool.setTooltip(resources.getString("AnathemaCore.Tools.RepositoryView.ExportToolTip"));
    tool.setText(resources.getString("AnathemaCore.Tools.RepositoryView.ExportName"));
    tool.setIcon(new FileUi().getExportFilePath());
    tool.setCommand(new Command() {
      @Override
      public void execute() {
        try {
          String description = resources.getString("Filetype.all");
          Path saveFile = view.showSaveFile("Export.zip", new Extension(description, "*.*"));
          if (saveFile == null) {
            return;
          }
          PrintNameFile[] printNameFiles = fileExporter.exportToZip(saveFile);
          messaging.addMessage("AnathemaCore.Tools.RepositoryView.ExportDoneMessage", printNameFiles.length);
        } catch (IOException e) {
          Message message = new Message(resources.getString("AnathemaCore.Tools.RepositoryView.FileError"), e);
          ExceptionIndicator.indicate(resources, FxApplicationFrame.getOwner(), message);
        }
      }
    });
    model.addTreeSelectionChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        if (model.canSelectionBeDeleted()) {
          tool.enable();
        } else {
          tool.disable();
        }
      }
    });
    tool.disable();
  }
}
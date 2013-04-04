package net.sf.anathema.framework.presenter.resources;

import net.sf.anathema.lib.gui.ui.AbstractUI;

import javax.swing.Icon;

public class FileUi extends AbstractUI {

  public Icon getRemoveFileIcon() {
    return getIcon("ButtonRemoveFile16.png");
  }

  public Icon getDuplicateFileIcon() {
    return getIcon("ButtonDuplicate16.png");
  }

  public Icon getImportFileIcon() {
    return getIcon("ButtonImport16.png");
  }

  public Icon getExportFileIcon() {
    return getIcon("ButtonExport16.png");
  }
}
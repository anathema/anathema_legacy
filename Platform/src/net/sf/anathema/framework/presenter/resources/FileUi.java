package net.sf.anathema.framework.presenter.resources;

import net.sf.anathema.lib.gui.ui.AbstractUI;

import javax.swing.Icon;

public class FileUi extends AbstractUI {

  public Icon getRemoveFileIcon() {
    return getIcon("ButtonRemoveFile16.png"); //$NON-NLS-1$
  }

  public Icon getDuplicateFileIcon() {
    return getIcon("ButtonDuplicate16.png"); //$NON-NLS-1$
  }

  public Icon getImportFileIcon() {
    return getIcon("ButtonImport16.png"); //$NON-NLS-1$
  }

  public Icon getExportFileIcon() {
    return getIcon("ButtonExport16.png"); //$NON-NLS-1$
  }
}
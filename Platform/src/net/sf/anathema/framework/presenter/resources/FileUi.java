package net.sf.anathema.framework.presenter.resources;

import javax.swing.Icon;

import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

public class FileUi extends AbstractUI {

  public FileUi(IResources resources) {
    super(resources);
  }

  public Icon getAddFileIcon() {
    return getIcon("ButtonAddFile16.png"); //$NON-NLS-1$
  }

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
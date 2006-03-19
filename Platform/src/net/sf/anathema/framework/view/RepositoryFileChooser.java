package net.sf.anathema.framework.view;

import java.io.File;

import javax.swing.JOptionPane;

import net.disy.commons.swing.dialog.userdialog.UserDialog;
import net.sf.anathema.framework.ObjectSelectionDialogPage;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.repository.EmptyRepositoryException;
import net.sf.anathema.framework.repository.IRepositoryFileChooser;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.repository.filechooser.RepositoryFileChooserPropertiesExtensionPoint;

public class RepositoryFileChooser implements IRepositoryFileChooser {

  private final IItemMangementModel itemManagement;
  private final RepositoryFileChooserPropertiesExtensionPoint propertiesExtension;
  private final IPrintNameFileAccess printNameFileAccess;

  public RepositoryFileChooser(
      IPrintNameFileAccess printNameFileAccess,
      IItemMangementModel itemManagement,
      RepositoryFileChooserPropertiesExtensionPoint properties) {
    this.printNameFileAccess = printNameFileAccess;
    this.itemManagement = itemManagement;
    this.propertiesExtension = properties;
  }

  public File getRepositoryFile(IItemType type) throws EmptyRepositoryException {
    PrintNameFile[] printNameFiles = printNameFileAccess.collectPrintNameFiles(type, itemManagement);
    if (printNameFiles.length == 0) {
      throw new EmptyRepositoryException();
    }
    ObjectSelectionDialogPage page = new ObjectSelectionDialogPage(printNameFiles, propertiesExtension.get(type));
    UserDialog dialog = new UserDialog(JOptionPane.getRootFrame(), page);
    dialog.show();
    if (dialog.isCanceled()) {
      return null;
    }
    PrintNameFile printNameFile = (PrintNameFile) page.getSelectedObject();
    if (printNameFile == null) {
      return null;
    }
    return printNameFile.getFile();
  }
}
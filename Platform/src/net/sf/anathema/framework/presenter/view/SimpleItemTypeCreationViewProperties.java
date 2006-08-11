package net.sf.anathema.framework.presenter.view;

import javax.swing.Icon;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.module.NullWizardPageFactory;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileScanner;
import net.sf.anathema.framework.repository.access.printname.NullPrintNameFileScanner;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

public class SimpleItemTypeCreationViewProperties extends AbstractItemTypeCreationViewProperties {

  public SimpleItemTypeCreationViewProperties(IItemType type, Icon icon) {
    super(type, icon);
  }

  public Icon getPrintNameFileIcon(PrintNameFile file) {
    return getIcon();
  }

  public String getPrintNameFileLabel(PrintNameFile file) {
    return file.toString();
  }

  public IWizardFactory getNewItemWizardFactory() {
    return new NullWizardPageFactory();
  }

  public IPrintNameFileScanner getScanner() {
    return new NullPrintNameFileScanner();
  }
}
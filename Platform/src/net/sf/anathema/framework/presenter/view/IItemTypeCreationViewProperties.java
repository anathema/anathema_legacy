package net.sf.anathema.framework.presenter.view;

import javax.swing.Icon;

import net.sf.anathema.framework.repository.access.printname.IPrintNameFileScanner;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

public interface IItemTypeCreationViewProperties {

  public Icon getIcon();

  public IWizardFactory getNewItemWizardFactory();

  public String getLabelKey();

  public Icon getPrintNameFileIcon(PrintNameFile file);

  public String getPrintNameFileLabel(PrintNameFile file);

  public IPrintNameFileScanner getScanner();
}
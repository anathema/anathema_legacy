package net.sf.anathema.framework.presenter.view;

import javax.swing.Icon;

import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileScanner;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

public interface IItemTypeCreationViewProperties {

  public Icon getIcon();

  public IWizardFactory getNewItemWizardFactory();

  public String getLabelKey();

  public IPrintNameFileScanner getScanner();

  public IObjectUi getItemTypeUI();
}
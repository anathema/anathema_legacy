package net.sf.anathema.lib.gui.wizard;

import net.disy.commons.swing.dialog.wizard.IBasicWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;

public interface IAnathemaWizardPage extends IBasicWizardPage {
  
  public void initPresentation(CheckInputListener inputListener);

  public void setPreviousPage(IAnathemaWizardPage page);
}
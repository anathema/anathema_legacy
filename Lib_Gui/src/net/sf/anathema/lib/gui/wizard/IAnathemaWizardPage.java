package net.sf.anathema.lib.gui.wizard;

import net.sf.anathema.lib.gui.dialog.wizard.IWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;

public interface IAnathemaWizardPage extends IWizardPage {

  public void initPresentation(CheckInputListener inputListener);

  public void setPreviousPage(IAnathemaWizardPage page);
}
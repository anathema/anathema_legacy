package net.sf.anathema.lib.gui.wizard;

import net.disy.commons.swing.dialog.wizard.IBasicWizardPage;

public abstract class AbstractAnathemaWizardPage implements IBasicWizardPage {

  public boolean canFlipToNextPage() {
    return getNextPage() != null;
  }

  public String getTitle() {
    return getDescription();
  }

  public void performHelp() {
    //Nothing to do
  }

  public boolean isHelpAvailable() {
    return false;
  }
}
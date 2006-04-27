package net.sf.anathema.lib.gui.wizard;


public abstract class AbstractAnathemaWizardPage implements IAnathemaWizardPage {

  private IAnathemaWizardPage previousPage;

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

  public final IAnathemaWizardPage getPreviousPage() {
    return previousPage;
  }

  public final void setPreviousPage(IAnathemaWizardPage page) {
    this.previousPage = page;
  }
}
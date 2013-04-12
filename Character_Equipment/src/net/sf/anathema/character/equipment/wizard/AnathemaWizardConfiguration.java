package net.sf.anathema.character.equipment.wizard;

import com.google.common.base.Preconditions;

public class AnathemaWizardConfiguration extends AbstractWizardConfiguration {

  private final IAnathemaWizardPage startPage;
  private final CheckInputListener inputListener = new CheckInputListener(new Runnable() {
    @Override
    public void run() {
      IWizardContainer wizardContainer = getContainer();
      if (wizardContainer == null) {
        return;
      }
      wizardContainer.updateButtons();
      wizardContainer.updateMessage();
    }
  });

  public AnathemaWizardConfiguration(IAnathemaWizardPage startPage) {
    Preconditions.checkNotNull(startPage);
    this.startPage = startPage;
    this.startPage.initPresentation(inputListener);
  }

  @Override
  public IWizardPage getStartingPage() {
    return startPage;
  }
}
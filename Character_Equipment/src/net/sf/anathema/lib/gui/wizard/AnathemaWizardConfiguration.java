package net.sf.anathema.lib.gui.wizard;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.gui.dialog.wizard.AbstractWizardConfiguration;
import net.sf.anathema.lib.gui.dialog.wizard.IWizardContainer;
import net.sf.anathema.lib.gui.dialog.wizard.IWizardPage;

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
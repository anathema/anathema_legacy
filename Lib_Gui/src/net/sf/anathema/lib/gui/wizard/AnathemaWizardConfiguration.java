package net.sf.anathema.lib.gui.wizard;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.gui.dialog.wizard.AbstractWizardConfiguration;
import net.sf.anathema.lib.gui.dialog.wizard.IWizardContainer;
import net.sf.anathema.lib.gui.dialog.wizard.IWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;
import net.sf.anathema.lib.util.SimpleBlock;

public class AnathemaWizardConfiguration extends AbstractWizardConfiguration {

  private final IAnathemaWizardPage startPage;
  private final CheckInputListener inputListener = new CheckInputListener(new SimpleBlock() {
    @Override
    public void execute() {
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

  private IWizardContainer container;

  @Override
  public IWizardContainer getContainer() {
    return container;
  }

  @Override
  public void setContainer(IWizardContainer container) {
    this.container = container;
  }

  @Override
  public boolean canFinish() {
    return getContainer().getCurrentPage().canFinish();
  }

  @Override
  public IWizardPage getStartingPage() {
    return startPage;
  }

  @Override
  public void addPages() {
  }

}
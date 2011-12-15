package net.sf.anathema.lib.gui.wizard;

import javax.swing.Icon;

import net.disy.commons.core.util.Ensure;
import net.disy.commons.core.util.SimpleBlock;
import net.disy.commons.swing.dialog.wizard.AbstractWizardConfiguration;
import net.disy.commons.swing.dialog.wizard.IWizardContainer;
import net.disy.commons.swing.dialog.wizard.IWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;

public class AnathemaWizardConfiguration extends AbstractWizardConfiguration {

  private final IAnathemaWizardPage startPage;
  private final CheckInputListener inputListener = new CheckInputListener(new SimpleBlock() {
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
    Ensure.ensureArgumentNotNull(startPage);
    this.startPage = startPage;
    this.startPage.initPresentation(inputListener);
  }

  private IWizardContainer container;

  public IWizardContainer getContainer() {
    return container;
  }

  public void setContainer(IWizardContainer container) {
    this.container = container;
  }

  public boolean canFinish() {
    return getContainer().getCurrentPage().canFinish();
  }

  public boolean isHelpAvailable() {
    return false;
  }

  public boolean isHeaderPanelVisible() {
    return true;
  }

  public IWizardPage getStartingPage() {
    return startPage;
  }

  public Icon getLargeDialogIcon() {
    return null;
  }

  @Override
  public void addPages() {
  }

  @Override
  public IWizardPage getNextPage(IWizardPage page) {
    return page != null ? page.getNextPage() : null;
  }

  @Override
  public IWizardPage getPreviousPage(IWizardPage page) {
    return page != null ? page.getPreviousPage() : null;
  }
}
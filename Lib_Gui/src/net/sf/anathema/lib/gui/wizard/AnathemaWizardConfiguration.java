package net.sf.anathema.lib.gui.wizard;

import com.google.common.base.Preconditions;
import net.disy.commons.core.util.SimpleBlock;
import net.disy.commons.swing.dialog.wizard.AbstractWizardConfiguration;
import net.disy.commons.swing.dialog.wizard.IWizardContainer;
import net.disy.commons.swing.dialog.wizard.IWizardPage;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;

import javax.swing.Icon;

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
  public boolean isHelpAvailable() {
    return false;
  }

  public boolean isHeaderPanelVisible() {
    return true;
  }

  @Override
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
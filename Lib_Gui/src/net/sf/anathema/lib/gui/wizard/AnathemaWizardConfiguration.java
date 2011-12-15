package net.sf.anathema.lib.gui.wizard;

import javax.swing.Icon;

import net.disy.commons.core.util.Ensure;
import net.disy.commons.core.util.SimpleBlock;
import net.disy.commons.swing.action.ActionConfiguration;
import net.disy.commons.swing.action.IActionConfiguration;
import net.disy.commons.swing.dialog.DisyCommonsSwingDialogMessages;
import net.disy.commons.swing.dialog.userdialog.buttons.DialogButtonConfiguration;
import net.disy.commons.swing.dialog.userdialog.buttons.IDialogButtonConfiguration;
import net.disy.commons.swing.dialog.wizard.IBasicWizardConfiguration;
import net.disy.commons.swing.dialog.wizard.IBasicWizardPage;
import net.disy.commons.swing.dialog.wizard.IWizardContainer;
import net.sf.anathema.lib.gui.wizard.workflow.CheckInputListener;

public class AnathemaWizardConfiguration implements IBasicWizardConfiguration {

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

  public final IDialogButtonConfiguration getButtonConfiguration() {
    return new DialogButtonConfiguration() {
      @Override
      public IActionConfiguration getOkActionConfiguration() {
        return new ActionConfiguration(DisyCommonsSwingDialogMessages.WIZARD_FINISH);
      }
    };
  }

  public IBasicWizardPage getStartingPage() {
    return startPage;
  }

  public Icon getLargeDialogIcon() {
    return null;
  }
}
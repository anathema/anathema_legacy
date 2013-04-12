package net.sf.anathema.character.equipment.wizard;

import net.sf.anathema.lib.gui.dialog.DialogMessages;
import net.sf.anathema.lib.gui.dialog.core.AbstractGenericDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.core.DialogHeaderPanelConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.DialogButtonConfigurationFactory;

/**
 * Abstract superclass that can be extended for {@link IWizardConfiguration} implementations.
 */
public abstract class AbstractWizardConfiguration extends AbstractGenericDialogConfiguration implements IWizardConfiguration {
  private IWizardContainer container;

  public AbstractWizardConfiguration() {
    super(DialogButtonConfigurationFactory.createOkCancelWithOkText(DialogMessages.WIZARD_FINISH),
            DialogHeaderPanelConfiguration.createVisible());
  }

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
    IWizardPage currentPage = getContainer().getCurrentPage();
    return currentPage.canFinish();
  }
}
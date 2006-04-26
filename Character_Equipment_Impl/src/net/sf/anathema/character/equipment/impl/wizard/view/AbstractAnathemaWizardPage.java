package net.sf.anathema.character.equipment.impl.wizard.view;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.wizard.AbstractWizardPage;
import net.disy.commons.swing.dialog.wizard.IWizardConfiguration;

public abstract class AbstractAnathemaWizardPage extends AbstractWizardPage {

  public AbstractAnathemaWizardPage(String description, IBasicMessage defaultMessage, IWizardConfiguration wizard) {
    super(description, null, defaultMessage, wizard);
  }
  
  public void performHelp() {
    throw new UnsupportedOperationException();
  }
  
  public boolean isHelpAvailable() {
    return false;
  }
  
  public abstract boolean isComplete();
}
package net.sf.anathema.equipment.editor.wizard;

import java.awt.Component;

public class AnathemaWizardDialog extends WizardDialog {

  public AnathemaWizardDialog(Component parent, IAnathemaWizardPage startPage) {
    super(parent, new AnathemaWizardConfiguration(startPage));
  }
}
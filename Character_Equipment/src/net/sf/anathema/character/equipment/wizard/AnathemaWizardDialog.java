package net.sf.anathema.character.equipment.wizard;

import net.sf.anathema.lib.gui.dialog.wizard.WizardDialog;

import java.awt.Component;

public class AnathemaWizardDialog extends WizardDialog {

  public AnathemaWizardDialog(Component parent, IAnathemaWizardPage startPage) {
    super(parent, new AnathemaWizardConfiguration(startPage));
  }
}
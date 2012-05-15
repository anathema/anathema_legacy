package net.sf.anathema.lib.gui.wizard;

import net.sf.anathema.lib.gui.dialog.wizard.WizardDialog;

import java.awt.Component;

public class AnathemaWizardDialog extends WizardDialog {

  public AnathemaWizardDialog(Component parent, IAnathemaWizardPage startPage) {
    super(parent, new AnathemaWizardConfiguration(startPage));
  }
}
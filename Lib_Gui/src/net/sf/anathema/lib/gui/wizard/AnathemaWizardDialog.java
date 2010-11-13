package net.sf.anathema.lib.gui.wizard;

import java.awt.Component;

import net.disy.commons.swing.dialog.wizard.WizardDialog;

public class AnathemaWizardDialog extends WizardDialog {

  public AnathemaWizardDialog(Component parent, IAnathemaWizardPage startPage) {
    super(parent, new AnathemaWizardConfiguration(startPage));
  }
}
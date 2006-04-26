package net.sf.anathema.lib.gui.wizard;

import java.awt.Component;

import net.disy.commons.swing.dialog.wizard.IBasicWizardPage;
import net.disy.commons.swing.dialog.wizard.WizardDialog;

public class AnathemaWizardDialog extends WizardDialog {

  public AnathemaWizardDialog(Component parent, IBasicWizardPage startPage) {
    super(parent, new AnathemaWizardConfiguration(startPage));
  }
}
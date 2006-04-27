package net.sf.anathema.lib.gui.wizard;

import net.disy.commons.swing.dialog.wizard.IBasicWizardPage;

public interface IAnathemaWizardPage extends IBasicWizardPage {
  
  public void initPresentation(CheckInputListener inputListener);
}
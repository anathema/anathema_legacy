package net.sf.anathema.character.equipment.wizard;

import net.sf.anathema.lib.gui.dialog.wizard.IWizardPage;

public interface IAnathemaWizardPage extends IWizardPage {

  void initPresentation(CheckInputListener inputListener);

  void setPreviousPage(IAnathemaWizardPage page);
}
package net.sf.anathema.character.equipment.wizard;

public interface IAnathemaWizardPage extends IWizardPage {

  void initPresentation(CheckInputListener inputListener);

  void setPreviousPage(IAnathemaWizardPage page);
}
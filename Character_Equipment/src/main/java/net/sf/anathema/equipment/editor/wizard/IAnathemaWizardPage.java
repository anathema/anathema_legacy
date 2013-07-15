package net.sf.anathema.equipment.editor.wizard;

public interface IAnathemaWizardPage extends IWizardPage {

  void initPresentation(CheckInputListener inputListener);

  void setPreviousPage(IAnathemaWizardPage page);
}
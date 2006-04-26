package net.sf.anathema.character.equipment.impl.wizard;

import net.disy.commons.swing.dialog.wizard.AbstractWizardConfiguration;
import net.disy.commons.swing.dialog.wizard.IWizardPage;
import net.sf.anathema.character.equipment.impl.wizard.model.AddEquipmentStatisticsWizardModel;
import net.sf.anathema.character.equipment.impl.wizard.view.AbstractAnathemaWizardPage;
import net.sf.anathema.character.equipment.impl.wizard.view.CloseCombatStatisticsPage;
import net.sf.anathema.character.equipment.impl.wizard.view.EquipmentStatisticsChoicePage;

public class AddEquipmentStatisticWizard extends AbstractWizardConfiguration {

  private IWizardPage typeChoicePage;
  private IWizardPage closeCombatStatisticsPage;

  public AddEquipmentStatisticWizard(
      AddEquipmentStatisticsWizardModel model,
      IAddEquipmentStatisticsProperties properties) {
    this.typeChoicePage = new EquipmentStatisticsChoicePage(this, model, properties);
    this.closeCombatStatisticsPage = new CloseCombatStatisticsPage(this, model);
  }

  public IWizardPage getStartingPage() {
    return typeChoicePage;
  }

  public IWizardPage getNextPage(IWizardPage page) {
    if (!((AbstractAnathemaWizardPage) page).isComplete()) {
      return null;
    }
    return page == typeChoicePage ? closeCombatStatisticsPage : null;
  }

  public IWizardPage getPreviousPage(IWizardPage page) {
    return page != typeChoicePage ? typeChoicePage : null;
  }

  public boolean isHelpAvailable() {
    return false;
  }

  public void addPages() {
    //Nothing to do
  }
}
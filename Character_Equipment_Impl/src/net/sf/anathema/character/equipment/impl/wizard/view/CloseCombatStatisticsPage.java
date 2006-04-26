package net.sf.anathema.character.equipment.impl.wizard.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.wizard.IWizardConfiguration;
import net.sf.anathema.character.equipment.impl.wizard.model.AddEquipmentStatisticsWizardModel;

public class CloseCombatStatisticsPage extends AbstractAnathemaWizardPage {

  public CloseCombatStatisticsPage(IWizardConfiguration wizard, AddEquipmentStatisticsWizardModel model) {
    super("description", new BasicMessage("Basic Message"), wizard);
  }

  @Override
  protected IBasicMessage createCurrentMessage() {
    return getDefaultMessage();
  }

  @Override
  protected JComponent createContent() {
    return new JPanel();
  }

  public boolean canFinish() {
    return isComplete();
  }

  public void requestFocus() {
  }

  @Override
  public boolean isComplete() {
    return false;
  }
}
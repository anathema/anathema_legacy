package net.sf.anathema.test.character.equipment.item;

import net.disy.commons.swing.dialog.wizard.WizardDialog;
import net.sf.anathema.character.equipment.impl.wizard.AddEquipmentStatisticWizard;
import net.sf.anathema.character.equipment.impl.wizard.AddEquipmentStatsticsProperties;
import net.sf.anathema.character.equipment.impl.wizard.model.AddEquipmentStatisticsWizardModel;
import net.sf.anathema.framework.resources.AnathemaResources;
import de.jdemo.extensions.SwingDemoCase;

public class AddEquipmentStatisticsWizardDemo extends SwingDemoCase {

  public void demo() {
    AddEquipmentStatisticWizard statisticWizard = new AddEquipmentStatisticWizard(
        new AddEquipmentStatisticsWizardModel(),
        new AddEquipmentStatsticsProperties(new AnathemaResources()));
    WizardDialog dialog = new WizardDialog(createParentComponent(), statisticWizard);
    show(dialog.getConfiguredDialog().getWindow());
  }
}
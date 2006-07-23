package net.sf.anathema.character.equipment.impl.character.model;

import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.impl.character.model.natural.TotalArmour;
import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;

public abstract class AbstractEquipmentAdditionalModel extends AbstractAdditionalModelAdapter implements
    IEquipmentAdditionalModel {

  public final AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Miscellaneous;
  }

  public final String getTemplateId() {
    return EquipmentAdditonalModelTemplate.ID;
  }

  public final IArmourStats getTotalPrintArmour(int lineCount) {
    TotalArmour armour = new TotalArmour();
    IArmourStats[] printArmours = getPrintArmours();
    for (int index = 0; index < Math.min(lineCount, printArmours.length); index++) {
      armour.addArmour(printArmours[index]);
    }
    return armour;
  }
}
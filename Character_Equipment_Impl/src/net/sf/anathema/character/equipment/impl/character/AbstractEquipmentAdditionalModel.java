package net.sf.anathema.character.equipment.impl.character;

import net.sf.anathema.character.equipment.character.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.impl.character.natural.TotalArmour;
import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.equipment.weapon.IArmour;

public abstract class AbstractEquipmentAdditionalModel extends AbstractAdditionalModelAdapter implements
    IEquipmentAdditionalModel {

  public final AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Miscellaneous;
  }

  public final String getTemplateId() {
    return EquipmentAdditonalModelTemplate.ID;
  }

  public final IArmour getTotalPrintArmour(int lineCount) {
    TotalArmour armour = new TotalArmour();
    IArmour[] printArmours = getPrintArmours();
    for (int index = 0; index < Math.min(lineCount, printArmours.length); index++) {
      armour.addArmour(printArmours[index]);
    }
    return armour;
  }
}
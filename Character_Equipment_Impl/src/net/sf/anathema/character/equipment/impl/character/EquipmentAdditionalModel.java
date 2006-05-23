package net.sf.anathema.character.equipment.impl.character;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.character.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.impl.character.natural.NaturalSoak;
import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.equipment.weapon.IArmour;
import net.sf.anathema.character.generic.equipment.weapon.IWeapon;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.traits.types.AttributeType;

public class EquipmentAdditionalModel extends AbstractAdditionalModelAdapter implements IEquipmentAdditionalModel {

  private final List<IArmour> armours = new ArrayList<IArmour>();

  public EquipmentAdditionalModel(ICharacterModelContext context) {
    armours.add(new NaturalSoak(
        context.getTraitCollection().getTrait(AttributeType.Stamina),
        context.getBasicCharacterContext().getCharacterType()));
  }

  public IArmour[] getPrintArmours() {
    return armours.toArray(new IArmour[armours.size()]);
  }

  public IWeapon[] getPrintWeapons() {
    return new IWeapon[0];
  }

  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Miscellaneous;
  }

  public String getTemplateId() {
    return EquipmentAdditonalModelTemplate.ID;
  }
}
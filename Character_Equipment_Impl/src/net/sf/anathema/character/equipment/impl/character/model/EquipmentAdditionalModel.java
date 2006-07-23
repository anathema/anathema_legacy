package net.sf.anathema.character.equipment.impl.character.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.character.model.IEquipmentObject;
import net.sf.anathema.character.equipment.impl.character.model.natural.Clinch;
import net.sf.anathema.character.equipment.impl.character.model.natural.Kick;
import net.sf.anathema.character.equipment.impl.character.model.natural.NaturalSoak;
import net.sf.anathema.character.equipment.impl.character.model.natural.Punch;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.traits.types.AttributeType;

public class EquipmentAdditionalModel extends AbstractEquipmentAdditionalModel {

  private final List<IArmourStats> armours = new ArrayList<IArmourStats>();
  private final List<IWeaponStats> weapons = new ArrayList<IWeaponStats>();

  public EquipmentAdditionalModel(ICharacterModelContext context) {
    IBasicCharacterData basicCharacterContext = context.getBasicCharacterContext();
    armours.add(new NaturalSoak(
        context.getTraitCollection().getTrait(AttributeType.Stamina),
        basicCharacterContext.getCharacterType()));
    if (basicCharacterContext.getRuleSet().getEdition() == ExaltedEdition.SecondEdition) {
      weapons.add(new Punch());
      weapons.add(new Kick());
      weapons.add(new Clinch());
    }
  }

  public IArmourStats[] getPrintArmours() {
    return armours.toArray(new IArmourStats[armours.size()]);
  }

  public IWeaponStats[] getPrintWeapons() {
    return weapons.toArray(new IWeaponStats[weapons.size()]);
  }

  public IEquipmentObject[] getAvailableObjects() {
    return new IEquipmentObject[0];
  }
}
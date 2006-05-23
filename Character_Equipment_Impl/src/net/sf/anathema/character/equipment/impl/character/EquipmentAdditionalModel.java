package net.sf.anathema.character.equipment.impl.character;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.impl.character.natural.Clinch;
import net.sf.anathema.character.equipment.impl.character.natural.Kick;
import net.sf.anathema.character.equipment.impl.character.natural.NaturalSoak;
import net.sf.anathema.character.equipment.impl.character.natural.Punch;
import net.sf.anathema.character.generic.equipment.weapon.IArmour;
import net.sf.anathema.character.generic.equipment.weapon.IWeapon;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.traits.types.AttributeType;

public class EquipmentAdditionalModel extends AbstractEquipmentAdditionalModel {

  private final List<IArmour> armours = new ArrayList<IArmour>();
  private final List<IWeapon> weapons = new ArrayList<IWeapon>();

  public EquipmentAdditionalModel(ICharacterModelContext context) {
    armours.add(new NaturalSoak(
        context.getTraitCollection().getTrait(AttributeType.Stamina),
        context.getBasicCharacterContext().getCharacterType()));
    weapons.add(new Punch());
    weapons.add(new Kick());
    weapons.add(new Clinch());
  }

  public IArmour[] getPrintArmours() {
    return armours.toArray(new IArmour[armours.size()]);
  }

  public IWeapon[] getPrintWeapons() {
    return weapons.toArray(new IWeapon[weapons.size()]);
  }
}
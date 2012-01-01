package net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionSpeedWeaponStatsGroup extends AbstractSpeedWeaponStatsGroup {

  private final IGenericTraitCollection collection;

  public FirstEditionSpeedWeaponStatsGroup(IResources resources, IGenericTraitCollection collection, IEquipmentModifiers equipment) {
    super(resources, equipment);
    this.collection = collection;
  }

  @Override
  protected int getSpeedValue(IWeaponStats weapon, IEquipmentModifiers equipment) {
    return collection.getTrait(AttributeType.Dexterity).getCurrentValue()
        + collection.getTrait(AttributeType.Wits).getCurrentValue()
        + weapon.getSpeed()+
    	(weapon.isRangedCombat() ? equipment.getRangedSpeedMod() : equipment.getMeleeSpeedMod());
  }
}

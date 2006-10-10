package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class MeleeWeaponStats extends AbstractWeaponStats {

  public MeleeWeaponStats(ICollectionFactory collectionFactory) {
    super(collectionFactory);
  }

  @Override
  protected AbilityType getCombatTrait() {
    return hasTag(WeaponTag.Natural) ? AbilityType.MartialArts : AbilityType.Melee;
  }
}
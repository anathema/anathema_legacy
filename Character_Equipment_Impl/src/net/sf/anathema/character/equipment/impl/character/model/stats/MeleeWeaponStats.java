package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class MeleeWeaponStats extends AbstractWeaponStats {

  public MeleeWeaponStats(ICollectionFactory collectionFactory) {
    super(collectionFactory);
  }

  public MeleeWeaponStats(ICollectionFactory collectionFactory, MeleeWeaponStats stats) {
    super(collectionFactory, stats);
  }

  @Override
  protected AbilityType getCombatTrait() {
    return hasTag(WeaponTag.Natural) || hasTag(WeaponTag.ClinchEnhancer) ? AbilityType.MartialArts : AbilityType.Melee;
  }
}
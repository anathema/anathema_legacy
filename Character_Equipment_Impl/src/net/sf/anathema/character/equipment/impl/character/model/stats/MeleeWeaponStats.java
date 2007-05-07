package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
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
    return isMartialArtsOnlyWeapon() ? AbilityType.MartialArts : AbilityType.Melee;
  }

  private boolean isMartialArtsOnlyWeapon() {
    return hasTag(WeaponTag.Natural) || hasTag(WeaponTag.ClinchEnhancer);
  }

  @Override
  public IEquipmentStats[] getViews() {
    if ((!hasTag(WeaponTag.MartialArts)) || isMartialArtsOnlyWeapon()) {
      return new IEquipmentStats[] { this };
    }
    IEquipmentStats[] views = new IEquipmentStats[2];
    views[0] = new MeleeWeaponStatsView(this, AbilityType.Melee);
    views[1] = new MeleeWeaponStatsView(this, AbilityType.MartialArts);
    return views;
  }
}
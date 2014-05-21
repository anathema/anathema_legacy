package net.sf.anathema.hero.equipment.sheet.content;

import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IWeaponStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.EquipmentNameStatsGroup;
import net.sf.anathema.hero.equipment.sheet.content.stats.IEquipmentStatsGroup;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapons.AbstractDefenceWeaponStatsGroup;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapons.AbstractSpeedWeaponStatsGroup;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapons.AccuracyWeaponStatsGroup;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapons.DamageWeaponStatsGroup;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapons.RangeWeaponStatsGroup;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapons.RateWeaponStatsGroup;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapons.TagsStatsGroup;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.framework.environment.Resources;

public abstract class AbstractWeaponryContent extends AbstractEquipmentContent<IWeaponStats> {

  public AbstractWeaponryContent(Hero hero, Resources resources) {
    super(hero, resources);
  }

  @Override
  public IWeaponStats[] getPrintStats() {
    return getEquipmentModel().getPrintWeapons(getResources());
  }

  @SuppressWarnings("unchecked")
  @Override
  public IEquipmentStatsGroup<IWeaponStats>[] createStatsGroups() {
    return new IEquipmentStatsGroup[]{createNameGroup(), createSpeedGroup(), createAccuracyGroup(), createDamageGroup(), createDefenceGroup(),
            createRateGroup(), createRangeWeaponGroup(), createTagsGroup()};
  }

  private EquipmentNameStatsGroup<IWeaponStats> createNameGroup() {
    return new EquipmentNameStatsGroup<>(getResources());
  }

  private DamageWeaponStatsGroup createDamageGroup() {
    return new DamageWeaponStatsGroup(getResources(), getOverallTraits());
  }

  private RangeWeaponStatsGroup createRangeWeaponGroup() {
    return new RangeWeaponStatsGroup(getResources());
  }

  private TagsStatsGroup createTagsGroup() {
    return new TagsStatsGroup(getResources());
  }

  protected abstract RateWeaponStatsGroup createRateGroup();

  protected abstract AccuracyWeaponStatsGroup createAccuracyGroup();

  protected abstract AbstractDefenceWeaponStatsGroup createDefenceGroup();

  protected abstract AbstractSpeedWeaponStatsGroup createSpeedGroup();

  protected TraitMap getOverallTraits() {
    return TraitModelFetcher.fetch(getHero());
  }
}

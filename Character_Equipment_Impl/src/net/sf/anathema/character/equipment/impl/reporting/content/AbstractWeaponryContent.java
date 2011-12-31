package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.character.equipment.impl.reporting.content.stats.EquipmentNameStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.IEquipmentStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.AbstractDefenceWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.AbstractSpeedWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.AccuracyWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.DamageWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.RangeWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.RateWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons.TagsStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractWeaponryContent extends AbstractEquipmentContent<IWeaponStats> {

  public AbstractWeaponryContent(IResources resources, IGenericCharacter character) {
    super(resources, character);
  }

  @Override
  public IWeaponStats[] getPrintStats() {
    return getEquipmentModel().getPrintWeapons(getResources());
  }

  @Override
  public IEquipmentStatsGroup<IWeaponStats>[] createStatsGroups() {
    return new IEquipmentStatsGroup[] { createNameGroup(), createSpeedGroup(), createAccuracyGroup(), createDamageGroup(), createDefenceGroup(),
      createRateGroup(), createRangeWeaponGroup(), createTagsGroup() };
  }

  private EquipmentNameStatsGroup<IWeaponStats> createNameGroup() {
    return new EquipmentNameStatsGroup<IWeaponStats>(getResources());
  }

  private DamageWeaponStatsGroup createDamageGroup() {
    return new DamageWeaponStatsGroup(getResources(), getTraitCollection(), getEquipmentModifiers());
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

  protected IGenericTraitCollection getTraitCollection() {
    return getCharacter().getTraitCollection();
  }
}

package net.sf.anathema.character.equipment.impl.reporting;

import net.sf.anathema.character.equipment.impl.reporting.second.AbstractEquipmentTableEncoder;
import net.sf.anathema.character.equipment.impl.reporting.second.stats.EquipmentNameStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.stats.IEquipmentStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.weaponstats.DamageWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.weaponstats.RangeWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.weaponstats.TagsStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public abstract class AbstractWeaponryTableEncoder extends AbstractEquipmentTableEncoder<IWeaponStats> {
  private final IResources resources;

  public AbstractWeaponryTableEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont);
    this.resources = resources;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IEquipmentStatsGroup<IWeaponStats>[] createStatsGroups(IGenericCharacter character) {
    IGenericTraitCollection traitCollection = character.getTraitCollection();
    return new IEquipmentStatsGroup[] {
        new EquipmentNameStatsGroup<IWeaponStats>(resources),
        createSpeedGroup(traitCollection),
        createAccuracyGroup(character),
        new DamageWeaponStatsGroup(resources, traitCollection),
        createDefenceGroup(character),
        createRateGroup(character),
        new RangeWeaponStatsGroup(resources),
        new TagsStatsGroup(resources) };
  }

  protected abstract RateWeaponStatsGroup createRateGroup(IGenericCharacter character);

  protected abstract AccuracyWeaponStatsGroup createAccuracyGroup(IGenericCharacter character);

  protected abstract AbstractDefenceWeaponStatsGroup createDefenceGroup(IGenericCharacter character);

  protected abstract AbstractSpeedWeaponStatsGroup createSpeedGroup(IGenericTraitCollection traitCollection);

  protected IResources getResources() {
    return resources;
  }

  @Override
  protected int getLineCount() {
    return 20;
  }

  @Override
  protected IWeaponStats[] getPrintStats(IGenericCharacter character) {
    return getEquipmentModel(character).getPrintWeapons(getResources());
  }
}
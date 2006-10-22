package net.sf.anathema.character.equipment.impl.reporting;

import net.sf.anathema.character.equipment.impl.reporting.second.AbstractEquipmentTableEncoder;
import net.sf.anathema.character.equipment.impl.reporting.second.stats.EquipmentNameStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.stats.IEquipmentStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.weaponstats.DamageWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.weaponstats.RangeWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.weaponstats.RateWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.weaponstats.TagsStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.traits.IGenericTrait;
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
        new EquipmentNameStatsGroup(resources),
        getSpeedWeaponStatsGroup(),
        getAccuracyWeaponStatsGroup(character),
        new DamageWeaponStatsGroup(resources, traitCollection),
        getDefenceWeaponStatsGroup(character),
        new RateWeaponStatsGroup(resources),
        new RangeWeaponStatsGroup(resources),
        new TagsStatsGroup(resources) };
  }

  protected AccuracyWeaponStatsGroup getAccuracyWeaponStatsGroup(IGenericCharacter character) {
    return new AccuracyWeaponStatsGroup(resources, character.getTraitCollection());
  }

  protected abstract AbstractDefenceWeaponStatsGroup getDefenceWeaponStatsGroup(IGenericCharacter character);

  protected abstract AbstractSpeedWeaponStatsGroup getSpeedWeaponStatsGroup();

  protected IResources getResources() {
    return resources;
  }

  @Override
  protected int getLineCount() {
    return 20;
  }

  @Override
  protected IWeaponStats[] getPrintStats(IGenericCharacter character) {
    return getEquipmentModel(character).getPrintWeapons();
  }

  @Override
  protected IGenericTrait getTrait(IGenericTraitCollection collection, IWeaponStats equipment) {
    return collection.getTrait(equipment.getTraitType());
  }
}
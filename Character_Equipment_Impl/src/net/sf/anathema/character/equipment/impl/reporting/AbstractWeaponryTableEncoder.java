package net.sf.anathema.character.equipment.impl.reporting;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.equipment.impl.reporting.stats.EquipmentNameStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.stats.IEquipmentStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.stats.weapons.*;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractWeaponryTableEncoder extends AbstractEquipmentTableEncoder<IWeaponStats> {
  private final IResources resources;
  protected final IEquipmentModifiers equipment;

  public AbstractWeaponryTableEncoder(BaseFont baseFont, IResources resources, IEquipmentModifiers equipment) {
    super(baseFont);
    this.resources = resources;
    this.equipment = equipment;
  }
  
  protected IGenericTraitCollection getTraitCollection(IGenericCharacter character)
  {
	  return character.getTraitCollection();
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IEquipmentStatsGroup<IWeaponStats>[] createStatsGroups(IGenericCharacter character) {
    IGenericTraitCollection traitCollection = getTraitCollection(character);
    return new IEquipmentStatsGroup[] {
        new EquipmentNameStatsGroup<IWeaponStats>(resources),
        createSpeedGroup(traitCollection),
        createAccuracyGroup(character, traitCollection),
        new DamageWeaponStatsGroup(resources, traitCollection, equipment),
        createDefenceGroup(character, traitCollection),
        createRateGroup(character),
        new RangeWeaponStatsGroup(resources),
        new TagsStatsGroup(resources) };
  }

  protected abstract RateWeaponStatsGroup createRateGroup(IGenericCharacter character);

  protected abstract AccuracyWeaponStatsGroup createAccuracyGroup(IGenericCharacter character, IGenericTraitCollection traitCollection);

  protected abstract AbstractDefenceWeaponStatsGroup createDefenceGroup(IGenericCharacter character, IGenericTraitCollection traitCollection);

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

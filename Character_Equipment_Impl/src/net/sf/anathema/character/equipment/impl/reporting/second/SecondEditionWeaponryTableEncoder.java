package net.sf.anathema.character.equipment.impl.reporting.second;

import net.sf.anathema.character.equipment.impl.reporting.second.stats.EquipmentNameStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.stats.IEquipmentStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.weaponstats.AccuracyWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.weaponstats.DamageWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.weaponstats.DefenceWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.weaponstats.RangeWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.weaponstats.RateWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.weaponstats.SpeedWeaponStatsGroup;
import net.sf.anathema.character.equipment.impl.reporting.second.weaponstats.TagsStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.weapon.IWeapon;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class SecondEditionWeaponryTableEncoder extends AbstractEquipmentTableEncoder<IWeapon> {

  private final IResources resources;

  public SecondEditionWeaponryTableEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont);
    this.resources = resources;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IEquipmentStatsGroup<IWeapon>[] createEquipmentGroups() {
    return new IEquipmentStatsGroup[] {
        new EquipmentNameStatsGroup(resources),
        new SpeedWeaponStatsGroup(resources),
        new AccuracyWeaponStatsGroup(resources),
        new DamageWeaponStatsGroup(resources),
        new DefenceWeaponStatsGroup(resources),
        new RateWeaponStatsGroup(resources),
        new RangeWeaponStatsGroup(resources),
        new TagsStatsGroup(resources) };
  }

  @Override
  protected int getLineCount() {
    return 8;
  }

  @Override
  protected IWeapon[] getPrintEquipments(IGenericCharacter character) {
    return getEquipmentModel(character).getPrintWeapons();
  }

  @Override
  protected IGenericTrait getTrait(IGenericCharacter character, IWeapon equipment) {
    return character.getTrait(equipment.getTraitType());
  }
}
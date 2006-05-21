package net.sf.anathema.character.reporting.sheet.second.equipment;

import net.sf.anathema.character.reporting.sheet.second.equipment.stats.EquipmentNameStatsGroup;
import net.sf.anathema.character.reporting.sheet.second.equipment.stats.IEquipmentStatsGroup;
import net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats.AccuracyWeaponStatsGroup;
import net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats.DamageWeaponStatsGroup;
import net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats.DefenceWeaponStatsGroup;
import net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats.RangeWeaponStatsGroup;
import net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats.RateWeaponStatsGroup;
import net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats.SpeedWeaopnStatsGroup;
import net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats.TagsStatsGroup;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class SecondEditionWeaponryTableEncoder extends AbstractEquipmentTableEncoder {

  private final IResources resources;

  public SecondEditionWeaponryTableEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont);
    this.resources = resources;
  }

  @Override
  protected IEquipmentStatsGroup[] createEquipmentGroups() {
    return new IEquipmentStatsGroup[] {
        new EquipmentNameStatsGroup(resources),
        new SpeedWeaopnStatsGroup(resources),
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
}
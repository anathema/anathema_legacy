package net.sf.anathema.character.equipment.impl.reporting.stats.weapons;

import net.sf.anathema.character.equipment.impl.reporting.stats.weapons.RateWeaponStatsGroup;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class CoreRulesRateWeaponStatsGroup extends RateWeaponStatsGroup {

  public CoreRulesRateWeaponStatsGroup(IResources resources, IEquipmentModifiers equipment) {
    super(resources, equipment);
  }

  @Override
  public void addContent(PdfPTable table, Font font, IWeaponStats weapon) {
    if (weapon == null || weapon.isRangedCombat()) {
      super.addContent(table, font, weapon);
      return;
    }
    table.addCell(createFinalValueCell(font, (Integer) null));
  }
}
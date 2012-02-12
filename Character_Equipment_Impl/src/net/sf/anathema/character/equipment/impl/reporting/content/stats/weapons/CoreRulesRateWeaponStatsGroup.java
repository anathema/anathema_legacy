package net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.IResources;

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

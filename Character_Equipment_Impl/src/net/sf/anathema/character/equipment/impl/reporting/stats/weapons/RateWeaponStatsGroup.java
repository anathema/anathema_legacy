package net.sf.anathema.character.equipment.impl.reporting.stats.weapons;

import net.sf.anathema.character.equipment.impl.reporting.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class RateWeaponStatsGroup extends AbstractValueEquipmentStatsGroup<IWeaponStats> {

  private final IEquipmentModifiers equipment;
  
  public RateWeaponStatsGroup(IResources resources, IEquipmentModifiers equipment) {
    super(resources, "Rate"); //$NON-NLS-1$
    this.equipment = equipment;
  }

  public int getColumnCount() {
    return 1;
  }

  public void addContent(PdfPTable table, Font font, IWeaponStats weapon) {
    if (weapon == null) {
      table.addCell(createFinalValueCell(font));
    }
    else {
      table.addCell(createFinalValueCell(font, getRate(weapon)));
    }
  }
  
  private Integer getRate(IWeaponStats weapon)
  {
	  Integer baseValue = weapon.getRate();
	  if (baseValue != null) {
	    baseValue += weapon.isRangedCombat() ? equipment.getRangedRateMod() : equipment.getMeleeRateMod();
	  }
	  return baseValue;
  }
}
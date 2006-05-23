package net.sf.anathema.character.equipment.impl.reporting.second.weaponstats;

import net.sf.anathema.character.equipment.impl.reporting.second.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.generic.equipment.weapon.IWeapon;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class DamageWeaponStatsGroup extends AbstractValueEquipmentStatsGroup<IWeapon> {

  public DamageWeaponStatsGroup(IResources resources) {
    super(resources, "Damage"); //$NON-NLS-1$
  }

  @Override
  public Float[] getColumnWeights() {
    Float[] columnWidth = super.getColumnWeights();
    columnWidth[getColumnCount() - 1] = new Float(0.7f);
    return columnWidth;
  }

  public int getColumnCount() {
    return 3;
  }

  public void addContent(PdfPTable table, Font font, IWeapon weapon, IGenericTrait... traits) {
    if (weapon == null) {
      table.addCell(createEmptyEquipmentValueCell(font));
      table.addCell(createFinalValueCell(font));
      table.addCell(createFinalValueCell(font));
    }
    else {
      final int weaponValue = weapon.getDamage();
      table.addCell(createEquipmentValueCell(font, weaponValue));
      table.addCell(createFinalValueCell(font, calculateFinalValue(weaponValue, traits)));
      table.addCell(createFinalValueCell(font, getDamageTypeLabel(weapon.getDamageType()), Element.ALIGN_CENTER));
    }
  }

  private String getDamageTypeLabel(HealthType damageType) {
    return getResources().getString("Weapons.Damage." + damageType.getId() + ".Short"); //$NON-NLS-1$ //$NON-NLS-2$
  }
}
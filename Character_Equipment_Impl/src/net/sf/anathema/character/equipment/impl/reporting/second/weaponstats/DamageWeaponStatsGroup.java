package net.sf.anathema.character.equipment.impl.reporting.second.weaponstats;

import net.sf.anathema.character.equipment.impl.reporting.second.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class DamageWeaponStatsGroup extends AbstractValueEquipmentStatsGroup<IWeaponStats> {

  private final IGenericCharacter character;

  public DamageWeaponStatsGroup(IResources resources, IGenericCharacter character) {
    super(resources, "Damage"); //$NON-NLS-1$
    this.character = character;
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

  public void addContent(PdfPTable table, Font font, IWeaponStats weapon) {
    if (weapon == null) {
      table.addCell(createEmptyValueCell(font));
      table.addCell(createFinalValueCell(font));
      table.addCell(createFinalValueCell(font));
    }
    else if (weapon.inflictsNoDamage()) {
      table.addCell(createEquipmentValueCell(font, (Integer) null));
      table.addCell(createFinalValueCell(font, (Integer) null));
      table.addCell(createFinalValueCell(font, (Integer) null));
    }
    else {
      final int weaponValue = weapon.getDamage();
      table.addCell(createEquipmentValueCell(font, weaponValue));
      int finalValue = weaponValue;
      if (weapon.getDamageTraitType() != null) {
        finalValue = calculateFinalValue(weaponValue, character.getTrait(weapon.getDamageTraitType()));
      }
      table.addCell(createFinalValueCell(font, finalValue));
      table.addCell(createFinalValueCell(font, getDamageTypeLabel(weapon.getDamageType()), Element.ALIGN_CENTER));
    }
  }

  private String getDamageTypeLabel(HealthType damageType) {
    return getResources().getString("Weapons.Damage." + damageType.getId() + ".Short"); //$NON-NLS-1$ //$NON-NLS-2$
  }
}
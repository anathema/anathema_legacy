package net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.main.equipment.weapon.IWeaponStats;
import net.sf.anathema.hero.health.HealthType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.lib.resources.Resources;

public class DamageWeaponStatsGroup extends AbstractValueEquipmentStatsGroup<IWeaponStats> {

  private final TraitMap traitMap;

  public DamageWeaponStatsGroup(Resources resources, TraitMap traitMap) {
    super(resources, "Damage");
    this.traitMap = traitMap;
  }

  @Override
  public Float[] getColumnWeights() {
    Float[] columnWidth = super.getColumnWeights();
    columnWidth[getColumnCount() - 1] = 0.7f;
    return columnWidth;
  }

  @Override
  public int getColumnCount() {
    return 3;
  }

  @Override
  public void addContent(PdfPTable table, Font font, IWeaponStats weapon) {
    if (weapon == null) {
      table.addCell(createEmptyValueCell(font));
      table.addCell(createFinalValueCell(font));
      table.addCell(createFinalValueCell(font));
    } else if (weapon.inflictsNoDamage()) {
      table.addCell(createEquipmentValueCell(font, null));
      table.addCell(createFinalValueCell(font, (Integer) null));
      table.addCell(createFinalValueCell(font, (Integer) null));
    } else {
      int weaponValue = weapon.getDamage();
      int finalValue = weaponValue;
      TraitType damageTraitType = weapon.getDamageTraitType();
      if (damageTraitType != null) {
        finalValue = calculateFinalValue(weaponValue, traitMap.getTrait(damageTraitType));
        table.addCell(createEquipmentValueCell(font, weaponValue));
      } else {
        table.addCell(createEquipmentValueCell(font, null));
      }
      table.addCell(createFinalValueCell(font, finalValue + (weapon.getMinimumDamage() > 1 ? "/" + weapon.getMinimumDamage() : "")));
      table.addCell(createFinalValueCell(font, getDamageTypeLabel(weapon.getDamageType()), Element.ALIGN_CENTER));
    }
  }

  private String getDamageTypeLabel(HealthType damageType) {
    return getResources().getString("HealthType." + damageType.getId() + ".Short");
  }
}

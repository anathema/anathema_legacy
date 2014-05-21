package net.sf.anathema.hero.equipment.sheet.content.stats.weapons;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.framework.library.HeroStatsModifiers;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IWeaponStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.framework.environment.Resources;

public abstract class AbstractSpeedWeaponStatsGroup extends AbstractValueEquipmentStatsGroup<IWeaponStats> {

  private final HeroStatsModifiers equipment;

  public AbstractSpeedWeaponStatsGroup(Resources resources, HeroStatsModifiers equipment) {
    super(resources, "Speed");
    this.equipment = equipment;
  }

  @Override
  public int getColumnCount() {
    return 1;
  }

  @Override
  public void addContent(PdfPTable table, Font font, IWeaponStats weapon) {
    if (weapon == null) {
      table.addCell(createFinalValueCell(font));
    } else {
      table.addCell(createFinalValueCell(font, getSpeedValue(weapon, equipment)));
    }
  }

  protected abstract int getSpeedValue(IWeaponStats weapon, HeroStatsModifiers equipment);
}

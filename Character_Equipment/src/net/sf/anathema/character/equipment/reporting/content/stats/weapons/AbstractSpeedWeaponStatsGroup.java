package net.sf.anathema.character.equipment.reporting.content.stats.weapons;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.equipment.reporting.content.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.main.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.main.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.Resources;

public abstract class AbstractSpeedWeaponStatsGroup extends AbstractValueEquipmentStatsGroup<IWeaponStats> {

  private final ICharacterStatsModifiers equipment;

  public AbstractSpeedWeaponStatsGroup(Resources resources, ICharacterStatsModifiers equipment) {
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

  protected abstract int getSpeedValue(IWeaponStats weapon, ICharacterStatsModifiers equipment);
}

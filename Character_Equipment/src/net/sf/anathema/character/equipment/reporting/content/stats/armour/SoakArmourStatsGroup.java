package net.sf.anathema.character.equipment.reporting.content.stats.armour;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.equipment.reporting.content.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.main.equipment.weapon.IArmourStats;
import net.sf.anathema.hero.health.HealthType;
import net.sf.anathema.lib.resources.Resources;

public class SoakArmourStatsGroup extends AbstractValueEquipmentStatsGroup<IArmourStats> implements IArmourStatsGroup {

  private String valuePrefix = "";

  public SoakArmourStatsGroup(Resources resources) {
    super(resources, "Soak");
  }

  @Override
  public void addContent(PdfPTable table, Font font, IArmourStats armour) {
    if (armour == null) {
      table.addCell(createEmptyValueCell(font));
      table.addCell(createEmptyValueCell(font));
      table.addCell(createEmptyValueCell(font));
    } else {
      table.addCell(createEquipmentValueCell(font, armour.getSoak(HealthType.Bashing)));
      table.addCell(createEquipmentValueCell(font, armour.getSoak(HealthType.Lethal)));
      table.addCell(createEquipmentValueCell(font, armour.getSoak(HealthType.Aggravated)));
    }
    valuePrefix = "+";
  }

  @Override
  public void addTotal(PdfPTable table, Font font, IArmourStats armour) {
    table.addCell(createFinalValueCell(font, armour.getSoak(HealthType.Bashing)));
    table.addCell(createFinalValueCell(font, armour.getSoak(HealthType.Lethal)));
    table.addCell(createFinalValueCell(font, armour.getSoak(HealthType.Aggravated)));
  }

  @Override
  public int getColumnCount() {
    return 3;
  }

  @Override
  protected String getPositivePrefix() {
    return valuePrefix;
  }

  @Override
  protected String getZeroPrefix() {
    return valuePrefix;
  }
}

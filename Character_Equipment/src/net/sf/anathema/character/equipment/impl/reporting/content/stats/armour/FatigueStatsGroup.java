package net.sf.anathema.character.equipment.impl.reporting.content.stats.armour;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.lib.resources.Resources;

public class FatigueStatsGroup extends AbstractValueEquipmentStatsGroup<IArmourStats> implements IArmourStatsGroup {

  public FatigueStatsGroup(Resources resources) {
    super(resources, "Fatigue");
  }

  @Override
  public int getColumnCount() {
    return 1;
  }

  @Override
  public void addContent(PdfPTable table, Font font, IArmourStats stats) {
    if (stats == null) {
      table.addCell(createEmptyValueCell(font));
    } else {
      table.addCell(createEquipmentValueCell(font, stats.getFatigue()));
    }
  }

  @Override
  public void addTotal(PdfPTable table, Font font, IArmourStats totalArmour) {
    table.addCell(createFinalValueCell(font, totalArmour.getFatigue()));
  }

  @Override
  protected String getPositivePrefix() {
    return "";
  }

  @Override
  protected String getZeroPrefix() {
    return getPositivePrefix();
  }
}

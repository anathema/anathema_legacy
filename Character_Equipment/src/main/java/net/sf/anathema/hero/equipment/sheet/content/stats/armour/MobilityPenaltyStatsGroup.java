package net.sf.anathema.hero.equipment.sheet.content.stats.armour;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IArmourStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.lib.resources.Resources;

public class MobilityPenaltyStatsGroup extends AbstractValueEquipmentStatsGroup<IArmourStats> implements IArmourStatsGroup {

  public MobilityPenaltyStatsGroup(Resources resources) {
    super(resources, "MobilityPenalty");
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
      table.addCell(createEquipmentValueCell(font, stats.getMobilityPenalty()));
    }
  }

  @Override
  public void addTotal(PdfPTable table, Font font, IArmourStats armour) {
    table.addCell(createFinalValueCell(font, armour.getMobilityPenalty()));
  }

  @Override
  protected String getZeroPrefix() {
    return "-";
  }
}

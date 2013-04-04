package net.sf.anathema.character.equipment.impl.reporting.content.stats.armour;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IDefensiveStats;
import net.sf.anathema.lib.resources.Resources;

public class MobilityPenaltyStatsGroup extends AbstractValueEquipmentStatsGroup<IDefensiveStats> implements
        IArmourStatsGroup {

  public MobilityPenaltyStatsGroup(Resources resources) {
    super(resources, "MobilityPenalty"); //$NON-NLS-1$
  }

  @Override
  public int getColumnCount() {
    return 1;
  }

  @Override
  public void addContent(PdfPTable table, Font font, IDefensiveStats stats) {
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
    return "-"; //$NON-NLS-1$
  }
}

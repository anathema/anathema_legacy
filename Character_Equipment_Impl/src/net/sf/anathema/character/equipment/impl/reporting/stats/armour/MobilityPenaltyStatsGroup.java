package net.sf.anathema.character.equipment.impl.reporting.stats.armour;

import net.sf.anathema.character.equipment.impl.reporting.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IDefensiveStats;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class MobilityPenaltyStatsGroup extends AbstractValueEquipmentStatsGroup<IDefensiveStats> implements
    IArmourStatsGroup {

  public MobilityPenaltyStatsGroup(IResources resources) {
    super(resources, "MobilityPenalty"); //$NON-NLS-1$
  }

  public int getColumnCount() {
    return 1;
  }

  public void addContent(PdfPTable table, Font font, IDefensiveStats stats) {
    if (stats == null) {
      table.addCell(createEmptyValueCell(font));
    }
    else {
      table.addCell(createEquipmentValueCell(font, stats.getMobilityPenalty()));
    }
  }

  public void addTotal(PdfPTable table, Font font, IArmourStats armour) {
    table.addCell(createFinalValueCell(font, armour.getMobilityPenalty()));
  }

  @Override
  protected String getZeroPrefix() {
    return "-"; //$NON-NLS-1$
  }
}
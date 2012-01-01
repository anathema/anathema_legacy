package net.sf.anathema.character.equipment.impl.reporting.content.stats.armour;

import net.sf.anathema.character.equipment.impl.reporting.content.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IDefensiveStats;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class FatigueStatsGroup extends AbstractValueEquipmentStatsGroup<IDefensiveStats> implements IArmourStatsGroup {

  public FatigueStatsGroup(IResources resources) {
    super(resources, "Fatigue"); //$NON-NLS-1$
  }

  public int getColumnCount() {
    return 1;
  }

  public void addContent(PdfPTable table, Font font, IDefensiveStats stats) {
    if (stats == null) {
      table.addCell(createEmptyValueCell(font));
    }
    else {
      table.addCell(createEquipmentValueCell(font, stats.getFatigue()));
    }
  }

  public void addTotal(PdfPTable table, Font font, IArmourStats totalArmour) {
    table.addCell(createFinalValueCell(font, totalArmour.getFatigue()));
  }

  @Override
  protected String getPositivePrefix() {
    return ""; //$NON-NLS-1$
  }

  @Override
  protected String getZeroPrefix() {
    return getPositivePrefix();
  }
}

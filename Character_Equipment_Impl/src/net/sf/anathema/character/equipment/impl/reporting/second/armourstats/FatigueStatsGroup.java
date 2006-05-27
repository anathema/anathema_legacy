package net.sf.anathema.character.equipment.impl.reporting.second.armourstats;

import net.sf.anathema.character.equipment.impl.reporting.second.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.generic.equipment.weapon.IArmour;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class FatigueStatsGroup extends AbstractValueEquipmentStatsGroup<IArmour> implements IArmourStatsGroup {

  public FatigueStatsGroup(IResources resources) {
    super(resources, "Fatigue"); //$NON-NLS-1$
  }

  public int getColumnCount() {
    return 1;
  }

  public void addContent(PdfPTable table, Font font, IArmour armour) {
    if (armour == null) {
      table.addCell(createEmptyValueCell(font));
    }
    else {
      table.addCell(createEquipmentValueCell(font, armour.getFatigue()));
    }
  }

  public void addTotal(PdfPTable table, Font font, IArmour totalArmour) {
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
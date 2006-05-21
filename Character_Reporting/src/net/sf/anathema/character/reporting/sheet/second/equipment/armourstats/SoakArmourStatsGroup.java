package net.sf.anathema.character.reporting.sheet.second.equipment.armourstats;

import net.sf.anathema.character.generic.equipment.weapon.IArmour;
import net.sf.anathema.character.reporting.sheet.second.equipment.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class SoakArmourStatsGroup extends AbstractValueEquipmentStatsGroup<IArmour> {

  public SoakArmourStatsGroup(IResources resources) {
    super(resources, "Soak"); //$NON-NLS-1$
  }

  public void addContent(PdfPTable table, Font font, IArmour armour) {
    table.addCell(createEquipmentValueCell(font));
    table.addCell(createEquipmentValueCell(font));
    table.addCell(createEquipmentValueCell(font));
  }

  public int getColumnCount() {
    return 3;
  }
}
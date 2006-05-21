package net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats;

import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class DefenceWeaponStatsGroup extends AbstractValueWeaponStatsGroup {

  public DefenceWeaponStatsGroup(IResources resources) {
    super(resources, "Defence"); //$NON-NLS-1$
  }

  public int getColumnCount() {
    return 2;
  }

  public void addContent(PdfPTable table, Font font) {
    table.addCell(createWeaponValueCell(font));
    table.addCell(createFinalValueCell(font));
  }
}
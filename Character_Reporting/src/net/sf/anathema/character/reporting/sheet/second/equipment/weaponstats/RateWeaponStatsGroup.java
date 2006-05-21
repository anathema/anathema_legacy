package net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats;

import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class RateWeaponStatsGroup extends AbstractValueWeaponStatsGroup {

  public RateWeaponStatsGroup(IResources resources) {
    super(resources, "Rate"); //$NON-NLS-1$
  }

  public int getColumnCount() {
    return 1;
  }

  public void addContent(PdfPTable table, Font font) {
    table.addCell(createFinalValueCell(font));
  }
}
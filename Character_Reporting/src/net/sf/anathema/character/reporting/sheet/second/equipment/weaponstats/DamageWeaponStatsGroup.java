package net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats;

import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class DamageWeaponStatsGroup extends AbstractValueWeaponStatsGroup {

  public DamageWeaponStatsGroup(IResources resources) {
    super(resources, "Damage"); //$NON-NLS-1$
  }

  @Override
  public Float[] getColumnWeights() {
    Float[] columnWidth = super.getColumnWeights();
    columnWidth[getColumnCount() - 1] = new Float(0.7f);
    return columnWidth;
  }
  
  public int getColumnCount() {
    return 3;
  }
  
  public void addContent(PdfPTable table, Font font) {
    table.addCell(createWeaponValueCell(font));
    table.addCell(createFinalValueCell(font));
    table.addCell(createFinalValueCell(font));
  }
}
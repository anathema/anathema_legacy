package net.sf.anathema.character.equipment.impl.reporting.second.shieldstats;

import net.sf.anathema.character.equipment.impl.reporting.second.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.character.reporting.sheet.util.TableEncodingUtilities;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class RangedCombatShieldStatsGroup extends AbstractValueEquipmentStatsGroup<IShieldStats> {

  public RangedCombatShieldStatsGroup(IResources resources) {
    super(resources, "RangedCombatShield"); //$NON-NLS-1$
  }

  public int getColumnCount() {
    return 2;
  }

  public void addContent(PdfPTable table, Font font, IShieldStats shield) {
    PdfPCell cell = TableEncodingUtilities.createContentCellTable(
        null,
        "Ranged",
        font,
        0f,
        Rectangle.NO_BORDER,
        Element.ALIGN_LEFT);
    table.addCell(cell);
    if (shield == null) {
      table.addCell(createFinalValueCell(font));
    }
    else {
      table.addCell(createFinalValueCell(font, shield.getRangedCombatBonus()));
    }
  }

  @Override
  protected String getZeroPrefix() {
    return ""; //$NON-NLS-1$
  }

  @Override
  protected String getPositivePrefix() {
    return ""; //$NON-NLS-1$
  }
}
package net.sf.anathema.character.equipment.impl.reporting.content.stats.shields;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.lib.resources.IResources;

public class RangedCombatShieldStatsGroup extends AbstractValueEquipmentStatsGroup<IShieldStats> {

  public RangedCombatShieldStatsGroup(IResources resources) {
    super(resources, "RangedCombatShield"); //$NON-NLS-1$
  }

  public int getColumnCount() {
    return 2;
  }

  public void addContent(PdfPTable table, Font font, IShieldStats shield) {
    PdfPCell cell = TableEncodingUtilities.createContentCellTable(null, getResources().getString("Sheet.Equipment.Header.RangeBonus"), //$NON-NLS-1$
            font, 0f, Rectangle.NO_BORDER, Element.ALIGN_LEFT);
    table.addCell(cell);
    if (shield == null) {
      table.addCell(createFinalValueCell(font));
    } else {
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

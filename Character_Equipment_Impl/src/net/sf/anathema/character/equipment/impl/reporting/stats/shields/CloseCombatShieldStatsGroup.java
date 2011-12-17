package net.sf.anathema.character.equipment.impl.reporting.stats.shields;

import net.sf.anathema.character.equipment.impl.reporting.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class CloseCombatShieldStatsGroup extends AbstractValueEquipmentStatsGroup<IShieldStats> {

  private final String valuePrefix = ""; //$NON-NLS-1$

  public CloseCombatShieldStatsGroup(IResources resources) {
    super(resources, "CloseCombatShield"); //$NON-NLS-1$
  }

  public void addContent(PdfPTable table, Font font, IShieldStats shield) {
    PdfPCell cell = TableEncodingUtilities.createContentCellTable(null, getResources().getString("Sheet.Equipment.Header.CloseCombatBonus"), font,
                                                                  0f, Rectangle.NO_BORDER, Element.ALIGN_LEFT); //$NON-NLS-1$
    cell.setColspan(2);
    table.addCell(cell);
    if (shield == null) {
      table.addCell(createFinalValueCell(font));
    }
    else {
      table.addCell(createFinalValueCell(font, shield.getCloseCombatBonus()));
    }
  }

  public int getColumnCount() {
    return 3;
  }

  @Override
  protected String getPositivePrefix() {
    return "+"; //$NON-NLS-1$
  }

  @Override
  protected String getZeroPrefix() {
    return valuePrefix;
  }
}

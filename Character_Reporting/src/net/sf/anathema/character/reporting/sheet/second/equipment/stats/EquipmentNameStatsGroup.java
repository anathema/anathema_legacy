package net.sf.anathema.character.reporting.sheet.second.equipment.stats;

import java.awt.Color;

import net.sf.anathema.character.reporting.sheet.second.equipment.WeaponEncodingUtilities;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public final class EquipmentNameStatsGroup implements IEquipmentStatsGroup {
  private final String title;

  public EquipmentNameStatsGroup(IResources resources) {
    this.title =resources.getString("Sheet.Equipment.Header.Name"); //$NON-NLS-1$ ;
  }

  public int getColumnCount() {
    return 1;
  }

  public String getTitle() {
    return title;
  }

  public Float[] getColumnWeights() {
    return new Float[] { new Float(6) };
  }

  public void addContent(PdfPTable table, Font font) {
    table.addCell(createEmptyNameCell(font));
  }

  private PdfPCell createEmptyNameCell(Font font) {
    return WeaponEncodingUtilities.createContentCellTable(Color.BLACK, " ", font, 0.5f, Rectangle.BOTTOM); //$NON-NLS-1$
  }
}
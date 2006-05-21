package net.sf.anathema.character.reporting.sheet.second.equipment.stats;

import java.awt.Color;

import net.sf.anathema.character.generic.equipment.weapon.IEquipment;
import net.sf.anathema.character.reporting.sheet.second.equipment.WeaponEncodingUtilities;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;

public abstract class AbstractValueEquipmentStatsGroup<T extends IEquipment> implements IEquipmentStatsGroup<T> {

  private final String title;

  public AbstractValueEquipmentStatsGroup(IResources resources, String resourceKey) {
    this.title = resources.getString("Sheet.Equipment.Header." + resourceKey); //$NON-NLS-1$
  }

  public Float[] getColumnWeights() {
    return WeaponEncodingUtilities.createStandardColumnWeights(getColumnCount());
  }

  public final String getTitle() {
    return title;
  }

  protected final PdfPCell createFinalValueCell(Font font) {
    return createContentCellTable(Color.BLACK, " ", font, 0.75f, true); //$NON-NLS-1$
  }

  protected final PdfPCell createEmptyEquipmentValueCell(Font font) {
    return createContentCellTable(Color.GRAY, " ", font, 0.5f, true); //$NON-NLS-1$
  }

  protected final PdfPCell createEquipmentValueCell(Font font, Integer value) {
    String text = value == null ? " " : value.toString(); //$NON-NLS-1$
    return createContentCellTable(Color.GRAY, text, font, 0.5f, value != null);
  }

  private final PdfPCell createContentCellTable(
      Color borderColor,
      String text,
      Font font,
      float borderWidth,
      boolean enabled) {
    return WeaponEncodingUtilities.createContentCellTable(borderColor, text, font, borderWidth, Rectangle.BOX, enabled);
  }
}
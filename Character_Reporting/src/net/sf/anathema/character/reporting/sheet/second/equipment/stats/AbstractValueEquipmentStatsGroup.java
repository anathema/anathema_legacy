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
    return createContentCellTable(Color.BLACK, " ", font, 0.75f); //$NON-NLS-1$
  }

  protected final PdfPCell createEquipmentValueCell(Font font) {
    return createContentCellTable(Color.GRAY, " ", font, 0.5f); //$NON-NLS-1$
  }

  private final PdfPCell createContentCellTable(Color borderColor, String text, Font font, float borderWidth) {
    return WeaponEncodingUtilities.createContentCellTable(borderColor, text, font, borderWidth, Rectangle.BOX);
  }
}
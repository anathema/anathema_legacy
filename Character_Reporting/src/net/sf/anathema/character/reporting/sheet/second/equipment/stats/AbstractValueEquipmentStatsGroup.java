package net.sf.anathema.character.reporting.sheet.second.equipment.stats;

import java.awt.Color;

import net.sf.anathema.character.generic.equipment.weapon.IEquipment;
import net.sf.anathema.character.reporting.sheet.second.equipment.EquipmentEncodingUtilities;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;

public abstract class AbstractValueEquipmentStatsGroup<T extends IEquipment> implements IEquipmentStatsGroup<T> {

  private final String title;
  private final IResources resources;

  public AbstractValueEquipmentStatsGroup(IResources resources, String resourceKey) {
    this.resources = resources;
    this.title = resources.getString("Sheet.Equipment.Header." + resourceKey); //$NON-NLS-1$
  }

  public final IResources getResources() {
    return resources;
  }

  public Float[] getColumnWeights() {
    return EquipmentEncodingUtilities.createStandardColumnWeights(getColumnCount());
  }

  public final String getTitle() {
    return title;
  }

  protected final PdfPCell createFinalValueCell(Font font) {
    return createContentCellTable(Color.BLACK, " ", font, 0.75f, true); //$NON-NLS-1$
  }

  protected final PdfPCell createFinalValueCell(Font font, Integer value) {
    return createFinalValueCell(font, value != null ? value.toString() : null);
  }

  protected PdfPCell createFinalValueCell(Font font, String text) {
    String content = text != null ? text : " "; //$NON-NLS-1$
    return createContentCellTable(Color.BLACK, content, font, 0.75f, text != null);
  }

  protected PdfPCell createFinalValueCell(Font font, String text, int alignment) {
    String content = text != null ? text : " "; //$NON-NLS-1$
    return createContentCellTable(Color.BLACK, content, font, 0.75f, alignment, text != null);
  }

  protected final PdfPCell createEmptyEquipmentValueCell(Font font) {
    return createContentCellTable(Color.GRAY, " ", font, 0.5f, true); //$NON-NLS-1$
  }

  protected final PdfPCell createEquipmentValueCell(Font font, Integer value) {
    String text = getEquipmentValueString(value);
    return createContentCellTable(Color.GRAY, text, font, 0.5f, value != null);
  }

  private String getEquipmentValueString(Integer value) {
    if (value == null) {
      return " "; //$NON-NLS-1$
    }
    StringBuilder stringBuilder = new StringBuilder(value.toString());
    if (value == 0) {
      stringBuilder.insert(0, getZeroPrefix());
    }
    if (value > 0) {
      stringBuilder.insert(0, getPositivePrefix());
    }
    return stringBuilder.toString();
  }

  protected String getPositivePrefix() {
    return "+"; //$NON-NLS-1$
  }

  protected String getZeroPrefix() {
    return "+"; //$NON-NLS-1$
  }

  private final PdfPCell createContentCellTable(
      Color borderColor,
      String text,
      Font font,
      float borderWidth,
      boolean enabled) {
    return EquipmentEncodingUtilities.createContentCellTable(
        borderColor,
        text,
        font,
        borderWidth,
        Rectangle.BOX,
        Element.ALIGN_RIGHT,
        enabled);
  }

  private final PdfPCell createContentCellTable(
      Color borderColor,
      String text,
      Font font,
      float borderWidth,
      int alignment,
      boolean enabled) {
    return EquipmentEncodingUtilities.createContentCellTable(
        borderColor,
        text,
        font,
        borderWidth,
        Rectangle.BOX,
        alignment,
        enabled);
  }
}
package net.sf.anathema.character.reporting.pdf.content.stats;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.util.IStats;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.lib.resources.Resources;

public abstract class AbstractValueStatsGroup<T extends IStats> implements IStatsGroup<T> {

  private final String title;
  private final Resources resources;

  public AbstractValueStatsGroup(Resources resources, String resourceKey) {
    this.resources = resources;
    this.title = resources.getString(getHeaderResourceBase() + resourceKey);
  }

  protected abstract String getHeaderResourceBase();

  public final Resources getResources() {
    return resources;
  }

  @Override
  public Float[] getColumnWeights() {
    return TableEncodingUtilities.createStandardColumnWeights(getColumnCount());
  }

  @Override
  public final String getTitle() {
    return title;
  }

  protected final PdfPCell createFinalValueCell(Font font) {
    return createContentCellTable(BaseColor.BLACK, " ", font, 0.75f, true);
  }

  protected final PdfPCell createFinalValueCell(Font font, Integer value) {
    return createFinalValueCell(font, value != null ? value.toString() : null);
  }

  protected final PdfPCell createFinalValueCell(Font font, String text) {
    String content = text != null ? text : " ";
    return createContentCellTable(BaseColor.BLACK, content, font, 0.75f, text != null);
  }

  protected final PdfPCell createFinalValueCell(Font font, String text, int alignment) {
    String content = text != null ? text : " ";
    return createContentCellTable(BaseColor.BLACK, content, font, 0.75f, alignment, text != null);
  }

  protected final PdfPCell createEmptyValueCell(Font font) {
    return createContentCellTable(BaseColor.GRAY, " ", font, 0.5f, true);
  }

  protected final PdfPCell createEquipmentValueCell(Font font, Integer value) {
    String text = getStatsValueString(value);
    return createContentCellTable(BaseColor.GRAY, text, font, 0.5f, value != null);
  }

  private String getStatsValueString(Integer value) {
    if (value == null) {
      return " ";
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
    return "+";
  }

  protected String getZeroPrefix() {
    return "+";
  }

  private PdfPCell createContentCellTable(BaseColor borderColor, String text, Font font, float borderWidth, boolean enabled) {
    return TableEncodingUtilities.createContentCellTable(borderColor, text, font, borderWidth, Rectangle.BOX, Element.ALIGN_RIGHT, enabled);
  }

  private PdfPCell createContentCellTable(BaseColor borderColor, String text, Font font, float borderWidth, int alignment, boolean enabled) {
    return TableEncodingUtilities.createContentCellTable(borderColor, text, font, borderWidth, Rectangle.BOX, alignment, enabled);
  }

  protected final int calculateFinalValue(int weaponValue, ValuedTraitType... traits) {
    int totalValue = weaponValue;
    for (ValuedTraitType trait : traits) {
      totalValue += trait.getCurrentValue();
    }
    return totalValue;
  }
}

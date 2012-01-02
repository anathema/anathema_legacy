package net.sf.anathema.character.reporting.pdf.rendering.boxes.anima;

import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.stats.anima.AnimaTableRangeProvider;
import net.sf.anathema.character.reporting.pdf.content.stats.anima.AnimaTableStealthProvider;
import net.sf.anathema.character.reporting.pdf.content.stats.anima.AnimaUtils;
import net.sf.anathema.character.reporting.pdf.content.stats.anima.ColumnDescriptor;
import net.sf.anathema.character.reporting.pdf.content.stats.anima.IAnimaTableRangeProvider;
import net.sf.anathema.character.reporting.pdf.content.stats.anima.IAnimaTableStealthProvider;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.AbstractTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

import java.awt.Color;

public class AnimaTableEncoder extends AbstractTableEncoder<ReportContent> {

  public final static float TABLE_HEIGHT = 58f;

  private final IResources resources;
  private final float fontSize;
  private final IAnimaTableRangeProvider rangeProvider;
  private final IAnimaTableStealthProvider stealthProvider;

  public AnimaTableEncoder(IResources resources, float fontSize) {
    this(resources, fontSize, new AnimaTableRangeProvider());
  }

  public AnimaTableEncoder(IResources resources, float fontSize, IAnimaTableRangeProvider rangeProvider) {
    this.resources = resources;
    this.fontSize = fontSize;
    this.rangeProvider = rangeProvider;
    this.stealthProvider = new AnimaTableStealthProvider(resources);
  }

  public AnimaTableEncoder(IResources resources, float fontSize, IAnimaTableStealthProvider stealthProvider) {
    this.resources = resources;
    this.fontSize = fontSize;
    this.rangeProvider = new AnimaTableRangeProvider();
    this.stealthProvider = stealthProvider;
  }

  @Override
  protected PdfPTable createTable(SheetGraphics graphics, ReportContent content, Bounds bounds) {
    ColumnDescriptor[] columns = getColumns();
    PdfPTable table = new PdfPTable(getColumnWidths(columns));
    table.setWidthPercentage(100);
    for (ColumnDescriptor column : columns) {
      table.addCell(createHeaderCell(graphics, getString(column.getHeaderKey())));
    }
    ICharacterType type = content.getCharacter().getTemplate().getTemplateType().getCharacterType();
    String descriptionPrefix = "Sheet.AnimaTable.Description." + type; //$NON-NLS-1$
    for (int index = 0; index < 5; index++) {
      addAnimaRow(graphics, table, index, content, descriptionPrefix);
    }
    return table;
  }

  protected void addAnimaRow(SheetGraphics graphics, PdfPTable table, int level, ReportContent content, String descriptionPrefix) {
    table.addCell(createRangeCell(graphics, level, content.getCharacter()));
    table.addCell(createDescriptionCell(graphics, level, descriptionPrefix));
    table.addCell(createStealthCell(graphics, level));
  }

  protected PdfPCell createStealthCell(SheetGraphics graphics, int level) {
    return createContentCell(graphics, stealthProvider.getStealth(level));
  }

  protected PdfPCell createDescriptionCell(SheetGraphics graphics, int level, String descriptionPrefix) {
    return createContentCell(graphics, getString(descriptionPrefix + "." + AnimaUtils.resourceIds[level])); //$NON-NLS-1$
  }

  protected PdfPCell createRangeCell(SheetGraphics graphics, int level, IGenericCharacter character) {
    return createContentCell(graphics, rangeProvider.getRange(level, character));
  }

  private float[] getColumnWidths(ColumnDescriptor[] columns) {
    float[] widths = new float[columns.length];
    for (int index = 0; index < widths.length; index++) {
      widths[index] = columns[index].getWidthPart();
    }
    return widths;
  }

  protected ColumnDescriptor[] getColumns() {
    return new ColumnDescriptor[] { new ColumnDescriptor(0.15f, "Sheet.AnimaTable.Header.Motes"), //$NON-NLS-1$
      new ColumnDescriptor(0.6f, "Sheet.AnimaTable.Header.BannerFlare"), //$NON-NLS-1$
      new ColumnDescriptor(0.25f, "Sheet.AnimaTable.Header.Stealth") }; //$NON-NLS-1$
  }

  protected final PdfPCell createContentCell(SheetGraphics graphics, String text) {
    PdfPCell cell = new PdfPCell(new Phrase(text, createFont(graphics)));
    configureCell(cell);
    return cell;
  }

  protected void configureCell(PdfPCell cell) {
    cell.setPaddingTop(1);
    cell.setPaddingBottom(2);
  }

  private PdfPCell createHeaderCell(SheetGraphics graphics, String text) {
    PdfPCell cell = new PdfPCell(new Phrase(text, createHeaderFont(graphics)));
    cell.setBorder(Rectangle.BOTTOM);
    return cell;
  }

  protected String getString(String key) {
    return resources.getString(key);
  }

  private Font createFont(SheetGraphics graphics) {
    return new Font(graphics.getBaseFont(), this.fontSize, Font.NORMAL, Color.BLACK);
  }

  private Font createHeaderFont(SheetGraphics graphics) {
    return new Font(graphics.getBaseFont(), this.fontSize, Font.ITALIC, Color.BLACK);
  }
}

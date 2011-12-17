package net.sf.anathema.character.reporting.extended.common.anima;

import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.common.encoder.AbstractTableEncoder;
import net.sf.anathema.character.reporting.common.stats.anima.AnimaTableRangeProvider;
import net.sf.anathema.character.reporting.common.stats.anima.AnimaTableStealthProvider;
import net.sf.anathema.character.reporting.common.stats.anima.AnimaUtils;
import net.sf.anathema.character.reporting.common.stats.anima.ColumnDescriptor;
import net.sf.anathema.character.reporting.common.stats.anima.IAnimaTableRangeProvider;
import net.sf.anathema.character.reporting.common.stats.anima.IAnimaTableStealthProvider;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.lib.resources.IResources;

import java.awt.*;

public class AnimaTableEncoder extends AbstractTableEncoder {
  
  public final static float TABLE_HEIGHT = 58f;

  private final IResources resources;
  private final Font headerFont;
  private final Font font;
  private final IAnimaTableRangeProvider rangeProvider;
  private final IAnimaTableStealthProvider stealthProvider;

  public AnimaTableEncoder(IResources resources, BaseFont baseFont, float fontSize) {
    this(resources, baseFont, fontSize, new AnimaTableRangeProvider());
  }

  public AnimaTableEncoder(
      IResources resources,
      BaseFont baseFont,
      float fontSize,
      IAnimaTableRangeProvider rangeProvider) {
    this.resources = resources;
    this.headerFont = new Font(baseFont, fontSize, Font.ITALIC, Color.BLACK);
    this.font = new Font(baseFont, fontSize, Font.NORMAL, Color.BLACK);
    this.rangeProvider = rangeProvider;
    this.stealthProvider = new AnimaTableStealthProvider(resources);
  }

  public AnimaTableEncoder(
      IResources resources,
      BaseFont baseFont,
      float fontSize,
      IAnimaTableStealthProvider stealthProvider) {
    this.resources = resources;
    this.headerFont = new Font(baseFont, fontSize, Font.ITALIC, Color.BLACK);
    this.font = new Font(baseFont, fontSize, Font.NORMAL, Color.BLACK);
    this.rangeProvider = new AnimaTableRangeProvider();
    this.stealthProvider = stealthProvider;
  }

  @Override
  protected PdfPTable createTable(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) {
    ColumnDescriptor[] columns = getColumns();
    PdfPTable table = new PdfPTable(getColumWidths(columns));
    table.setWidthPercentage(100);
    for (ColumnDescriptor column : columns) {
      table.addCell(createHeaderCell(getString(column.getHeaderKey())));
    }
    ICharacterType type = character.getTemplate().getTemplateType().getCharacterType();
    String descriptionPrefix = "Sheet.AnimaTable.Description." + type; //$NON-NLS-1$
    for (int index = 0; index < 5; index++) {
      addAnimaRow(table, index, character, descriptionPrefix);
    }
    return table;
  }

  protected void addAnimaRow(PdfPTable table, int level, IGenericCharacter character, String descriptionPrefix) {
    table.addCell(createRangeCell(level, character));
    table.addCell(createDescriptionCell(level, descriptionPrefix));
    table.addCell(createStealthCell(level));
  }

  protected PdfPCell createStealthCell(int level) {
    return createContentCell(stealthProvider.getStealth(level));
  }

  protected PdfPCell createDescriptionCell(int level, String descriptionPrefix) {
    return createContentCell(getString(descriptionPrefix + "." + AnimaUtils.resourceIds[level])); //$NON-NLS-1$
  }

  protected PdfPCell createRangeCell(int level, IGenericCharacter character) {
    return createContentCell(rangeProvider.getRange(level, character));
  }

  private float[] getColumWidths(ColumnDescriptor[] columns) {
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

  protected final PdfPCell createContentCell(String text) {
    PdfPCell cell = new PdfPCell(new Phrase(text, font));
    configureCell(cell);
    return cell;
  }

  protected void configureCell(PdfPCell cell) {
    cell.setPaddingTop(1);
    cell.setPaddingBottom(2);
  }

  private PdfPCell createHeaderCell(String text) {
    PdfPCell cell = new PdfPCell(new Phrase(text, headerFont));
    cell.setBorder(Rectangle.BOTTOM);
    return cell;
  }

  protected String getString(String key) {
    return resources.getString(key);
  }

  protected Font getFont() {
    return font;
  }
}

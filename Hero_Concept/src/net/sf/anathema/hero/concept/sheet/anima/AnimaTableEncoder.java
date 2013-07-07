package net.sf.anathema.hero.concept.sheet.anima;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.stats.anima.AnimaTableRangeProvider;
import net.sf.anathema.character.reporting.pdf.content.stats.anima.AnimaTableStealthProvider;
import net.sf.anathema.character.reporting.pdf.content.stats.anima.AnimaUtils;
import net.sf.anathema.character.reporting.pdf.content.stats.anima.ColumnDescriptor;
import net.sf.anathema.character.reporting.pdf.content.stats.anima.IAnimaTableRangeProvider;
import net.sf.anathema.character.reporting.pdf.content.stats.anima.IAnimaTableStealthProvider;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.AbstractTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

public class AnimaTableEncoder extends AbstractTableEncoder<ReportSession> {

  public final static float TABLE_HEIGHT = 58f;

  private final Resources resources;
  private final float fontSize;
  private final IAnimaTableRangeProvider rangeProvider;
  private final IAnimaTableStealthProvider stealthProvider;

  public AnimaTableEncoder(Resources resources, float fontSize) {
    this(resources, fontSize, new AnimaTableRangeProvider());
  }

  public AnimaTableEncoder(Resources resources, float fontSize, IAnimaTableRangeProvider rangeProvider) {
    this.resources = resources;
    this.fontSize = fontSize;
    this.rangeProvider = rangeProvider;
    this.stealthProvider = new AnimaTableStealthProvider(resources);
  }

  @Override
  protected PdfPTable createTable(SheetGraphics graphics, ReportSession session, Bounds bounds) {
    ColumnDescriptor[] columns = getColumns();
    PdfPTable table = new PdfPTable(getColumnWidths(columns));
    table.setWidthPercentage(100);
    for (ColumnDescriptor column : columns) {
      table.addCell(createHeaderCell(graphics, getString(column.getHeaderKey())));
    }
    ICharacterType type = session.getHero().getTemplate().getTemplateType().getCharacterType();
    String descriptionPrefix = "Sheet.AnimaTable.Description." + type.getId();
    for (int index = 0; index < 5; index++) {
      addAnimaRow(graphics, table, index, session, descriptionPrefix);
    }
    return table;
  }

  protected void addAnimaRow(SheetGraphics graphics, PdfPTable table, int level, ReportSession session, String descriptionPrefix) {
    table.addCell(createRangeCell(graphics, level, session.getHero()));
    table.addCell(createDescriptionCell(graphics, level, descriptionPrefix));
    table.addCell(createStealthCell(graphics, level));
  }

  protected PdfPCell createStealthCell(SheetGraphics graphics, int level) {
    return createContentCell(graphics, stealthProvider.getStealth(level));
  }

  protected PdfPCell createDescriptionCell(SheetGraphics graphics, int level, String descriptionPrefix) {
    return createContentCell(graphics, getString(descriptionPrefix + "." + AnimaUtils.resourceIds[level]));
  }

  protected PdfPCell createRangeCell(SheetGraphics graphics, int level, Hero hero) {
    return createContentCell(graphics, rangeProvider.getRange(level, hero));
  }

  private float[] getColumnWidths(ColumnDescriptor[] columns) {
    float[] widths = new float[columns.length];
    for (int index = 0; index < widths.length; index++) {
      widths[index] = columns[index].getWidthPart();
    }
    return widths;
  }

  protected ColumnDescriptor[] getColumns() {
    return new ColumnDescriptor[]{new ColumnDescriptor(0.15f, "Sheet.AnimaTable.Header.Motes"),
            new ColumnDescriptor(0.6f, "Sheet.AnimaTable.Header.BannerFlare"),
            new ColumnDescriptor(0.25f, "Sheet.AnimaTable.Header.Stealth")};
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
    return new Font(graphics.getBaseFont(), this.fontSize, Font.NORMAL, BaseColor.BLACK);
  }

  private Font createHeaderFont(SheetGraphics graphics) {
    return new Font(graphics.getBaseFont(), this.fontSize, Font.ITALIC, BaseColor.BLACK);
  }
}

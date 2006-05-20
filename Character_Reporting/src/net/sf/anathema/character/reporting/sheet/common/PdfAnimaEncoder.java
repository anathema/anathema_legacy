package net.sf.anathema.character.reporting.sheet.common;

import java.awt.Color;
import java.io.IOException;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class PdfAnimaEncoder extends AbstractPdfEncoder implements IPdfContentEncoder {

  private static final int FONT_SIZE = IVoidStateFormatConstants.FONT_SIZE - 1;
  private static final float LINE_HEIGHT = FONT_SIZE * 1.5f;
  private static final String SYMBOL = "\u00A8  "; //$NON-NLS-1$
  private final BaseFont baseFont;
  private final IResources resources;

  public PdfAnimaEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds contentBounds)
      throws DocumentException,
      IOException {
    Position cursorPosition = encodeAnimaPowers(directContent, character, contentBounds);
    setFillColorBlack(directContent);
    directContent.setLineWidth(0);
    float startX = contentBounds.getMinX() + cursorPosition.x;
    float endX = contentBounds.getMaxX();
    float yPosition = cursorPosition.y;
    directContent.moveTo(startX, yPosition);
    directContent.lineTo(endX, yPosition);
    yPosition -= LINE_HEIGHT;
    startX = (int) contentBounds.getMinX();
    directContent.moveTo(startX, yPosition);
    directContent.lineTo(endX, yPosition);
    yPosition -= LINE_HEIGHT;
    directContent.moveTo(startX, yPosition);
    directContent.lineTo(endX, yPosition);
    directContent.stroke();

    ColumnText tableColumn = new ColumnText(directContent);
    PdfPTable table = createTable();
    tableColumn.setSimpleColumn(
        contentBounds.getMinX(),
        contentBounds.getMinY(),
        contentBounds.getMaxX(),
        contentBounds.getCenterY(),
        LINE_HEIGHT,
        PdfContentByte.ALIGN_LEFT);
    tableColumn.addElement(table);
    tableColumn.go();
  }

  private Position encodeAnimaPowers(PdfContentByte directContent, IGenericCharacter character, Bounds contentBounds)
      throws DocumentException,
      IOException {
    BaseFont symbolBaseFont = BaseFont.createFont(BaseFont.SYMBOL, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
    Font symbolFont = new Font(symbolBaseFont, FONT_SIZE, Font.NORMAL, Color.BLACK);
    Font font = new Font(getBaseFont(), FONT_SIZE, Font.NORMAL, Color.BLACK);
    Phrase powerPhrase = new Phrase("", font); //$NON-NLS-1$
    addAnimaPowerText(character.getTemplate().getTemplateType().getCharacterType(), powerPhrase, symbolFont);
    ColumnText columnText = new ColumnText(directContent);
    columnText.setSimpleColumn(
        powerPhrase,
        contentBounds.getMinX(),
        contentBounds.getCenterX(),
        contentBounds.getMaxX(),
        contentBounds.getMaxY(),
        LINE_HEIGHT,
        PdfContentByte.ALIGN_LEFT);
    columnText.go();
    float xPosition = symbolBaseFont.getWidthPoint(SYMBOL, FONT_SIZE);
    return new Position(xPosition, columnText.getYLine());
  }

  private PdfPTable createTable() {
    PdfPTable table = new PdfPTable(new float[] { 0.15f, 0.6f, 0.25f });
    Font font = new Font(getBaseFont(), FONT_SIZE, Font.NORMAL, Color.BLACK);
    table.setWidthPercentage(100);
    table.addCell(createHeaderCell(new Phrase(resources.getString("Sheet.AnimaTable.Header.Motes"), font))); //$NON-NLS-1$
    table.addCell(createHeaderCell(new Phrase(resources.getString("Sheet.AnimaTable.Header.BannerFlare"), font))); //$NON-NLS-1$
    table.addCell(createHeaderCell(new Phrase(resources.getString("Sheet.AnimaTable.Header.Stealth"), font))); //$NON-NLS-1$

    table.addCell(createContentCell(new Phrase("1-3", font))); //$NON-NLS-1$
    table.addCell(createContentCell(new Phrase(resources.getString("Sheet.AnimaTable.CasteMarkGlitters"), font))); //$NON-NLS-1$
    table.addCell(createContentCell(new Phrase(resources.getString("Sheet.AnimaTable.StealthNormal"), font))); //$NON-NLS-1$

    table.addCell(createContentCell(new Phrase("4-7", font))); //$NON-NLS-1$
    table.addCell(createContentCell(new Phrase(resources.getString("Sheet.AnimaTable.CasteMarkBurns"), font))); //$NON-NLS-1$
    table.addCell(createContentCell(new Phrase("+2", font))); //$NON-NLS-1$

    table.addCell(createContentCell(new Phrase("8-10", font))); //$NON-NLS-1$
    table.addCell(createContentCell(new Phrase(resources.getString("Sheet.AnimaTable.CoruscantAura"), font))); //$NON-NLS-1$
    String stealthImpossible = resources.getString("Sheet.AnimaTable.StealthImpossible"); //$NON-NLS-1$
    table.addCell(createContentCell(new Phrase(stealthImpossible, font)));

    table.addCell(createContentCell(new Phrase("11-15", font))); //$NON-NLS-1$
    table.addCell(createContentCell(new Phrase(resources.getString("Sheet.AnimaTable.BrilliantBonfire"), font))); //$NON-NLS-1$
    table.addCell(createContentCell(new Phrase(stealthImpossible, font)));

    table.addCell(createContentCell(new Phrase("16+", font))); //$NON-NLS-1$
    table.addCell(createContentCell(new Phrase(resources.getString("Sheet.AnimaTable.TotemicAura"), font))); //$NON-NLS-1$
    table.addCell(createContentCell(new Phrase(stealthImpossible, font)));
    return table;
  }

  private PdfPCell createContentCell(Phrase phrase) {
    PdfPCell cell = new PdfPCell(phrase);
    cell.setPaddingTop(1);
    cell.setPaddingBottom(1.5f);
    return cell;
  }

  private PdfPCell createHeaderCell(Phrase phrase) {
    PdfPCell cell = new PdfPCell(phrase);
    cell.setBorder(Rectangle.BOTTOM);
    return cell;
  }

  private void addAnimaPowerText(CharacterType characterType, Phrase phrase, Font symbolFont) {
    Chunk symbolChunk = new Chunk(SYMBOL, symbolFont);
    String resourceBase = "Sheet.AnimaPower." + characterType.getId() + "."; //$NON-NLS-1$ //$NON-NLS-2$
    phrase.add(symbolChunk);
    phrase.add(resources.getString(resourceBase + "First") + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
    phrase.add(symbolChunk);
    phrase.add(resources.getString(resourceBase + "Second") + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
    phrase.add(symbolChunk);
    phrase.add(resources.getString(resourceBase + "Third") + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
    phrase.add(symbolChunk);
  }
}
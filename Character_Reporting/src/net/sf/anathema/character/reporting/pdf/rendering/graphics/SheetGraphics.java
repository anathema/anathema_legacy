package net.sf.anathema.character.reporting.pdf.rendering.graphics;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;

import java.awt.*;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.COMMENT_FONT_SIZE;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.FONT_SIZE;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.SUBSECTION_FONT_SIZE;

public class SheetGraphics implements ITextMetrics {

  private static final String SYMBOL = "\u2022 "; //$NON-NLS-1$
  private static final int SYMBOL_FONT_SIZE = FONT_SIZE - 1;
  private final PdfContentByte directContent;
  private final BaseFont baseFont;
  private BaseFont symbolBaseFont;

  public SheetGraphics(PdfContentByte directContent, BaseFont baseFont, BaseFont symbolBaseFont) {
    this.directContent = directContent;
    this.baseFont = baseFont;
    this.symbolBaseFont = symbolBaseFont;
  }

  @Deprecated
  public PdfContentByte getDirectContent() {
    return directContent;
  }

  public BaseFont getBaseFont() {
    return baseFont;
  }

  @Override
  public final float getDefaultTextWidth(String text) {
    return baseFont.getWidthPoint(text, FONT_SIZE);
  }

  @Override
  public final float getCommentTextWidth(String text) {
    return baseFont.getWidthPoint(text, COMMENT_FONT_SIZE);
  }

  public final void drawMissingTextLine(Position position, float length) {
    setFillColorBlack();
    directContent.setLineWidth(0);
    directContent.moveTo(position.x, position.y);
    directContent.lineTo(position.x + length, position.y);
    directContent.stroke();
  }

  public final void drawComment(String text, Position position, int alignment) {
    setFillColorBlack();
    setCommentFont();
    directContent.setLineWidth(0);
    drawText(text, position, alignment, 0);
  }

  public final void drawText(String text, Position position, int alignment) {
    addText(text, position, alignment, 0);
  }

  public final void drawVerticalText(String text, Position position, int alignment) {
    addText(text, position, alignment, 90);
  }

  public final void drawLabelledContent(String label, String content, Position position, float width) {
    initDirectContentForText();
    directContent.beginText();
    directContent.showTextAlignedKerned(PdfContentByte.ALIGN_LEFT, label, position.x, position.y, 0);
    float labelWidth = getDefaultTextWidth(label);
    float contentX = position.x + labelWidth + 2;
    if (StringUtilities.isNullOrTrimEmpty(content)) {
      directContent.endText();
      float lineWidth = position.x + width - contentX;
      drawMissingTextLine(new Position(contentX, position.y), lineWidth);
    }
    else {
      directContent.showTextAlignedKerned(PdfContentByte.ALIGN_LEFT, content, contentX, position.y, 0);
      directContent.endText();
    }
  }

  public void encodeTextWithReducedLineHeight(Bounds textBounds, Phrase phrase) throws DocumentException {
    encodeText(phrase, textBounds, IVoidStateFormatConstants.LINE_HEIGHT - 2f, Element.ALIGN_LEFT);
  }

  private void addText(String text, Position position, int alignment, int rotation) {
    initDirectContentForText();
    drawText(text, position, alignment, rotation);
  }

  private void drawText(String text, Position position, int alignment, int rotation) {
    directContent.beginText();
    directContent.showTextAlignedKerned(alignment, text, position.x, position.y, rotation);
    directContent.endText();
  }

  private void initDirectContentForText() {
    setFillColorBlack();
    setDefaultFont();
    directContent.setLineWidth(0);
  }

  public void initDirectContentForGraphics() {
    setDefaultFont();
    setFillColorBlack();
    directContent.setLineWidth(0.8f);
  }

  public GraphicsTemplate createTemplate(float width, float height) {
    return new GraphicsTemplate(directContent, baseFont, symbolBaseFont, width, height);
  }

  public final void setFillColorBlack() {
    directContent.setRGBColorFill(0, 0, 0);
  }

  private final void setCommentFont() {
    setFontSize(COMMENT_FONT_SIZE);
  }

  private final void setDefaultFont() {
    setFontSize(FONT_SIZE);
  }

  public final void setSubsectionFont() {
    setFontSize(SUBSECTION_FONT_SIZE);
  }

  private void setFontSize(int fontSize) {
    directContent.setFontAndSize(baseFont, fontSize);
  }

  public Font createBoldFont() {
    Font boldFont = new Font(baseFont);
    boldFont.setStyle(Font.BOLD);
    return boldFont;
  }

  public Font createTextFont() {
    return createFont(FONT_SIZE);
  }

  public Font createFont(float size) {
    return new Font(baseFont, size, Font.NORMAL, Color.black);
  }

  public Font createCommentFont() {
    return new Font(baseFont, IVoidStateFormatConstants.COMMENT_FONT_SIZE, Font.NORMAL, Color.BLACK);
  }

  public ColumnText encodeText(Phrase phrase, Bounds bounds, float lineHeight) throws DocumentException {
    return encodeText(phrase, bounds, lineHeight, Element.ALIGN_LEFT);
  }

  public ColumnText encodeText(Phrase phrase, Bounds bounds, float lineHeight, int alignment) throws DocumentException {
    ColumnText columnText = new ColumnText(directContent);
    float minX = bounds.getMinX();
    float minY = bounds.getMinY();
    float maxX = bounds.getMaxX();
    float maxY = bounds.getMaxY();
    columnText.setSimpleColumn(phrase, minX, minY, maxX, maxY, lineHeight, alignment);
    columnText.go();
    return columnText;
  }

  public ColumnText createColumn(Bounds bounds, float lineHeight, int alignment) {
    ColumnText columnText = new ColumnText(directContent);
    float minX = bounds.getMinX();
    float minY = bounds.getMinY();
    float maxX = bounds.getMaxX();
    float maxY = bounds.getMaxY();
    columnText.setSimpleColumn(minX, minY, maxX, maxY, lineHeight, alignment);
    return columnText;
  }

  public Chunk createSymbolChunk() {
    Font font = new Font(symbolBaseFont, SYMBOL_FONT_SIZE, Font.NORMAL, Color.BLACK);
    return new Chunk(SYMBOL, font);
  }

  public float getCaretSymbolWidth() {
    return symbolBaseFont.getWidthPoint(SYMBOL, SYMBOL_FONT_SIZE);
  }

  public Box createBox(Bounds bounds) {
    return new Box(bounds, directContent);
  }

  public Line createHorizontalLineByCoordinate(Position startPoint, float endX) {
    return new Line(directContent, startPoint, new Position(endX, startPoint.y));
  }

  public Line createHorizontalLineByLength(Position startPoint, float length) {
    return createHorizontalLineByCoordinate(startPoint, startPoint.x + length);
  }

  public Line createVerticalLineByLength(Position startPoint, float length) {
    return createVerticalLineByCoordinate(startPoint, startPoint.y + length);
  }

  public Line createVerticalLineByCoordinate(Position startPoint, float endY) {
    return new Line(directContent, startPoint, new Position(startPoint.x, endY));
  }
}

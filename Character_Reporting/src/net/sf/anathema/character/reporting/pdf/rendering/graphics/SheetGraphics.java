package net.sf.anathema.character.reporting.pdf.rendering.graphics;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.shape.Box;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.shape.Dot;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.shape.Line;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.shape.Square;

import java.awt.Color;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.COMMENT_FONT_SIZE;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.FONT_SIZE;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.SUBSECTION_FONT_SIZE;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.TABLE_FONT_SIZE;

public class SheetGraphics {

  public static SheetGraphics WithSymbolBaseFontInSpecialEncoding(PdfContentByte directContent) {
    BaseFont baseFont = createDefaultBaseFont();
    BaseFont symbolFont = createSymbolBaseFontInSpecialEncoding();
    return new SheetGraphics(directContent, baseFont, symbolFont);
  }

  public static SheetGraphics WithSymbolBaseFontInCodepage1252(PdfContentByte directContent) {
    BaseFont baseFont = createDefaultBaseFont();
    BaseFont symbolFont = createSymbolBaseFontWithCodepage1252();
    return new SheetGraphics(directContent, baseFont, symbolFont);
  }

  private static BaseFont createSymbolBaseFontInSpecialEncoding() {
    return createSymbolFont().getCalculatedBaseFont(true);
  }

  private static BaseFont createSymbolBaseFontWithCodepage1252() {
    return createSymbolFont().getCalculatedBaseFont(false);
  }

  private static BaseFont createDefaultBaseFont() {
    return new Font(Font.HELVETICA, 7, Font.NORMAL, Color.BLACK).getCalculatedBaseFont(true);
  }

  private static Font createSymbolFont() {
    return new Font(Font.SYMBOL, 7, Font.NORMAL, Color.BLACK);
  }

  private static final String SYMBOL = "\u00A8  "; //$NON-NLS-1$
  private static final int SYMBOL_FONT_SIZE = FONT_SIZE - 1;
  private final PdfContentByte directContent;

  private final BaseFont baseFont;

  private final BaseFont symbolBaseFont;

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

  public TextMetrics getTextMetrics() {
    return new SheetTextMetrics(getBaseFont());
  }

  public final void drawMissingTextLine(Position position, float length) {
    initDirectContentForEnabledText();
    directContent.moveTo(position.x, position.y);
    directContent.lineTo(position.x + length, position.y);
    directContent.stroke();
  }

  public final void drawComment(String text, Position position, int alignment) {
    initDirectContentForEnabledText();
    setCommentFont();
    writeConfiguredText(text, position, alignment, 0);
  }

  public final void drawDisabledText(String text, Position position, int alignment) {
    initDirectContentForDisableText();
    writeConfiguredText(text, position, alignment, 0);
  }

  public final void drawText(String text, Position position, int alignment) {
    initDirectContentForEnabledText();
    writeConfiguredText(text, position, alignment, 0);
  }

  public final void drawVerticalText(String text, Position position, int alignment) {
    initDirectContentForEnabledText();
    writeConfiguredText(text, position, alignment, 90);
  }

  private void writeConfiguredText(String text, Position position, int alignment, int rotation) {
    directContent.beginText();
    directContent.showTextAlignedKerned(alignment, text, position.x, position.y, rotation);
    directContent.endText();
  }

  public final void drawLabelledContent(String label, String content, Position position, float width) {
    initDirectContentForEnabledText();
    directContent.beginText();
    directContent.showTextAlignedKerned(PdfContentByte.ALIGN_LEFT, label, position.x, position.y, 0);
    float labelWidth = getTextMetrics().getDefaultTextWidth(label);
    float contentX = position.x + labelWidth + 2;
    if (StringUtilities.isNullOrTrimmedEmpty(content)) {
      directContent.endText();
      float lineWidth = position.x + width - contentX;
      drawMissingTextLine(new Position(contentX, position.y), lineWidth);
    } else {
      directContent.showTextAlignedKerned(PdfContentByte.ALIGN_LEFT, content, contentX, position.y, 0);
      directContent.endText();
    }
  }

  private void initDirectContentForEnabledText() {
    initDirectContentForText(Color.BLACK);
  }

  private void initDirectContentForDisableText() {
    initDirectContentForText(Color.LIGHT_GRAY);
  }

  private void initDirectContentForText(Color color) {
    directContent.setColorFill(color);
    setDefaultFont();
    directContent.setLineWidth(0);
  }

  public GraphicsTemplate createTemplate(float width, float height) {
    return new GraphicsTemplate(directContent, baseFont, symbolBaseFont, width, height);
  }

  public final void setFillColorBlack() {
    directContent.setColorFill(Color.BLACK);
  }

  private void setCommentFont() {
    setFontSize(COMMENT_FONT_SIZE);
  }

  private void setDefaultFont() {
    setFontSize(FONT_SIZE);
  }

  public final void setSubsectionFont() {
    setFontSize(SUBSECTION_FONT_SIZE);
  }

  private void setFontSize(int fontSize) {
    directContent.setFontAndSize(baseFont, fontSize);
  }

  public Font createBoldFont() {
    Font boldFont = createTextFont();
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
    return new Font(baseFont, COMMENT_FONT_SIZE, Font.NORMAL, Color.BLACK);
  }

  public Font createTableFont() {
    return new Font(baseFont, TABLE_FONT_SIZE, Font.NORMAL, Color.BLACK);
  }

  public SimpleColumnBuilder createSimpleColumn(Bounds bounds) {
    return new SimpleColumnBuilder(directContent, bounds);
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

  public Square createSquare(int size) {
    return new Square(directContent, size);
  }

  public Dot createDot(int size) {
    return new Dot(directContent, size);
  }
}

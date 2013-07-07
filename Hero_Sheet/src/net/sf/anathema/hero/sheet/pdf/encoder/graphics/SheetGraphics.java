package net.sf.anathema.hero.sheet.pdf.encoder.graphics;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Position;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.shape.Box;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.shape.Dot;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.shape.Line;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.shape.Square;
import net.sf.anathema.lib.lang.StringUtilities;

import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.COMMENT_FONT_SIZE;
import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.FONT_SIZE;
import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.SUBSECTION_FONT_SIZE;
import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.TABLE_FONT_SIZE;

public class SheetGraphics {

  public static SheetGraphics WithHelvetica(PdfContentByte directContent) {
    return new SheetGraphics(directContent, createDefaultBaseFont());
  }

  private static BaseFont createDefaultBaseFont() {
    return new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL, BaseColor.BLACK).getCalculatedBaseFont(true);
  }

  private static final String SYMBOL = "\u2022  ";
  private static final int SYMBOL_FONT_SIZE = FONT_SIZE - 1;
  private final PdfContentByte directContent;
  private final BaseFont baseFont;

  public SheetGraphics(PdfContentByte directContent, BaseFont baseFont) {
    this.directContent = directContent;
    this.baseFont = baseFont;
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
    initDirectContentForText(BaseColor.BLACK);
  }

  private void initDirectContentForDisableText() {
    initDirectContentForText(BaseColor.LIGHT_GRAY);
  }

  private void initDirectContentForText(BaseColor color) {
    directContent.setColorFill(color);
    setDefaultFont();
    directContent.setLineWidth(0);
  }

  public GraphicsTemplate createTemplate(float width, float height) {
    return new GraphicsTemplate(directContent, baseFont, width, height);
  }

  public final void setFillColorBlack() {
    directContent.setColorFill(BaseColor.BLACK);
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
    return new Font(baseFont, size, Font.NORMAL, BaseColor.BLACK);
  }

  public Font createCommentFont() {
    return new Font(baseFont, COMMENT_FONT_SIZE, Font.NORMAL, BaseColor.BLACK);
  }

  public Font createTableFont() {
    return new Font(baseFont, TABLE_FONT_SIZE, Font.NORMAL, BaseColor.BLACK);
  }

  public SimpleColumnBuilder createSimpleColumn(Bounds bounds) {
    return new SimpleColumnBuilder(directContent, bounds);
  }

  public Chunk createSymbolChunk() {
    Font font = new Font(baseFont, SYMBOL_FONT_SIZE, Font.NORMAL, BaseColor.BLACK);
    return new Chunk(SYMBOL, font);
  }

  public float getCaretSymbolWidth() {
    return baseFont.getWidthPoint(SYMBOL, SYMBOL_FONT_SIZE);
  }

  public Box createBox(Bounds bounds) {
    return new Box(bounds, directContent);
  }

  public Line createHorizontalLineByCoordinate(Position startPoint, float endX) {
    return new Line(directContent, startPoint, new Position(endX, startPoint.y));
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

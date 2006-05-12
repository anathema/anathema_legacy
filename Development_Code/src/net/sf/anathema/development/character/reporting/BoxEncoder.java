package net.sf.anathema.development.character.reporting;

import java.io.IOException;

import net.disy.commons.core.geometry.SmartRectangle;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class BoxEncoder {

  private static final int HEADER_FONT_PADDING = 2;
  private static final int HEADER_FONT_SIZE = 13;
  private static final int HEADER_HEIGHT = 14;
  private static final int ARCSPACE = 8;
  private BaseFont headerFont;

  public BoxEncoder() throws DocumentException, IOException {
    headerFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.NOT_EMBEDDED);
  }

  public void encodeBox(PdfContentByte directContent, SmartRectangle bounds, String title) {
    encodeContentBox(directContent, bounds);
    encodeHeaderBox(directContent, bounds, title);
  }

  private void encodeContentBox(PdfContentByte directContent, SmartRectangle bounds) {
    SmartRectangle contentBounds = calculateContentBounds(bounds);
    setFillColorBlack(directContent);
    directContent.setLineWidth(0);
    directContent.moveTo(contentBounds.x, contentBounds.y);
    directContent.lineTo(contentBounds.x + contentBounds.width, contentBounds.y);
    directContent.lineTo(contentBounds.x + contentBounds.width, contentBounds.y + contentBounds.height);
    directContent.lineTo(contentBounds.x, contentBounds.y + contentBounds.height);
    directContent.lineTo(contentBounds.x, contentBounds.y);
    directContent.stroke();
  }

  private SmartRectangle calculateContentBounds(SmartRectangle bounds) {
    int headerPadding = HEADER_HEIGHT / 2;
    return new SmartRectangle(bounds.x, bounds.y, bounds.width, bounds.height - headerPadding);
  }

  private void encodeHeaderBox(PdfContentByte directContent, SmartRectangle bounds, String title) {
    setFillColorBlack(directContent);
    SmartRectangle headerBounds = calculateHeaderBounds(bounds);
    directContent.rectangle(
        headerBounds.x + ARCSPACE,
        headerBounds.y,
        headerBounds.width - 2 * ARCSPACE,
        headerBounds.height);
    directContent.arc(headerBounds.x, headerBounds.y, headerBounds.x + 2 * ARCSPACE, headerBounds.y
        + headerBounds.height, 0, 360);
    directContent.arc(
        (float) headerBounds.getMaxX(),
        headerBounds.y,
        (float) headerBounds.getMaxX() - 2 * ARCSPACE,
        headerBounds.y + headerBounds.height,
        0,
        360);
    directContent.fillStroke();
    setFillColorWhite(directContent);
    directContent.setFontAndSize(headerFont, HEADER_FONT_SIZE);
    directContent.showTextAligned(PdfContentByte.ALIGN_CENTER, title, (int) headerBounds.getCenterX(), headerBounds.y
        + HEADER_FONT_PADDING, 0);
  }

  private void setFillColorWhite(PdfContentByte directContent) {
    directContent.setRGBColorFill(255, 255, 255);
  }

  private void setFillColorBlack(PdfContentByte directContent) {
    directContent.setRGBColorFill(0, 0, 0);
  }

  private SmartRectangle calculateHeaderBounds(SmartRectangle bounds) {
    return new SmartRectangle(bounds.x, bounds.y + bounds.height - HEADER_HEIGHT, bounds.width, HEADER_HEIGHT);
  }
}
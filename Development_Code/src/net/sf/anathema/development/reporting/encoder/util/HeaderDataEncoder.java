package net.sf.anathema.development.reporting.encoder.util;

import java.awt.Rectangle;

import net.sf.anathema.development.reporting.util.HeaderData;

import org.dom4j.Element;

public class HeaderDataEncoder {

  private static final int LABEL_WIDTH = 35;
  private static final int PADDING = 5;
  private final int LINE_HEIGHT;
  private final TextLineEncoder lineEncoder;

  public HeaderDataEncoder(TextLineEncoder lineEncoder, int lineHeight) {
    this.lineEncoder = lineEncoder;
    this.LINE_HEIGHT = lineHeight;
  }

  public void encode(Element bandElement, int x, int y, int width, int height, HeaderData[] headerData) {
    int lineCount = getLineCount(headerData);
    int textHeight = Math.max(lineCount, 3) * LINE_HEIGHT;
    y += (height - textHeight) / 2;

    int textWidth = ((width - PADDING) / 2) - LABEL_WIDTH;

    int xRight = x + textWidth + LABEL_WIDTH + PADDING;
    for (int line = 0; line < lineCount; line++) {
      addHeaderData(bandElement, headerData[line * 2], x, y, textWidth);
      if (headerData.length > (line * 2) + 1) {
        addHeaderData(bandElement, headerData[(line * 2) + 1], xRight, y, textWidth);
      }
      y += LINE_HEIGHT;
      if (lineCount < 3 && line == 0) {
        y += LINE_HEIGHT;
      }
    }
  }

  public int getLineCount(HeaderData[] headerData) {
    return (headerData.length + 1) / 2;
  }

  private final void addHeaderData(Element bandElement, HeaderData data, int x, int y, int textWidth) {
    if (data.getParameter() == null) {
      lineEncoder.addLabeledLine(bandElement, data.getLabel(), y, x, LABEL_WIDTH, textWidth);
    }
    else {
      lineEncoder.addLabeledTextParameter(
          bandElement,
          data.getLabel(),
          data.getParameter(),
          y,
          x,
          LABEL_WIDTH,
          textWidth,
          true);
    }
  }

  public void encode(Element bandElement, Rectangle bounds, HeaderData[] headerData) {
    encode(bandElement, bounds.x, bounds.y, bounds.width, bounds.height, headerData);
  }
}
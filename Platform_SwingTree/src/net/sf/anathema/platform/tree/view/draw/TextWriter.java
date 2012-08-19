package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.lib.lang.StringUtilities;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class TextWriter {

  private static final int TEXT_SIZE = 15;
  private static final int LINE_HEIGHT = TEXT_SIZE + 1;
  private final String text;
  private final Color textColor;
  private final Rectangle bounds;

  public TextWriter(Rectangle bounds, Color textColor, String text) {
    this.bounds = bounds;
    this.textColor = textColor;
    this.text = text;

  }

  public void write(Graphics2D graphics) {
    graphics.setColor(textColor);
    Font textFont = new Font("SansSerif", Font.PLAIN, TEXT_SIZE);
    graphics.setFont(textFont);
    FontMetrics textMetrics = graphics.getFontMetrics(textFont);
    String[] parts = breakText(textMetrics);
    for (int partIndex = 0; partIndex < parts.length; partIndex++) {
      String part = parts[partIndex];
      int centeredX = (int) (bounds.x + bounds.getWidth() / 2) - (textMetrics.stringWidth(part) / 2);
      int centeredY = (int) (bounds.y + bounds.getHeight() / 2) + (textMetrics.getHeight() / 2);
      int actualY = centeredY + +yCorrection(partIndex, parts.length);
      graphics.drawString(part, centeredX, actualY);
    }
  }

  private int yCorrection(int currentLine, int numberOfLines) {
    if (numberOfLines == 1) {
      return -4;
    }
    if (numberOfLines == 2) {
      return 4 + ((currentLine - 1) * LINE_HEIGHT);
    }
    return -4 + ((currentLine - 1) * LINE_HEIGHT);
  }

  private String[] breakText(FontMetrics textMetrics) {
    int lines = suggestNumberOfLines(textMetrics);
    String[] textNodes = new String[lines];
    List<Integer> wrap = new ArrayList<Integer>();
    wrap.add(0);
    for (int breakpoint : StringUtilities.findBreakPoints(text, lines)) {
      wrap.add(breakpoint);
    }
    wrap.add(text.length());
    for (int index = 0; index < lines; index++) {
      int startIndex = wrap.get(index);
      int runLength = wrap.get(index + 1);
      textNodes[index] = text.substring(startIndex, runLength).trim();
    }
    return textNodes;
  }

  private int suggestNumberOfLines(FontMetrics textMetrics) {
    float textLength = textMetrics.stringWidth(text);
    return Math.min(3, (int) Math.ceil(textLength / 95));
  }
}
package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.lib.lang.StringUtilities;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class TextWriter {

  private static final int TEXT_SIZE = 15;
  private static final int LINE_HEIGHT = TEXT_SIZE + 1;
  private String text = "";
  private RGBColor textColor = RGBColor.Black;
  private final Polygon polygon;
  private String[] parts;
  private LineSuggestion lineSuggestion = new LineSuggestion();

  public TextWriter(Polygon polygon, LineSuggestion lineSuggestion) {
    this(polygon);
    this.lineSuggestion = lineSuggestion;
  }

  public TextWriter(Polygon polygon) {
    this.polygon = polygon;
  }

  public void write(Canvas graphics) {
    graphics.setColor(textColor);
    Font textFont = new Font("SansSerif", Font.PLAIN, TEXT_SIZE);
    graphics.setFont(textFont);
    FontMetrics textMetrics = graphics.getFontMetrics(textFont);
    findBreaksIfNotAlreadyEstablished(textMetrics);
    Rectangle bounds = polygon.getBounds();
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

  private void findBreaksIfNotAlreadyEstablished(FontMetrics textMetrics) {
    if (parts == null) {
      parts = breakText(textMetrics);
    }
  }

  private String[] breakText(FontMetrics textMetrics) {
    int lines = lineSuggestion.suggestNumberOfLines(textMetrics, text);
    String[] textNodes = new String[lines];
    List<Integer> wrap = new ArrayList<>();
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

  public void setText(String text) {
    this.text = text;
  }

  public void setStroke(RGBColor stroke) {
    this.textColor = stroke;
  }
}
package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.Area;
import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.FontStyle;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.framework.ui.Rectangle;
import net.sf.anathema.lib.lang.StringUtilities;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;

import java.util.ArrayList;
import java.util.List;

public class TextWriter {

  private static final int TEXT_SIZE = 15;
  private static final int LINE_HEIGHT = TEXT_SIZE + 1;
  private String text = "";
  private RGBColor textColor = RGBColor.Black;
  private final AgnosticShape shape;
  private String[] parts;
  private LineSuggestion lineSuggestion = new LineSuggestion();

  public TextWriter(AgnosticShape shape, LineSuggestion lineSuggestion) {
    this(shape);
    this.lineSuggestion = lineSuggestion;
  }

  public TextWriter(AgnosticShape shape) {
    this.shape = shape;
  }

  public void write(Canvas graphics) {
    graphics.setColor(textColor);
    graphics.setFontStyle(FontStyle.Plain, TEXT_SIZE);
    Area textSize = graphics.measureText(text);
    findBreaksIfNotAlreadyEstablished(textSize);
    Rectangle bounds = graphics.calculateBounds(shape);
    for (int partIndex = 0; partIndex < parts.length; partIndex++) {
      String part = parts[partIndex];
      Area partSize = graphics.measureText(part);
      int centeredX = bounds.origin.x + bounds.area.width / 2 - (partSize.width / 2);
      int centeredY = bounds.origin.y + bounds.area.height / 2 + (partSize.height / 2);
      int actualY = centeredY + +yCorrection(partIndex, parts.length);
      graphics.drawString(part, new Coordinate(centeredX, actualY));
    }
  }

  private void findBreaksIfNotAlreadyEstablished(Area textSize) {
    if (parts == null) {
      int lines = lineSuggestion.suggestNumberOfLines(textSize);
      parts = breakText(lines);
    }
  }

  private String[] breakText(int lines) {
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

  private int yCorrection(int currentLine, int numberOfLines) {
    if (numberOfLines == 1) {
      return -4;
    }
    if (numberOfLines == 2) {
      return 4 + ((currentLine - 1) * LINE_HEIGHT);
    }
    return -4 + ((currentLine - 1) * LINE_HEIGHT);
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setStroke(RGBColor stroke) {
    this.textColor = stroke;
  }
}
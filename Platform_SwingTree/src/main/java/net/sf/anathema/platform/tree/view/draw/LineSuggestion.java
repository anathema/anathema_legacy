package net.sf.anathema.platform.tree.view.draw;

import java.awt.FontMetrics;

public class LineSuggestion {

  public int suggestNumberOfLines(FontMetrics textMetrics, String text) {
    float textLength = textMetrics.stringWidth(text);
    return Math.min(3, (int) Math.ceil(textLength / 95));
  }
}
package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.Area;

public class LineSuggestion {

  public int suggestNumberOfLines(Area textSize) {
    float textLength = textSize.width;
    return Math.min(3, (int) Math.ceil(textLength / 95));
  }
}
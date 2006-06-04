package net.sf.anathema.character.impl.reporting;

import com.lowagie.text.Rectangle;

public enum PageSize {

  A4(com.lowagie.text.PageSize.A4), Letter(com.lowagie.text.PageSize.LETTER);

  private final Rectangle size;

  private PageSize(Rectangle size) {
    this.size = size;
  }

  public Rectangle getRectangle() {
    return size;
  }
}
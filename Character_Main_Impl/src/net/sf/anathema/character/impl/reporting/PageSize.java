package net.sf.anathema.character.impl.reporting;

import com.itextpdf.text.Rectangle;

public enum PageSize {

  A4(com.itextpdf.text.PageSize.A4), Letter(com.itextpdf.text.PageSize.LETTER);

  private final Rectangle size;

  private PageSize(Rectangle size) {
    this.size = size;
  }

  public Rectangle getRectangle() {
    return size;
  }
}

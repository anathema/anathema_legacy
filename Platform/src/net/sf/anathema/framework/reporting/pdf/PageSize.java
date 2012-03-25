package net.sf.anathema.framework.reporting.pdf;

import com.itextpdf.text.Rectangle;

public enum PageSize {

  A4(com.itextpdf.text.PageSize.A4, com.itextpdf.text.PageSize.A4_LANDSCAPE), Letter(com.itextpdf.text.PageSize.LETTER,
          com.itextpdf.text.PageSize.LETTER_LANDSCAPE);

  private final Rectangle portrait;
  private Rectangle landscape;

  private PageSize(Rectangle portrait, Rectangle landscape) {
    this.portrait = portrait;
    this.landscape = landscape;
  }

  public Rectangle getPortraitRectangle() {
    return portrait;
  }

  public Rectangle getLandscape() {
    return landscape;
  }
}

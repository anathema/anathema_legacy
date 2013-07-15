package net.sf.anathema.framework.reporting.pdf;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.RectangleReadOnly;

public enum PageSize {

  A4(com.itextpdf.text.PageSize.A4), Letter(com.itextpdf.text.PageSize.LETTER);

  private final Rectangle portrait;

  private PageSize(Rectangle portrait) {
    this.portrait = portrait;
  }

  public Rectangle getPortraitRectangle() {
    return portrait;
  }

  public Rectangle getLandscapeRectangle() {
    return new RectangleReadOnly(portrait.getHeight(), portrait.getWidth());
  }
}

package net.sf.anathema.development.reporting.encoder.page;

import java.awt.Dimension;
import java.awt.Insets;

public class PageFormat implements IPageFormat {

  private static final int LEFT_RIGHT_MARGIN = 40;
  private static final int PAGE_HEIGHT = 842;
  private static final int PAGE_WIDTH = 595;
  private Dimension pageSize;
  private Insets insets;

  public static IPageFormat createDefault(int marginTop, int marginBottom) {
    return createDinA4PageFormat(new Insets(marginTop, LEFT_RIGHT_MARGIN, marginBottom, LEFT_RIGHT_MARGIN));
  }

  private static IPageFormat createDinA4PageFormat(Insets insets) {
    return new PageFormat(new Dimension(PAGE_WIDTH, PAGE_HEIGHT), insets);
  }

  public static IPageFormat recreateWithoutInsets(IPageFormat format) {
    return new PageFormat(new Dimension(format.getColumnWidth(), format.getPageHeight()), new Insets(0, 0, 0, 0));
  }

  public PageFormat(Dimension pageSize, Insets insets) {
    this.pageSize = pageSize;
    this.insets = insets;
  }

  public Dimension getPageSize() {
    return pageSize;
  }

  public int getColumnWidth() {
    return pageSize.width - getInsets().left - getInsets().right;
  }

  public int getPageHeight() {
    return getPageSize().height - getInsets().top - getInsets().bottom;
  }

  public Insets getInsets() {
    return insets;
  }
}
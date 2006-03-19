package net.sf.anathema.development.reporting.encoder.page;

import java.awt.Dimension;
import java.awt.Insets;

public interface IPageFormat {

  public Dimension getPageSize();

  public int getColumnWidth();

  public Insets getInsets();

  public int getPageHeight();
}
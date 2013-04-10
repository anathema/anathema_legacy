package net.sf.anathema.lib.gui;

import net.sf.anathema.lib.file.RelativePath;

public class AbstractUIConfiguration<T> implements AgnosticUIConfiguration<T> {
  @Override
  public RelativePath getIconsRelativePath(T value) {
    return NO_ICON;
  }

  @Override
  public String getLabel(T value) {
    return NO_LABEL;
  }

  @Override
  public String getToolTipText(T value) {
    return NO_TOOLTIP;
  }
}
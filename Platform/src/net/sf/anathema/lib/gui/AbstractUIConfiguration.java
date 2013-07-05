package net.sf.anathema.lib.gui;

import net.sf.anathema.lib.file.RelativePath;

public class AbstractUIConfiguration<T> implements AgnosticUIConfiguration<T> {
  @Override
  public RelativePath getIconsRelativePath(T value) {
    if(value == null) {
      return NO_ICON;
    }
    return iconForExistingValue(value);
  }

  @Override
  public String getLabel(T value) {
    if(value == null) {
      return NO_LABEL;
    }
    return labelForExistingValue(value);
  }

  @Override
  public String getToolTipText(T value) {
    if (value == null) {
      return NO_TOOLTIP;
    }
    return tooltipForExistingValue(value);
  }

  protected String tooltipForExistingValue(T value) {
    return NO_TOOLTIP;
  }

  protected String labelForExistingValue(T value) {
    return NO_LABEL;
  }

  protected RelativePath iconForExistingValue(T value) {
    return NO_ICON;
  }
}
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
  public void configureTooltip(T value, ConfigurableTooltip configurableTooltip) {
    if (value == null){
       configurableTooltip.showNoTooltip();
    }
    else {
      configureTooltipForExistingValue(value, configurableTooltip);
    }
  }

  /**Subclasses must never call this method via "super", lest they get no tooltip at all.*/
  protected void configureTooltipForExistingValue(T value, ConfigurableTooltip configurableTooltip) {
    configurableTooltip.showNoTooltip();
  }

  protected String labelForExistingValue(T value) {
    return NO_LABEL;
  }

  protected RelativePath iconForExistingValue(T value) {
    return NO_ICON;
  }
}
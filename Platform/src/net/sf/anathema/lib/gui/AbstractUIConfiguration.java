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

  //*This will go away when Swing replaced. Replaced by "configureTooltip" below.*/
  @Deprecated
  @Override
  public String getToolTipText(T value) {
    if (value == null) {
      return NO_TOOLTIP;
    }
    return tooltipForExistingValue(value);
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

  //*This will go away when Swing replaced. Replaced by "configureTooltipForExistingValue" below.*/
  @Deprecated
  protected String tooltipForExistingValue(T value) {
    return NO_TOOLTIP;
  }

  protected void configureTooltipForExistingValue(T value, ConfigurableTooltip configurableTooltip) {
    String text = tooltipForExistingValue(value);
    configurableTooltip.appendLine(text);
  }

  protected String labelForExistingValue(T value) {
    return NO_LABEL;
  }

  protected RelativePath iconForExistingValue(T value) {
    return NO_ICON;
  }
}
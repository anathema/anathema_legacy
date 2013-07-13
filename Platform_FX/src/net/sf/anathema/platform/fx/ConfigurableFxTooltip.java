package net.sf.anathema.platform.fx;

import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import net.sf.anathema.lib.gui.ConfigurableTooltip;

public class ConfigurableFxTooltip implements ConfigurableTooltip {
  private boolean tooltipEnabled = true;
  private String text;

  @Override
  public void showNoTooltip() {
    tooltipEnabled = false;
  }

  @Override
  public void addPlainText(String text) {
    this.text = text;
  }

  public void configure(Control control) {
    if (!tooltipEnabled) {
      return;
    }
    control.setTooltip(new Tooltip(text));
  }
}
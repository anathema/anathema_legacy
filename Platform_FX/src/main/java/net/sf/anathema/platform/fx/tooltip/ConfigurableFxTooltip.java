package net.sf.anathema.platform.fx.tooltip;

import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import net.sf.anathema.lib.gui.ConfigurableTooltip;

public class ConfigurableFxTooltip implements ConfigurableTooltip {
  private final FxTooltipBuilder builder = new FxTooltipBuilder();
  private boolean tooltipEnabled = true;

  @Override
  public void showNoTooltip() {
    tooltipEnabled = false;
  }

  @Override
  public void appendLine(String text) {
    builder.appendLine(text);
  }

  @Override
  public void appendTitleLine(String title) {
    builder.appendTitleLine(title);
  }

  @Override
  public void appendLine(String labelText, String valueText) {
    builder.appendLine(labelText, valueText);
  }

  @Override
  public void appendDescriptiveLine(String description) {
    builder.appendDescriptiveLine(description);
  }

  public void configure(Control control) {
    if (!tooltipEnabled) {
      return;
    }
    Tooltip tooltip = new Tooltip();
    tooltip.setGraphic(builder.getNode());
    control.setTooltip(tooltip);
  }
}
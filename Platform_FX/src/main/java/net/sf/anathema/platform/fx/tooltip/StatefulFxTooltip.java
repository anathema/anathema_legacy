package net.sf.anathema.platform.fx.tooltip;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import net.sf.anathema.lib.gui.StatefulTooltip;

public class StatefulFxTooltip implements StatefulTooltip {

  private final FxTooltipBuilder builder = new FxTooltipBuilder();
  private final Tooltip tooltip = new Tooltip();
  private boolean tooltipEnabled = true;
  private final Node node;

  public StatefulFxTooltip(Node node) {
    this.node = node;
  }

  @Override
  public void reset() {
    builder.reset();
    tooltipEnabled = true;
  }

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

  public void apply() {
    if (!tooltipEnabled) {
      Tooltip.uninstall(node, tooltip);
      return;
    }
    tooltip.setGraphic(builder.getNode());
    Tooltip.install(node, tooltip);
  }
}

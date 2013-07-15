package net.sf.anathema.platform.fx;

import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import net.miginfocom.layout.CC;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.lang.StringUtilities.insertLineBreakEveryXCharacters;

public class ConfigurableFxTooltip implements ConfigurableTooltip {
  private final MigPane body = new MigPane(LayoutUtils.withoutInsets().wrapAfter(2));
  public static final int DEFAULT_TOOLTIP_WIDTH = 80;
  private boolean tooltipEnabled = true;

  @Override
  public void showNoTooltip() {
    tooltipEnabled = false;
  }

  @Override
  public void appendLine(String text) {
    addAsLine(new Label(text));
  }

  @Override
  public void appendTitleLine(String title) {
    Label label = new Label(title);
    label.getStyleClass().add("boldText");
    addAsLine(label);
  }

  @Override
  public void appendLine(String labelText, String valueText) {
    Label label = new Label(labelText + ": ");
    label.getStyleClass().add("italicText");
    Label value = new Label(valueText);
    body.add(label);
    body.add(value);
  }

  @Override
  public void appendDescriptiveLine(String description) {
    String descriptionWithLineBreaks = insertLineBreakEveryXCharacters(description, "\n", DEFAULT_TOOLTIP_WIDTH);
    Label label = new Label(descriptionWithLineBreaks);
    label.getStyleClass().add("italicText");
    addAsLine(label);
  }

  public void configure(Control control) {
    if (!tooltipEnabled) {
      return;
    }
    Tooltip tooltip = new Tooltip();
    tooltip.setGraphic(body);
    control.setTooltip(tooltip);
  }

  private void addAsLine(Node node) {
    body.add(node, new CC().span());
  }
}
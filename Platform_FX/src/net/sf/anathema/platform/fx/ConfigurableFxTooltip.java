package net.sf.anathema.platform.fx;

import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import net.miginfocom.layout.CC;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import org.tbee.javafx.scene.layout.MigPane;

public class ConfigurableFxTooltip implements ConfigurableTooltip {
  private final MigPane body = new MigPane(LayoutUtils.withoutInsets().wrapAfter(2));
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
  public void appendTitleLine(String text) {
    Label label = new Label(text);
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
  public void appendParagraphs(String... paragraphs) {
    for (String paragraph : paragraphs) {
      appendLine(paragraph);
    }
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
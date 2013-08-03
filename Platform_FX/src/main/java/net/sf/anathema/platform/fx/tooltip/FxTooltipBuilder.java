package net.sf.anathema.platform.fx.tooltip;

import javafx.scene.Node;
import javafx.scene.control.Label;
import net.miginfocom.layout.CC;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.lang.StringUtilities.insertLineBreakEveryXCharacters;

public class FxTooltipBuilder {
  private final MigPane body = new MigPane(LayoutUtils.withoutInsets().wrapAfter(2));
  public static final int DEFAULT_TOOLTIP_WIDTH = 80;

  public void appendLine(String text) {
    addAsLine(new Label(text));
  }

  public void appendTitleLine(String title) {
    Label label = new Label(title);
    label.getStyleClass().add("boldText");
    addAsLine(label);
  }

  public void appendLine(String labelText, String valueText) {
    Label label = new Label(labelText + ": ");
    label.getStyleClass().add("italicText");
    Label value = new Label(valueText);
    body.add(label);
    body.add(value);
  }

  public void appendDescriptiveLine(String description) {
    String descriptionWithLineBreaks = insertLineBreakEveryXCharacters(description, "\n", DEFAULT_TOOLTIP_WIDTH);
    Label label = new Label(descriptionWithLineBreaks);
    label.getStyleClass().add("italicText");
    addAsLine(label);
  }

  private void addAsLine(Node node) {
    body.add(node, new CC().span());
  }

  public void reset() {
    body.getChildren().clear();
  }

  public Node getNode() {
    return body;
  }
}

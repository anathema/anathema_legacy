package net.sf.anathema.hero.combos.display.view;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.combos.display.presenter.ComboView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.tbee.javafx.scene.layout.MigPane;

public class FxComboView implements ComboView {
  private final TitledPane titledCombo = new TitledPane();
  private final MigPane parent;
  private final Label charms = new Label();
  private final Label description = new Label();
  private final MigPane buttonPanel = new MigPane(LayoutUtils.fillWithoutInsets());

  public FxComboView(MigPane parent, String name) {
    this.parent = parent;
    MigPane comboBody = new MigPane(LayoutUtils.fillWithoutInsets());
    comboBody.add(charms, new CC().wrap());
    comboBody.add(description, new CC().wrap());
    comboBody.add(buttonPanel, new CC().wrap());
    charms.getStyleClass().add("comboCharms");
    description.getStyleClass().add("comboDescription");
    titledCombo.setContent(comboBody);
    displayComboName(name);
  }

  public Node getNode() {
    return titledCombo;
  }

  @Override
  public Tool addTool() {
    FxButtonTool tool = FxButtonTool.ForToolbar();
    buttonPanel.add(tool.getNode());
    return tool;
  }

  @Override
  public void remove() {
    parent.getChildren().remove(titledCombo);
  }

  @Override
  public void displayComboName(String name) {
    titledCombo.setText(name);
  }

  @Override
  public void displayCharmNames(String commaSeparatedCharms) {
    charms.setText(commaSeparatedCharms);
  }

  @Override
  public void displayDescription(String text) {
    description.setText(text);
  }
}

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
  private final Label label = new Label();
  private final MigPane buttonPanel = new MigPane(LayoutUtils.fillWithoutInsets());

  public FxComboView(MigPane parent, String name, String description) {
    this.parent = parent;
    MigPane comboBody = new MigPane(LayoutUtils.fillWithoutInsets());
    comboBody.add(label, new CC().wrap());
    comboBody.add(buttonPanel, new CC().wrap());
    titledCombo.setContent(comboBody);
    updateCombo(name, description);
  }

  public Node getNode() {
    return titledCombo;
  }

  @Override
  public void updateCombo(String name, String description) {
    titledCombo.setText(name);
    label.setText(description);
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
}

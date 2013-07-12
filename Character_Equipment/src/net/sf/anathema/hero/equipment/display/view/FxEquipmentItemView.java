package net.sf.anathema.hero.equipment.display.view;

import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentObjectView;
import net.sf.anathema.hero.equipment.display.presenter.StatsView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.tbee.javafx.scene.layout.MigPane;

public class FxEquipmentItemView implements EquipmentObjectView {
  private final TitledPane border = new TitledPane();
  private final MigPane body = new MigPane();
  private final Label descriptionLabel = new Label();

  public FxEquipmentItemView() {
    border.setContent(body);
  }

  @Override
  public void setItemTitle(String title) {
    border.setText(title);
  }

  @Override
  public void setItemDescription(String text) {
    body.add(new Label(text));
  }

  @Override
  public void clear() {
    setItemTitle("");
    body.getChildren().removeAll();
  }

  @Override
  public StatsView addStats(String description) {
    FxStatsView statsView = new FxStatsView(description);
    body.add(statsView.getNode());
    return statsView;
  }

  @Override
  public Tool addAction() {
    FxButtonTool tool = FxButtonTool.ForToolbar();
    body.add(tool.getNode());
    return tool;
  }
}

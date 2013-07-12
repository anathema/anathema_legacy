package net.sf.anathema.hero.equipment.display.view;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentObjectView;
import net.sf.anathema.hero.equipment.display.presenter.StatsView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.platform.fx.FxThreading;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.tbee.javafx.scene.layout.MigPane;

public class FxEquipmentItemView implements EquipmentObjectView {
  private final TitledPane border = new TitledPane();
  private final Label descriptionLabel = new Label();
  private final MigPane elementPane = new MigPane(LayoutUtils.fillWithoutInsets().wrapAfter(1));
  private final MigPane buttonPane = new MigPane(LayoutUtils.fillWithoutInsets());

  public FxEquipmentItemView() {
    MigPane body = new MigPane(LayoutUtils.fillWithoutInsets().wrapAfter(1));
    body.add(descriptionLabel);
    body.add(elementPane);
    body.add(buttonPane);
    border.setContent(body);
  }

  @Override
  public void setItemTitle(String title) {
    border.setText(title);
  }

  @Override
  public void setItemDescription(String text) {
    descriptionLabel.setText(text);
  }

  @Override
  public void clear() {
    elementPane.getChildren().clear();
    buttonPane.getChildren().clear();
  }

  @Override
  public StatsView addStats(String description) {
    final FxStatsView statsView = new FxStatsView(description);
    FxThreading.runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        elementPane.add(statsView.getNode());
      }
    });
    return statsView;
  }

  @Override
  public Tool addAction() {
    FxButtonTool tool = FxButtonTool.ForToolbar();
    buttonPane.add(tool.getNode());
    return tool;
  }

  public Node getNode() {
    return border;
  }
}
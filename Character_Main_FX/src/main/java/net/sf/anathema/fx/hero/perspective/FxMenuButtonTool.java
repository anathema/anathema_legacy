package net.sf.anathema.fx.hero.perspective;

import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;
import net.sf.anathema.framework.view.menu.FxMenuTool;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.platform.view.MenuTool;
import net.sf.anathema.platform.tool.AdjustSize;
import net.sf.anathema.platform.tool.FxBaseTool;
import net.sf.anathema.platform.tool.ImageClosure;
import net.sf.anathema.platform.tool.SetImage;


public class FxMenuButtonTool extends FxBaseTool implements MenuTool {
  public static FxMenuButtonTool ForToolbar() {
    ImageView mainIcon = new ImageView();
    ImageView miniIcon = new ImageView();
    SplitMenuButton button = new SplitMenuButton();
    button.setGraphic(mainIcon);
    AdjustSize adjustSize = new AdjustSize(button);
    adjustSize.addExtraWidth(30);
    return new FxMenuButtonTool(button, miniIcon, adjustSize, new SetImage(mainIcon));
  }

  private final SplitMenuButton button;

  public FxMenuButtonTool(SplitMenuButton button, ImageView overlay, ImageClosure... actionsOnLoad) {
    super(button, overlay, actionsOnLoad);
    this.button = button;
  }

  @Override
  public void clearMenu() {
    button.getItems().clear();
  }

  @Override
  public Tool addMenuEntry() {
    FxMenuTool tool = new FxMenuTool();
    button.getItems().add(tool.getNode());
    return tool;
  }
}
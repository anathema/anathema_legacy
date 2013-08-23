package net.sf.anathema.platform.tool;

import javafx.scene.image.ImageView;
import jfxtras.labs.scene.control.MiniIconButton;
import net.sf.anathema.platform.fx.FxComponent;

public class FxButtonTool extends FxBaseTool implements FxComponent {

  public static FxButtonTool ForToolbar() {
    ImageView mainIcon = new ImageView();
    ImageView miniIcon = new ImageView();
    MiniIconButton button = createButton(mainIcon, miniIcon);
    return new FxButtonTool(button, miniIcon, new AdjustSize(button), new SetImage(mainIcon));
  }

  public static FxButtonTool ForAnyPurpose() {
    ImageView mainIcon = new ImageView();
    ImageView miniIcon = new ImageView();
    MiniIconButton button = createButton(mainIcon, miniIcon);
    return new FxButtonTool(button, miniIcon, new SetImage(mainIcon));
  }

  private static MiniIconButton createButton(ImageView mainIcon, ImageView miniIcon) {
    MiniIconButton button = new MiniIconButton();
    button.setGraphic(mainIcon);
    button.setMiniIcon(miniIcon);
    button.setMiniIconRatio(0.33);
    return button;
  }

  public FxButtonTool(MiniIconButton button, ImageView miniIcon, ImageClosure... onLoad) {
    super(button, miniIcon, onLoad);
  }
}
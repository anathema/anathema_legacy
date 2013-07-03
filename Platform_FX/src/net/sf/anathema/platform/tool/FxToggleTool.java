package net.sf.anathema.platform.tool;

import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import net.sf.anathema.interaction.ToggleTool;

public class FxToggleTool extends FxBaseTool implements ToggleTool {

  public static FxToggleTool create() {
    ImageView mainIcon = new ImageView();
    ImageView miniIcon = new ImageView();
    ToggleButton button = new ToggleButton("", mainIcon);
    return new FxToggleTool(button, miniIcon, new AdjustSize(button), new SetImage(mainIcon));
  }

  private final ToggleButton button;

  public FxToggleTool(ToggleButton button, ImageView miniIcon, ImageClosure... onLoad) {
    super(button, miniIcon, onLoad);
    this.button = button;
  }

  @Override
  public void select() {
    button.setSelected(true);
  }

  @Override
  public void deselect() {
    button.setSelected(false);
  }
}
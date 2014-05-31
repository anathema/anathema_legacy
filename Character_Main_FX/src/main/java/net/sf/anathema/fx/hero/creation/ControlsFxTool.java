package net.sf.anathema.fx.hero.creation;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Hotkey;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.platform.tool.ImageContainer;
import net.sf.anathema.platform.tool.LoadImage;
import net.sf.anathema.platform.tool.SetImage;
import org.controlsfx.control.action.Action;

public class ControlsFxTool implements Tool {
  private ConfigurableControlsFxAction action = new ConfigurableControlsFxAction("");

  @Override
  public void setIcon(RelativePath relativePath) {
    ImageContainer image = new LoadImage(relativePath).run();
    ImageView imageView = new ImageView();
    new SetImage(imageView).run(image);
    action.graphicProperty().setValue(imageView);
  }

  @Override
  public void setOverlay(RelativePath relativePath) {
    //nothing to do
  }

  @Override
  public void setTooltip(String text) {
    action.longTextProperty().setValue(text);
  }

  @Override
  public void setText(String text) {
    action.textProperty().setValue(text);
  }

  @Override
  public void enable() {
    action.disabledProperty().setValue(false);
  }

  @Override
  public void disable() {
    action.disabledProperty().setValue(true);
  }

  @Override
  public void setCommand(Command command) {
    action.setCommand(command);
  }

  @Override
  public void setHotkey(Hotkey s) {
    //nothing to do
  }

  public Action getAction() {
    return action;
  }
}

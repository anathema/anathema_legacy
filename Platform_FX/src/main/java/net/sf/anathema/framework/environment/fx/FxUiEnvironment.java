package net.sf.anathema.framework.environment.fx;

import javafx.stage.Stage;
import net.sf.anathema.initialization.ProxyFileChooser;
import net.sf.anathema.interaction.AcceleratorMap;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Hotkey;
import net.sf.anathema.interaction.ProxyAcceleratorMap;
import net.sf.anathema.lib.gui.file.Extension;
import net.sf.anathema.lib.gui.file.FileChooserConfiguration;
import net.sf.anathema.lib.gui.file.SingleFileChooser;
import org.controlsfx.dialog.Dialog;

import java.nio.file.Path;

import static org.controlsfx.dialog.DialogStyle.NATIVE;

public class FxUiEnvironment implements UiEnvironment {

  private final ProxyFileChooser chooser = new ProxyFileChooser();
  private final ProxyAcceleratorMap map = new ProxyAcceleratorMap();
  private Stage stage;

  @Override
  public void register(Hotkey hotkey, Command command) {
    map.register(hotkey, command);
  }

  @Override
  public Path selectSaveFile(FileChooserConfiguration configuration) {
    return chooser.selectSaveFile(configuration);
  }

  @Override
  public Path selectLoadFile(Extension extension) {
    return chooser.selectLoadFile(extension);
  }

  public void setFileChooser(SingleFileChooser chooser) {
    this.chooser.setDelegate(chooser);
  }

  public void setAcceleratorMap(AcceleratorMap map) {
    this.map.setActualMap(map);
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  @Override
  public Dialog createDialog(String title) {
    return new Dialog(stage, title, false, NATIVE);
  }
}

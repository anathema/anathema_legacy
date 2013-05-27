package net.sf.anathema.platform.tool;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jfxtras.labs.scene.control.MiniIconButton;
import net.sf.anathema.interaction.AcceleratorMap;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Hotkey;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.file.RelativePath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FxButtonTool implements Tool {


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

  private final Button button;
  private final ImageView overlay;
  private final List<ImageClosure> onLoad = new ArrayList<>();
  private final ProxyAcceleratorMap acceleratorMap = new ProxyAcceleratorMap();
  private Command command;

  public FxButtonTool(Button button, ImageView overlay, ImageClosure... actionsOnLoad) {
    this.button = button;
    this.overlay = overlay;
    Collections.addAll(onLoad, actionsOnLoad);
  }

  @Override
  public void setIcon(final RelativePath relativePath) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        Image image = new LoadImage(relativePath).run();
        for (ImageClosure action : onLoad) {
          action.run(image);
        }
      }
    });
  }


  @Override
  public void setOverlay(final RelativePath relativePath) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        Image image = new LoadImage(relativePath).run();
        new SetImage(overlay).run(image);
      }
    });
  }

  @Override
  public void setTooltip(final String text) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        button.setTooltip(new Tooltip(text));
      }
    });
  }

  @Override
  public void setText(final String text) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        button.setText(text);
      }
    });
  }

  @Override
  public void enable() {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        button.setDisable(false);
      }
    });
  }

  @Override
  public void disable() {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        button.setDisable(true);
      }
    });
  }

  @Override
  public void setCommand(final Command command) {
    this.command = command;
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        button.setOnAction(new Execute(command));
      }
    });
  }

  @Override
  public void setHotkey(Hotkey key) {
    acceleratorMap.register(key, command);
  }

  public Node getNode() {
    return button;
  }

  public void registerAcceleratorsWith(AcceleratorMap acceleratorMap) {
    this.acceleratorMap.setActualMap(acceleratorMap);
  }
}
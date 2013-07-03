package net.sf.anathema.platform.tool;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import net.sf.anathema.interaction.AcceleratorMap;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.CommandProxy;
import net.sf.anathema.interaction.Hotkey;
import net.sf.anathema.interaction.ProxyAcceleratorMap;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.platform.fx.FxComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.sf.anathema.platform.fx.FxUtilities.systemSupportsPopUpsWhileEmbeddingFxIntoSwing;

public class FxToggleTool implements ToggleTool, FxComponent {
  private final ToggleButton button;
  private final List<ImageClosure> onLoad = new ArrayList<>();
  private final ProxyAcceleratorMap acceleratorMap = new ProxyAcceleratorMap();
  private final CommandProxy command = new CommandProxy();

  public FxToggleTool() {
    ImageView imageView = new ImageView();
    this.button = new ToggleButton("", imageView);
    Collections.addAll(onLoad, new AdjustSize(button), new SetImage(imageView));
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        button.setOnAction(new Execute(command));
      }
    });
  }

  @Override
  public void setIcon(final RelativePath relativePath) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        ImageContainer image = new LoadImage(relativePath).run();
        for (ImageClosure action : onLoad) {
          action.run(image);
        }
      }
    });
  }


  @Override
  public void setOverlay(final RelativePath relativePath) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public void setTooltip(final String text) {
    if (!systemSupportsPopUpsWhileEmbeddingFxIntoSwing()) {
      return;
    }
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
    this.command.setDelegate(command);
  }

  @Override
  public void setHotkey(Hotkey key) {
    acceleratorMap.register(key, command);
  }

  @SuppressWarnings("UnusedDeclaration")
  public void registerHotkeyIn(AcceleratorMap actualMap) {
    acceleratorMap.setActualMap(actualMap);
  }

  @Override
  public Node getNode() {
    return button;
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
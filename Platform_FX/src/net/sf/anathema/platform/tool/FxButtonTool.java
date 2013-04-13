package net.sf.anathema.platform.tool;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.file.RelativePath;

public class FxButtonTool implements Tool {

  private Button button;
  private ImageView imageView;

  public FxButtonTool() {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        imageView = new ImageView();
        button = new Button("", imageView);
      }
    });
  }

  @Override
  public void setIcon(final RelativePath relativePath) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        Image image = new LoadImage(relativePath).run();
        new AdjustSize(button).adjustTo(image);
        imageView.setImage(image);
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
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        button.setOnAction(new Execute(command));
      }
    });
  }

  public Node getNode() {
    return button;
  }
}
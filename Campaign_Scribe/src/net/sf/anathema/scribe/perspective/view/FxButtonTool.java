package net.sf.anathema.scribe.perspective.view;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.scribe.perspective.NodeHolder;
import net.sf.anathema.scribe.perspective.presenter.Tool;

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
  public void setIcon(final String relativePath) {
    Platform.runLater(new SetImage(new NodeHolder<ImageView>() {
      @Override
      public ImageView getNode() {
        return imageView;
      }
    }, relativePath));
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
    Platform.runLater(new Enable(button));
  }

  @Override
  public void disable() {
    Platform.runLater(new Disable(button));
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

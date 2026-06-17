package net.sf.anathema.framework.fx.compat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.control.action.Action;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Compatibility replacement for the lightweight {@code org.controlsfx.dialog.Dialog} that ControlsFX
 * removed after 8.0.x. It reproduces the slice of behaviour Anathema relies on — a modal window with
 * an optional masthead, a content node, and a row of buttons built from {@link Action}s — on top of a
 * plain JavaFX {@link Stage}. Built-in actions ({@link Actions}) close the dialog when clicked; custom
 * actions simply run their handler.
 */
public class Dialog {

  /** Built-in actions, kept as identity constants so callers can compare with {@code ==}. */
  public static final class Actions {
    public static final Action OK = new Action(ButtonType.OK.getText());
    public static final Action CANCEL = new Action(ButtonType.CANCEL.getText());
    public static final Action CLOSE = new Action(ButtonType.CLOSE.getText());
    public static final Action YES = new Action(ButtonType.YES.getText());
    public static final Action NO = new Action(ButtonType.NO.getText());

    private Actions() {
    }
  }

  private static final Set<Action> BUILT_IN = new HashSet<>(
      Arrays.asList(Actions.OK, Actions.CANCEL, Actions.CLOSE, Actions.YES, Actions.NO));

  private final Stage stage = new Stage();
  private final BorderPane root = new BorderPane();
  private final HBox buttonBar = new HBox(10);
  private final ObservableList<Action> actions = FXCollections.observableArrayList();
  private Action selectedAction;

  public Dialog(Window owner, String title, boolean lightweight, DialogStyle style) {
    if (owner != null) {
      stage.initOwner(owner);
      stage.initModality(Modality.WINDOW_MODAL);
    } else {
      stage.initModality(Modality.APPLICATION_MODAL);
    }
    stage.setTitle(title);
    buttonBar.setAlignment(Pos.CENTER_RIGHT);
    buttonBar.setPadding(new Insets(10));
    root.setBottom(buttonBar);
    stage.setScene(new Scene(root));
  }

  public void setTitle(String title) {
    stage.setTitle(title);
  }

  public void setMasthead(String masthead) {
    if (masthead == null || masthead.isEmpty()) {
      root.setTop(null);
      return;
    }
    Label header = new Label(masthead);
    header.setWrapText(true);
    header.setPadding(new Insets(10));
    root.setTop(header);
  }

  public void setContent(Node content) {
    root.setCenter(content);
  }

  public ObservableList<Action> getActions() {
    return actions;
  }

  /** The owning window; its scene exists from construction, so accelerators can be registered early. */
  public Window getWindow() {
    return stage;
  }

  /** Shows the dialog without blocking (built-in buttons close it; custom handlers manage their own flow). */
  public void show() {
    buildButtons();
    stage.sizeToScene();
    stage.show();
  }

  /** Shows the dialog modally and returns the action whose button was pressed (null if dismissed). */
  public Action showAndWaitForAction() {
    buildButtons();
    selectedAction = null;
    stage.sizeToScene();
    stage.showAndWait();
    return selectedAction;
  }

  public void hide() {
    stage.close();
  }

  private void buildButtons() {
    buttonBar.getChildren().clear();
    for (Action action : actions) {
      Button button = new Button();
      button.textProperty().bind(action.textProperty());
      button.graphicProperty().bind(action.graphicProperty());
      button.disableProperty().bind(action.disabledProperty());
      button.setOnAction(event -> {
        action.handle(event);
        if (BUILT_IN.contains(action)) {
          selectedAction = action;
          hide();
        }
      });
      buttonBar.getChildren().add(button);
    }
  }
}

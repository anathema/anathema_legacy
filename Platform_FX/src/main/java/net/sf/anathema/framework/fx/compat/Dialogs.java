package net.sf.anathema.framework.fx.compat;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Window;
import org.controlsfx.control.action.Action;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Compatibility replacement for the {@code org.controlsfx.dialog.Dialogs} fluent builder removed after
 * ControlsFX 8.0.x. Only the methods Anathema used are provided, backed by {@link Dialog}.
 */
public class Dialogs {

  private Window owner;
  private String title;
  private String masthead;
  private String message;
  private Action[] actions;

  public static Dialogs create() {
    return new Dialogs();
  }

  public Dialogs owner(Window owner) {
    this.owner = owner;
    return this;
  }

  public Dialogs title(String title) {
    this.title = title;
    return this;
  }

  public Dialogs masthead(String masthead) {
    this.masthead = masthead;
    return this;
  }

  public Dialogs message(String message) {
    this.message = message;
    return this;
  }

  /** Visual style is a no-op on modern JavaFX; kept for source compatibility. */
  public Dialogs style(DialogStyle style) {
    return this;
  }

  public Dialogs actions(Action... actions) {
    this.actions = actions;
    return this;
  }

  /** Shows a modal confirmation and returns the {@link Action} whose button was pressed. */
  public Action showConfirm() {
    Dialog dialog = newDialog();
    Label content = new Label(message);
    content.setWrapText(true);
    dialog.setContent(content);
    dialog.getActions().setAll(actions != null ? actions : new Action[]{Dialog.Actions.OK});
    return dialog.showAndWaitForAction();
  }

  /** Shows a modal error dialog with the throwable's stack trace in an expandable text area. */
  public void showException(Throwable throwable) {
    Dialog dialog = newDialog();
    StringWriter writer = new StringWriter();
    throwable.printStackTrace(new PrintWriter(writer));
    TextArea details = new TextArea(writer.toString());
    details.setEditable(false);
    details.setWrapText(false);
    details.setPrefColumnCount(60);
    details.setPrefRowCount(20);
    dialog.setContent(details);
    dialog.getActions().setAll(Dialog.Actions.OK);
    dialog.showAndWaitForAction();
  }

  private Dialog newDialog() {
    Dialog dialog = new Dialog(owner, title, false, DialogStyle.NATIVE);
    dialog.setMasthead(masthead);
    return dialog;
  }
}

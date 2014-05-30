package net.sf.anathema.hero.equipment.display.view.personalization;

import javafx.stage.Window;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentPersonalizationProperties;
import net.sf.anathema.hero.equipment.display.presenter.PersonalizationEditView;
import net.sf.anathema.lib.util.Closure;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;

import static org.controlsfx.dialog.Dialog.Actions.CANCEL;
import static org.controlsfx.dialog.Dialog.Actions.OK;
import static org.controlsfx.dialog.DialogStyle.NATIVE;

public class FxPersonalizationEditView implements PersonalizationEditView {
  private final FxDialogPersonalizationView view;
  private final Window window;
  private Runnable onConfirmation;

  public FxPersonalizationEditView(Window window, EquipmentPersonalizationProperties properties) {
    this.window = window;
    this.view = new FxDialogPersonalizationView(properties);
  }

  @Override
  public void show() {
    Dialog dialog = new Dialog(window, "", false, NATIVE);
    dialog.getActions().setAll(OK, CANCEL);
    dialog.setContent(view.getNode());
    Action result = dialog.show();
    if (result == OK) {
      onConfirmation.run();
    }
  }

  @Override
  public void whenChangeIsConfirmed(Runnable runnable) {
    this.onConfirmation = runnable;
  }

  @Override
  public void setTitle(String title) {
    view.setTitle(title);
  }

  @Override
  public void setDescription(String description) {
    view.setDescription(description);
  }

  @Override
  public void whenTitleChanges(Closure<String> closure) {
    view.whenTitleChanges(closure);
  }

  @Override
  public void whenDescriptionChanges(Closure<String> closure) {
    view.whenDescriptionChanges(closure);
  }
}
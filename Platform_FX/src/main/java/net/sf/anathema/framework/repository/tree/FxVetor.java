package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.gui.list.veto.Vetor;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;

import static org.controlsfx.dialog.Dialog.Actions.NO;
import static org.controlsfx.dialog.Dialog.Actions.YES;

public class FxVetor implements Vetor {
  private String title;
  private final String message;

  public FxVetor(String title, String message) {
    this.title = title;
    this.message = message;
  }

  @Override
  public void requestPermissionFor(Command command) {
    Action action = Dialogs.create().title(title).masthead(null).message(message).actions(YES, NO).showConfirm();
    if (action == YES) {
      command.execute();
    }
  }
}
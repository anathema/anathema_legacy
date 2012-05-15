package net.sf.anathema.lib.gui.dialog.core;

import net.sf.anathema.lib.gui.dialog.events.IInputValidCheckable;
import net.sf.anathema.lib.gui.dialog.userdialog.IMessageSetable;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;

public class DialogPageInputValidCheckable implements IInputValidCheckable {
  private final IMessageSetable messageSetable;
  private final IDialogPage page;

  public DialogPageInputValidCheckable(IMessageSetable messageSetable, IDialogPage page) {
    this.messageSetable = messageSetable;
    this.page = page;
  }

  @Override
  public void checkInputValid() {
    messageSetable.setMessage(page.createCurrentMessage());
    page.updateInputValid();
  }
}
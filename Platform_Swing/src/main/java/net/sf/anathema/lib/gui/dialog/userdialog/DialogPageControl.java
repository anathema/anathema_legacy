package net.sf.anathema.lib.gui.dialog.userdialog;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import net.sf.anathema.lib.exception.ContractFailedException;
import net.sf.anathema.lib.gui.dialog.core.DialogPageInputValidCheckable;
import net.sf.anathema.lib.gui.dialog.core.IDialogControl;
import net.sf.anathema.lib.gui.dialog.events.IInputValidCheckable;
import net.sf.anathema.lib.gui.dialog.events.InputValidAfterChangeListener;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;
import net.sf.anathema.lib.message.IBasicMessage;

import javax.swing.JComponent;

public class DialogPageControl implements IInputValidCheckable, IMessageSetable {
  private IDialogControl dialogControl = new NullDialogControl();
  private JComponent content;
  private final IDialogPage dialogPage;
  private IBasicMessage message;

  public DialogPageControl(IDialogPage dialogPage) {
    this.dialogPage = dialogPage;
    setMessage(dialogPage.getDefaultMessage());
  }

  public boolean canFinish() {
    return dialogPage.canFinish();
  }

  @Override
  public final void setMessage(IBasicMessage message) {
    Preconditions.checkNotNull(message);
    if (Objects.equal(this.message, message)) {
      updateCanFinish();
      return;
    }
    this.message = message;
    updateCanFinish();
    dialogControl.updateMessage();
  }

  private void updateCanFinish() {
    dialogControl.updateButtons();
  }

  public final IBasicMessage getMessage() {
    return message;
  }

  public final void setDialogControl(IDialogControl dialogControl) {
    this.dialogControl = dialogControl;
  }

  public final JComponent getContent() {
    if (content == null) {
      content = dialogPage.createContent();
      if (content == null) {
        throw new ContractFailedException("Method createContent() must not return null in "
                                          + dialogPage.getClass().getName());
      }
      dialogPage.setInputValidListener(new InputValidAfterChangeListener(new DialogPageInputValidCheckable(this, dialogPage)));
      updateButtons();
    }
    return content;
  }

  protected void updateButtons() {
    dialogControl.updateButtons();
  }

  public String getDescription() {
    return dialogPage.getDescription();
  }

  @Override
  public void checkInputValid() {
    setMessage(dialogPage.createCurrentMessage());
    updateButtons();
  }

  public String getTitle() {
    return dialogPage.getTitle();
  }
}
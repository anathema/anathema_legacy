package net.sf.anathema.lib.gui.dialog.userdialog.page;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;

public abstract class AbstractDialogPage implements IDialogPage {
  private final IBasicMessage defaultMessage;

  public AbstractDialogPage(String defaultMessageText) {
    Preconditions.checkNotNull(defaultMessageText, "DefaultMessage text must not be null.");
    this.defaultMessage = new BasicMessage(defaultMessageText);
  }

  @Override
  public void setInputValidListener(IChangeListener inputValidListener) {
    //nothing to do
  }

  @Override
  public String getDescription() {
    return getTitle();
  }

  @Override
  public final IBasicMessage getDefaultMessage() {
    return defaultMessage;
  }

  /**
   * @return a non-null value of a message representing the current dialog state. This should be an error message
   *         when the dialog content is invalid. Simply return {@link #getDefaultMessage()} if there is no error or warning
   *         state.
   */
  @Override
  public abstract IBasicMessage createCurrentMessage();

  @Override
  public boolean canFinish() {
    return !createCurrentMessage().isErrorMessage();
  }
}
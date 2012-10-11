package net.sf.anathema.lib.gui.dialog.userdialog.page;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.dialog.events.ICheckInputValidListener;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import org.jmock.example.announcer.Announcer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class AbstractDialogPage implements IDialogPage {
  private final IBasicMessage defaultMessage;
  private final Announcer<IChangeListener> changeListeners = Announcer.to(IChangeListener.class);
  private ICheckInputValidListener inputValidListener;
  private final ICheckInputValidListener inputValidListenerProxy = (ICheckInputValidListener) Proxy.newProxyInstance(
          getClass().getClassLoader(), new Class[]{ICheckInputValidListener.class}, new InvocationHandler() {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      changeListeners.announce().changeOccurred();
      // (ip, mg) Events koennen schon kommen, bevor inputValidListener gesetzt wurde
      //          koennen wir aber getrost ignorieren, da nach dem Setzen des inputValidListeners
      //          Message und Button-Zustand der Seite eh initialisiert werden.
      if (inputValidListener == null) {
        return null;
      }
      return method.invoke(inputValidListener, args);
    }
  });

  public AbstractDialogPage(String defaultMessageText) {
    Preconditions.checkNotNull(defaultMessageText, "DefaultMessage text must not be null.");
    this.defaultMessage = new BasicMessage(defaultMessageText);
  }

  @Override
  public void setInputValidListener(ICheckInputValidListener inputValidListener) {
    this.inputValidListener = inputValidListener;
  }

  @Override
  public String getDescription() {
    return getTitle();
  }

  @Override
  public void requestFocus() {
    // nothing to do
  }

  protected final ICheckInputValidListener getCheckInputValidListener() {
    return inputValidListenerProxy;
  }

  protected final void checkInputValid() {
    inputValidListenerProxy.checkInputValid();
  }

  @Override
  public void updateInputValid() {
    // nothing to do
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
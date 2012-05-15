package net.sf.anathema.lib.gui.dialog.userdialog.page;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.dialog.core.AbstractPage;
import net.sf.anathema.lib.gui.dialog.events.ICheckInputValidListener;
import net.sf.anathema.lib.gui.dialog.input.RequestFinishListener;
import net.sf.anathema.lib.message.IBasicMessage;
import org.jmock.example.announcer.Announcer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class AbstractBasicDialogPage extends AbstractPage implements IBasicDialogPage {
  private final Announcer<RequestFinishListener> requestFinishListeners = Announcer.to(RequestFinishListener.class);
  private final Announcer<IChangeListener> changeListeners = Announcer.to(IChangeListener.class);

  private ICheckInputValidListener inputValidListener;
  private final ICheckInputValidListener inputValidListenerProxy = (ICheckInputValidListener) Proxy
          .newProxyInstance(getClass().getClassLoader(), new Class[]{ICheckInputValidListener.class}, new InvocationHandler() {
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

  @Override
  public IBasicMessage createCurrentMessage() {
    return null;
  }

  @Override
  public void setInputValidListener(ICheckInputValidListener inputValidListener) {
    this.inputValidListener = inputValidListener;
  }

  @Override
  @Deprecated
  public boolean performOk() {
    return true;
  }

  @Override
  @Deprecated
  public boolean performCancel() {
    return true;
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
  public final void addRequestFinishListener(RequestFinishListener requestFinishListener) {
    requestFinishListeners.addListener(requestFinishListener);
  }

  @Override
  public final void removeRequestFinishListener(RequestFinishListener requestFinishListener) {
    requestFinishListeners.removeListener(requestFinishListener);
  }

  @Override
  public void updateInputValid() {
    // nothing to do
  }

  @Override
  public boolean canFinish() {
    return !createCurrentMessage().isErrorMessage();
  }

  @Override
  public void enter() {
    //nothing to do
  }

  @Override
  public void leave() {
    //nothing to do
  }
}
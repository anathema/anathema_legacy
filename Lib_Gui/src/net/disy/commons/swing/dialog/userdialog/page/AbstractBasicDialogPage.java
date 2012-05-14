/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.userdialog.page;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.internal.AbstractPage;
import net.disy.commons.swing.dialog.input.IRequestFinishListener;
import net.disy.commons.swing.events.ICheckInputValidListener;
import net.sf.anathema.lib.control.IChangeListener;
import org.jmock.example.announcer.Announcer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class AbstractBasicDialogPage extends AbstractPage implements IBasicDialogPage {
  private final Announcer<IRequestFinishListener> requestFinishListeners = Announcer.to(IRequestFinishListener.class);
  private final Announcer<IChangeListener> changeListeners = Announcer.to(IChangeListener.class);

  private ICheckInputValidListener inputValidListener;
  private final ICheckInputValidListener inputValidListenerProxy = (ICheckInputValidListener) Proxy
      .newProxyInstance(
          getClass().getClassLoader(),
          new Class[]{ ICheckInputValidListener.class },
          new InvocationHandler() {
            @Override
            public Object invoke(final Object proxy, final Method method, final Object[] args)
                throws Throwable {
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
  public void setInputValidListener(final ICheckInputValidListener inputValidListener) {
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
  public final void addRequestFinishListener(final IRequestFinishListener requestFinishListener) {
    requestFinishListeners.addListener(requestFinishListener);
  }

  @Override
  public final void removeRequestFinishListener(final IRequestFinishListener requestFinishListener) {
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
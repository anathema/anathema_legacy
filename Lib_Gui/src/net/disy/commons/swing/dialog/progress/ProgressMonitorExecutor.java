/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.progress;

import net.disy.commons.core.progress.DefaultRunnableExecuter;
import net.disy.commons.core.progress.IInterruptableRunnableWithProgress;
import net.disy.commons.core.progress.INonInterruptableRunnableWithProgress;
import net.disy.commons.core.progress.IObservableCancelable;
import net.disy.commons.core.progress.IProgressMonitor;
import net.disy.commons.core.progress.IRunnableExecuter;

import java.lang.reflect.InvocationTargetException;

public class ProgressMonitorExecutor {

  public static final int DEFAULT_MILLISECONDS_UNTIL_DIALOG_POPUP = 500;
  private final IProgressComponent progressComponent;
  private final InternalProgressDialogModel model;
  private int millisecondsUntilDialogPopup = DEFAULT_MILLISECONDS_UNTIL_DIALOG_POPUP;
  private static IRunnableExecuter defaultExecuter = new DefaultRunnableExecuter("runWithProgress"); //$NON-NLS-1$

  public ProgressMonitorExecutor(
      final InternalProgressDialogModel model,
      final IProgressComponent progressComponent) {
    this.model = model;
    this.progressComponent = progressComponent;
  }

  public void run(
      final INonInterruptableRunnableWithProgress runnable,
      final IProgressMonitor monitor) throws InvocationTargetException {
    final Runnable actualRunnable = new Runnable() {
      @Override
      public void run() {
        try {
          runnable.run(monitor);
        }
        catch (final InvocationTargetException e) {
          model.crashed(e);
        }
        catch (final RuntimeException e) {
          model.crashed(e);
        }
        catch (final Error e) {
          model.crashed(e);
        }
        catch (final Throwable e) {
          model.crashed(new InvocationTargetException(e));
        }
        finally {
          synchronized (model) {
            model.finished();
            model.notifyAll();
          }
          progressComponent.dispose();
        }
      }
    };
    defaultExecuter.execute(actualRunnable);

    try {
      if (millisecondsUntilDialogPopup > 0) {
        synchronized (model) {
          if (!model.isFinished()) {
            model.wait(millisecondsUntilDialogPopup);
          }
        }
      }
      if (!model.isFinished()) {
        progressComponent.show();
      }
      model.throwThrowableIfAny();
    }
    catch (final InterruptedException e) {
      throw new RuntimeException("InterruptedException during non interruptable progress", e); //$NON-NLS-1$
    }
  }

  public void run(
      final IInterruptableRunnableWithProgress runnable,
      final IProgressMonitor progressMonitor,
      final IObservableCancelable cancelable)
      throws InterruptedException,
      InvocationTargetException {
    final Runnable actualRunnable = new Runnable() {
      @Override
      public void run() {
        try {
          runnable.run(progressMonitor, cancelable);
        }
        catch (final InterruptedException e) {
          model.interrupted(e);
        }
        catch (final InvocationTargetException e) {
          model.crashed(e);
        }
        catch (final RuntimeException e) {
          model.crashed(e);
        }
        catch (final Error e) {
          model.crashed(e);
        }
        catch (final Throwable e) {
          model.crashed(new InvocationTargetException(e));
        }
        finally {
          synchronized (model) {
            model.finished();
            model.notifyAll();
          }
          progressComponent.dispose();
        }
      }
    };
    defaultExecuter.execute(actualRunnable);

    synchronized (model) {
      if (!model.isFinished()) {
        model.wait(millisecondsUntilDialogPopup);
      }
    }
    if (!model.isFinished()) {
      progressComponent.show();
    }

    model.throwThrowableIfAny();
  }

}

package net.sf.anathema.lib.gui.dialog.progress;

import net.sf.anathema.lib.progress.Cancelable;
import net.sf.anathema.lib.progress.DefaultRunnableExecuter;
import net.sf.anathema.lib.progress.IInterruptibleRunnableWithProgress;
import net.sf.anathema.lib.progress.INonInterruptibleRunnableWithProgress;
import net.sf.anathema.lib.progress.IProgressMonitor;
import net.sf.anathema.lib.progress.IRunnableExecuter;

import java.lang.reflect.InvocationTargetException;

public class ProgressMonitorExecutor {

  public static final int DEFAULT_MILLISECONDS_UNTIL_DIALOG_POPUP = 500;
  private final IProgressComponent progressComponent;
  private final InternalProgressDialogModel model;
  private int millisecondsUntilDialogPopup = DEFAULT_MILLISECONDS_UNTIL_DIALOG_POPUP;
  private static IRunnableExecuter defaultExecuter = new DefaultRunnableExecuter("runWithProgress"); //$NON-NLS-1$

  public ProgressMonitorExecutor(
      InternalProgressDialogModel model,
      IProgressComponent progressComponent) {
    this.model = model;
    this.progressComponent = progressComponent;
  }

  public void run(
      final INonInterruptibleRunnableWithProgress runnable,
      final IProgressMonitor monitor) throws InvocationTargetException {
    Runnable actualRunnable = new Runnable() {
      @Override
      public void run() {
        try {
          runnable.run(monitor);
        }
        catch (InvocationTargetException e) {
          model.crashed(e);
        }
        catch (RuntimeException e) {
          model.crashed(e);
        }
        catch (Error e) {
          model.crashed(e);
        }
        catch (Throwable e) {
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
    catch (InterruptedException e) {
      throw new RuntimeException("InterruptedException during non interruptable progress", e); //$NON-NLS-1$
    }
  }

  public void run(
      final IInterruptibleRunnableWithProgress runnable,
      final IProgressMonitor progressMonitor,
      final Cancelable cancelable)
      throws InterruptedException,
      InvocationTargetException {
    Runnable actualRunnable = new Runnable() {
      @Override
      public void run() {
        try {
          runnable.run(progressMonitor, cancelable);
        }
        catch (InterruptedException e) {
          model.interrupted(e);
        }
        catch (InvocationTargetException e) {
          model.crashed(e);
        }
        catch (RuntimeException e) {
          model.crashed(e);
        }
        catch (Error e) {
          model.crashed(e);
        }
        catch (Throwable e) {
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

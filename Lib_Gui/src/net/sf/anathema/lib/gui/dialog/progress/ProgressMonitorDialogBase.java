package net.sf.anathema.lib.gui.dialog.progress;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.progress.IInterruptibleRunnableWithProgress;
import net.sf.anathema.lib.progress.INonInterruptibleRunnableWithProgress;
import net.sf.anathema.lib.progress.IRunnableContext;

import java.lang.reflect.InvocationTargetException;

/**
 * A modal dialog that displays progress during a long running operation.
 */
public abstract class ProgressMonitorDialogBase implements IRunnableContext, IProgressMonitorStrategy {

  private boolean first = true;

  @Override
  public void run(INonInterruptibleRunnableWithProgress runnable) throws InvocationTargetException {
    Preconditions.checkNotNull(runnable);
    synchronized (this) {
      if (!first) {
        throw new IllegalStateException("Progress monitor dialog can only be run once."); //$NON-NLS-1$
      }
      first = false;
    }
  }

  @Override
  public void run(IInterruptibleRunnableWithProgress runnable) throws InterruptedException, InvocationTargetException {
    Preconditions.checkNotNull(runnable);
    synchronized (this) {
      if (!first) {
        throw new IllegalStateException("Progress monitor dialog can only be run once."); //$NON-NLS-1$
      }
      first = false;
    }
  }
}
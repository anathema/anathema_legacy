package net.sf.anathema.lib.gui.dialog.progress;

import net.sf.anathema.lib.progress.IInterruptableRunnableWithProgress;
import net.sf.anathema.lib.progress.INonInterruptableRunnableWithProgress;

import java.lang.reflect.InvocationTargetException;

public interface IProgressMonitorStrategie {

  public abstract void run(final INonInterruptableRunnableWithProgress runnable)
      throws InvocationTargetException;

  public abstract void run(final IInterruptableRunnableWithProgress runnable)
      throws InterruptedException,
      InvocationTargetException;

}
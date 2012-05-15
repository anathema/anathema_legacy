package net.sf.anathema.lib.gui.dialog.progress;

import net.sf.anathema.lib.progress.IInterruptibleRunnableWithProgress;
import net.sf.anathema.lib.progress.INonInterruptibleRunnableWithProgress;

import java.lang.reflect.InvocationTargetException;

public interface IProgressMonitorStrategy {

  void run(INonInterruptibleRunnableWithProgress runnable) throws InvocationTargetException;

  void run(IInterruptibleRunnableWithProgress runnable) throws InterruptedException, InvocationTargetException;

}
package net.sf.anathema.lib.progress;

import java.lang.reflect.InvocationTargetException;

/**
 * Interface for UI components which can execute a long-running operation. The context is
 * responsible for displaying a progress indicator and optional Cancel button to the end user while
 * the operation is in progress; the context supplies a progress monitor to be used from code
 * running inside the operation. Note that an {@link IRunnableContext} is not a runnable itself.
 */
public interface IRunnableContext {

  /**
   * Runs the given {@link IInterruptibleRunnableWithProgress} in this context. For example, if this
   * is a ProgressMonitorDialog then the runnable is run using this dialog's progress monitor.
   * 
   * @param runnable the runnable to run
   * @throws InvocationTargetException wraps any exception or error which occurs while running the
   *           runnable
   * @throws InterruptedException propagated by the context if the runnable acknowledges cancelation
   *           by throwing this exception.
   * @see #run(INonInterruptibleRunnableWithProgress)
   */
  void run(IInterruptibleRunnableWithProgress runnable)
      throws InterruptedException,
      InvocationTargetException;

  /**
   * Runs the given {@link INonInterruptibleRunnableWithProgress} in this context. For example, if
   * this is a ProgressMonitorDialog then the runnable is run using this dialog's progress monitor.
   * 
   * @param runnable the runnable to run
   * @throws InvocationTargetException wraps any exception or error which occurs while running the
   *           runnable
   * @see #run(IInterruptibleRunnableWithProgress)
   */
  void run(INonInterruptibleRunnableWithProgress runnable) throws InvocationTargetException;
}
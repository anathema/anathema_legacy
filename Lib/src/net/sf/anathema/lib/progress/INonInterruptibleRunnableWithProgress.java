package net.sf.anathema.lib.progress;

import java.lang.reflect.InvocationTargetException;

/**
 * The INonInterruptibleRunnableWithProgress interface should be implemented by any class whose instances are
 * intended to be executed as a long-running operation. Long-running operations are typically
 * presented at the UI via a modal dialog showing a progress indicator.
 * The class must define a run method that takes a progress monitor.
 * 
 *<p>The run method is usually not invoked directly, but rather by passing the
 * <code>INonInterruptibleRunnableWithProgress</code> to the run method of an {@link IRunnableContext}, which
 * provides the UI for the progress monitor.
 * 
 * @author gebhard */
public interface INonInterruptibleRunnableWithProgress {

  /** Runs this operation. Progress should be reported to the given progress monitor. This
   * method is usually invoked by an IRunnableContext's run method, which supplies the progress
   * monitor. 
   * 
   * @param monitor the progress monitor to use to display progress
   */
  void run(IProgressMonitor monitor) throws InvocationTargetException;

}
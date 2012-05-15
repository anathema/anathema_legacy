package net.sf.anathema.lib.progress;

import java.lang.reflect.InvocationTargetException;

/**
 * The IInterruptibleRunnableWithProgress interface should be implemented by any class whose
 * instances are intended to be executed as a long-running operation. Long-running operations are
 * typically presented at the UI via a modal dialog showing a progress indicator and a Cancel
 * button. The class must define a run method that takes a progress monitor.
 * <p>
 * The run method is usually not invoked directly, but rather by passing the
 * <code>IInterruptibleRunnableWithProgress</code> to the run method of an {@link IRunnableContext},
 * which provides the UI for the progress monitor and Cancel button.
 * 
 * @author gebhard
 */
public interface IInterruptibleRunnableWithProgress {

  /**
   * Runs this operation. Progress should be reported to the given progress monitor. This method is
   * usually invoked by an IRunnableContext's run method, which supplies the progress monitor. A
   * request to cancel the operation should be honored and acknowledged by throwing
   * {@link InterruptedException}.
   * 
   * @param monitor the progress monitor to use to display progress
   * @param cancelable the cancelable to receive requests for cancellation
   * @throws InterruptedException if the operation detects a request to cancel, using
   *           {@link ICancelable#isCanceled()}, it should exit by throwing an
   *           {@link InterruptedException}
   */
  public void run(IProgressMonitor monitor, IObservableCancelable cancelable)
      throws InterruptedException,
      InvocationTargetException;

}
package net.sf.anathema.lib.progress;

/**
 * The IProgressMonitor interface is implemented by objects that monitor the progress of an
 * activity; the methods in this interface are invoked by code that performs the activity.
 * <p>
 * All activity is broken down into a linear sequence of tasks against which progress is reported.
 * When a task begins, a {@link #beginTask(String, int)} notification is reported, followed by any
 * number and mixture of progress reports ({@link #worked(int)}) and subtask notifications (
 * {@link #subTask(String)}). When the task is eventually completed, a {@link #done()} notification
 * is reported. After the done() notification, the progress monitor cannot be reused; i.e.,
 * {@link #beginTask(String, int)} cannot be called again after the call to {@link #done()}.
 * <p>
 * A request to cancel an operation can be signaled using the setCanceled method. Operations taking
 * a progress monitor are expected to poll the monitor periodically and abort at their earliest
 * convenience. Operation can however choose to ignore cancellation requests.
 * <p>
 * Since notification is synchronous with the activity itself, the listener should provide a fast
 * and robust implementation. If the handling of notifications would involve blocking operations, or
 * operations which might throw uncaught exceptions, the notifications should be queued, and the
 * actual processing deferred (or perhaps delegated to a separate thread).
 * <p>
 * Clients may implement this interface.
 */
public interface IProgressMonitor {

  /**
   * Constant indicating an unknown amount of work.
   */
  int UNKNOWN = -1;

  /**
   * Notifies that the main task is beginning. This must only be called once on a given progress
   * monitor instance.
   * 
   * @param name the name (or description) of the main task
   * @param totalWork the total number of work units into which the main task is been subdivided. If
   *          the value is {@link #UNKNOWN} the implemenation is free to indicate progress in a way
   *          which doesn't require the total number of work units in advance.
   * @see #beginTaskWithUnknownTotalWork(String)
   */
  void beginTask(String name, int totalWork);

  /** @see #beginTask(String, int) */
  void beginTaskWithUnknownTotalWork(String name);

  /**
   * Notifies that the work is done; that is, either the main task is completed or the user canceled
   * it. This method may be called more than once (implementations should be prepared to handle this
   * case).
   */
  void done();

  /**
   * Sets the cancel state to the given value.
   * 
   * @param canceled <code>true</code> indicates that cancelation has been requested (but not
   *          necessarily acknowledged); <code>false</code> clears this flag
   */
  void setCanceled(boolean canceled);

  /**
   * Notifies that a subtask of the main task is beginning. Subtasks are optional; the main task
   * might not have subtasks.
   * 
   * @param name the name (or description) of the subtask
   */
  void subTask(String name);

  /**
   * Notifies that a given number of work unit of the main task has been completed. Note that this
   * amount represents an installment, as opposed to a cumulative amount of work done to date.
   * 
   * @param work the number of work units just completed
   */
  void worked(int work);
}
package net.sf.anathema.lib.progress;

public class DefaultRunnableExecuter implements IRunnableExecuter {

  private static int count = 0;
  private final String threadName;

  public DefaultRunnableExecuter(final String threadName) {
    this.threadName = threadName;
  }

  @Override
  public void execute(final Runnable runnable) {
    final String name = threadName + "-" + (++count); //$NON-NLS-1$
    final Thread thread = new Thread(new ThreadGroup(name), runnable);
    thread.setName(name);
    thread.start();
  }
}
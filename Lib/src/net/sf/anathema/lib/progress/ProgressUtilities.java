package net.sf.anathema.lib.progress;

public class ProgressUtilities {

  public static void checkInterrupted(ICancelable cancelable) throws InterruptedException {
    if (cancelable.isCanceled()) {
      throw new InterruptedException();
    }
  }
}
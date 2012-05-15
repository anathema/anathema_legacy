package net.sf.anathema.lib.exception;

/**
 * This class is intended only for internal use of the
 * {@link CentralExceptionHandling}.
 */
public class InternalAwtExceptionHandler implements IExceptionHandler {

  public InternalAwtExceptionHandler() {
    //Necessary for instanciation from the awt eventdispatch thread
  }

  @Override
  public void handle(Throwable exception) {
    CentralExceptionHandling.getInstance().handle(exception);
  }
}
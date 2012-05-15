package net.sf.anathema.lib.gui.dialog.progress;

import java.lang.reflect.InvocationTargetException;

public class InternalProgressDialogModel {

  private boolean canceled = false;
  private boolean finished = false;
  private InterruptedException interruptedException;
  private InvocationTargetException invocationTargetException;
  private RuntimeException runtimeException;
  private Error error;

  public void setCanceled(final boolean canceled) {
    this.canceled = canceled;
  }

  public boolean isCanceled() {
    return canceled;
  }

  public void finished() {
    this.finished = true;
  }

  public boolean isFinished() {
    return finished;
  }

  public void interrupted(final InterruptedException withInterruptedException) {
    this.interruptedException = withInterruptedException;
  }

  public void crashed(final InvocationTargetException withInvocationTargetException) {
    this.invocationTargetException = withInvocationTargetException;
  }

  public void crashed(final RuntimeException withRuntimeException) {
    this.runtimeException = withRuntimeException;
  }

  public void crashed(final Error withError) {
    this.error = withError;
  }

  public void throwThrowableIfAny() throws InterruptedException, InvocationTargetException {
    if (error != null) {
      throw error;
    }
    if (runtimeException != null) {
      throw runtimeException;
    }
    if (interruptedException != null) {
      throw interruptedException;
    }
    if (invocationTargetException != null) {
      throw invocationTargetException;
    }
  }
}
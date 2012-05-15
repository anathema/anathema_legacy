/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
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
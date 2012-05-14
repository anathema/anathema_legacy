/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.progress;

import net.disy.commons.core.progress.IInterruptableRunnableWithProgress;
import net.disy.commons.core.progress.INonInterruptableRunnableWithProgress;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;

/**
 * A modal dialog that displays progress during a long running operation.
 *
 * @author gebhard
 */
public class ProgressMonitorDialog extends ProgressMonitorDialogBase {

  private final InternalProgressDialog progressDialog;
  private final InternalProgressDialogModel model;
  private final ProgressMonitorExecutor progressMonitorExecutor;

  public ProgressMonitorDialog(final Component parentComponent, final String title) {
    model = new InternalProgressDialogModel();
    progressDialog = new InternalProgressDialog(parentComponent, title, model);

    progressMonitorExecutor = new ProgressMonitorExecutor(model, progressDialog);
  }

  @Override
  public void run(final INonInterruptableRunnableWithProgress runnable)
      throws InvocationTargetException {
    super.run(runnable);
    progressDialog.setCancelable(false);

    progressMonitorExecutor.run(runnable, progressDialog);
  }

  @Override
  public void run(final IInterruptableRunnableWithProgress runnable)
      throws InterruptedException,
      InvocationTargetException {
    super.run(runnable);
    progressDialog.setCancelable(true);
    progressMonitorExecutor.run(runnable, progressDialog, progressDialog);
  }

}
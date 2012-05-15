package net.sf.anathema.lib.gui.dialog.progress;

import net.sf.anathema.lib.progress.IInterruptableRunnableWithProgress;
import net.sf.anathema.lib.progress.INonInterruptableRunnableWithProgress;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;

/**
 * A modal dialog that displays progress during a long running operation.
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
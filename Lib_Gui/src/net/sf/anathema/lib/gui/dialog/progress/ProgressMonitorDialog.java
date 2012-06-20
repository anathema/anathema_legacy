package net.sf.anathema.lib.gui.dialog.progress;

import net.sf.anathema.lib.progress.IInterruptibleRunnableWithProgress;
import net.sf.anathema.lib.progress.INonInterruptibleRunnableWithProgress;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;

/**
 * A modal dialog that displays progress during a long running operation.
 */
public class ProgressMonitorDialog extends ProgressMonitorDialogBase {

  private final InternalProgressDialog progressDialog;
  private final ProgressMonitorExecutor progressMonitorExecutor;

  public ProgressMonitorDialog(Component parentComponent, String title) {
    InternalProgressDialogModel model = new InternalProgressDialogModel();
    progressDialog = new InternalProgressDialog(parentComponent, title, model);
    progressMonitorExecutor = new ProgressMonitorExecutor(model, progressDialog);
  }

  @Override
  public void run(INonInterruptibleRunnableWithProgress runnable) throws InvocationTargetException {
    super.run(runnable);
    progressDialog.setCancelable(false);
    progressMonitorExecutor.run(runnable, progressDialog);
  }

  @Override
  public void run(IInterruptibleRunnableWithProgress runnable) throws InterruptedException, InvocationTargetException {
    super.run(runnable);
    progressDialog.setCancelable(true);
    progressMonitorExecutor.run(runnable, progressDialog, progressDialog);
  }
}
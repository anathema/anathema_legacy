package net.sf.anathema.lib.gui.dialog.progress;

import net.sf.anathema.lib.progress.IInterruptibleRunnableWithProgress;
import net.sf.anathema.lib.progress.INonInterruptibleRunnableWithProgress;

import java.awt.Component;
import java.lang.reflect.InvocationTargetException;

/**
 * A modal dialog that displays progress during a long running operation.
 */
public class ProgressMonitorDialog extends ProgressMonitorDialogBase {

  InternalProgressDialogModel model = new InternalProgressDialogModel();
  private final Component parentComponent;
  private final String title;

  public ProgressMonitorDialog(Component parentComponent, String title) {
    this.parentComponent = parentComponent;
    this.title = title;

  }

  @Override
  public void run(INonInterruptibleRunnableWithProgress runnable) throws InvocationTargetException {
    UnstyledProgressDialog progressDialog = new UnstyledProgressDialog(parentComponent, title, model);
    ProgressMonitorExecutor progressMonitorExecutor = new ProgressMonitorExecutor(model, progressDialog);
    super.run(runnable);
    progressMonitorExecutor.run(runnable, progressDialog);
  }

  @Override
  public void run(IInterruptibleRunnableWithProgress runnable) throws InterruptedException, InvocationTargetException {
    InternalProgressDialog progressDialog = new InternalProgressDialog(parentComponent, title, model);
    ProgressMonitorExecutor progressMonitorExecutor = new ProgressMonitorExecutor(model, progressDialog);
    super.run(runnable);
    progressDialog.setCancelable(true);
    progressMonitorExecutor.run(runnable, progressDialog, progressDialog);
  }
}
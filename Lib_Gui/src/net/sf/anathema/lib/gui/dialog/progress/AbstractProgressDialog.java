package net.sf.anathema.lib.gui.dialog.progress;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.gui.swing.GuiUtilities;
import net.sf.anathema.lib.progress.ICanceledListener;
import net.sf.anathema.lib.progress.IObservableCancelable;
import net.sf.anathema.lib.progress.IProgressMonitor;

import javax.swing.JComponent;
import javax.swing.JDialog;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractProgressDialog implements IProgressMonitor, IObservableCancelable, IProgressComponent {

  private JDialog dialog;
  private final InternalProgressDialogModel model;
  private boolean disposed = false;
  private boolean showing = false;
  private boolean dialogClosed = false;
  private final Collection<ICanceledListener> canceledListeners = new ArrayList<ICanceledListener>();
  private final ProgressContainer container;

  public AbstractProgressDialog(InternalProgressDialogModel model, ProgressContainer container) {
    this.container = container;
    Preconditions.checkNotNull(model);
    this.model = model;
  }

  @Override
  public void show() {
    synchronized (this) {
      if (disposed) {
        return;
      }
      showing = true;
      dialog = createDialog();
    }
    GuiUtilities.centerToParent(dialog);
    dialog.setVisible(true);
  }

  protected JComponent getContainerContent() {
    return container.getContent();
  }

  protected abstract JDialog createDialog();

  @Override
  public void dispose() {
    synchronized (this) {
      disposed = true;
      if (showing) {
        while ((dialog == null || !dialog.isVisible()) && !dialogClosed) {
          try {
            wait(50); // give up monitor for WindowListener
          } catch (InterruptedException e) {
            //nothing to do
          }
        }
      }
    }
    if (dialog != null) {
      dialog.dispose();
    }
  }

  @Override
  public final void beginTaskWithUnknownTotalWork(String name) {
    container.beginTaskWithUnknownTotalWork(name);
    yield();
  }

  @Override
  public void beginTask(String name, int totalWork) {
    container.beginTask(name, totalWork);
    yield();
  }

  @Override
  public void done() {
    container.done();
    yield();
  }

  @Override
  public void setCanceled(boolean canceled) {
    model.setCanceled(canceled);
    if (canceled) {
      fireCanceled();
    }
    yield();
  }

  @Override
  public synchronized void addCanceledListener(ICanceledListener listener) {
    Preconditions.checkNotNull(listener);
    canceledListeners.add(listener);
  }

  @Override
  public synchronized void removeCanceledListener(ICanceledListener listener) {
    canceledListeners.remove(listener);
  }

  private void fireCanceled() {
    List<ICanceledListener> clonedListeners;
    synchronized (this) {
      clonedListeners = new ArrayList<ICanceledListener>(canceledListeners);
    }
    for (ICanceledListener listener : clonedListeners) {
      listener.canceled();
    }
  }

  @Override
  public void subTask(String name) {
    container.subTask(name);
    yield();
  }

  @Override
  public void worked(int work) {
    container.worked(work);
    yield();
  }

  @Override
  public boolean isCanceled() {
    yield();
    return model.isCanceled();
  }

  protected void yield() {
    if (dialog != null) {
      if (!dialog.isActive()) {
        dialog.requestFocusInWindow();
      }
    }
    Thread.yield();
  }

  protected void setDialogClosed(boolean newValue) {
    this.dialogClosed = newValue;
  }
}
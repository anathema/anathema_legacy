package net.sf.anathema.lib.gui.dialog.progress;

import com.google.common.base.Preconditions;
import net.disy.commons.swing.layout.util.ButtonPanelBuilder;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.DialogMessages;
import net.sf.anathema.lib.gui.swing.GuiUtilities;
import net.sf.anathema.lib.progress.ICanceledListener;
import net.sf.anathema.lib.progress.IObservableCancelable;
import net.sf.anathema.lib.progress.IProgressMonitor;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class InternalProgressDialog
    implements
    IProgressMonitor,
    IObservableCancelable,
    IProgressComponent {

  private JDialog dialog;
  private final SmartAction cancelAction;
  private boolean cancelable = false;
  private final InternalProgressDialogModel model;
  private boolean disposed = false;
  private boolean showing = false;
  private boolean dialogClosed = false;
  private final Component parentComponent;
  private final String title;
  private final Collection<ICanceledListener> canceledListeners = new ArrayList<ICanceledListener>();
  private final ProgressMonitorComponent monitorComponent;

  public InternalProgressDialog(
      Component parentComponent,
      String title,
      InternalProgressDialogModel model) {
    Preconditions.checkNotNull(model);
    this.parentComponent = parentComponent;
    this.title = title;
    this.model = model;

    cancelAction = new SmartAction(DialogMessages.CANCEL) {
      @Override
      protected void execute(Component parent) {
        performCancel();
      }
    };

    monitorComponent = new ProgressMonitorComponent();
  }

  private void performCancel() {
    if (cancelable) {
      setCanceled(true);
      cancelAction.setEnabled(false);
    }
    yield();
  }

  public void setCancelable(boolean cancelable) {
    this.cancelable = cancelable;
    cancelAction.setEnabled(cancelable);
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

  private JDialog createDialog() {
    JButton cancelButton = new JButton(cancelAction);
    ButtonPanelBuilder builder = new ButtonPanelBuilder();
    builder.add(cancelButton);

    JDialog newDialog = GuiUtilities.createDialog(parentComponent, title);
    newDialog.getContentPane().setLayout(new BorderLayout(2, 2));
    newDialog.getContentPane().add(monitorComponent.getContent(), BorderLayout.CENTER);

    newDialog.getContentPane().add(builder.createPanel(), BorderLayout.SOUTH);
    newDialog.pack();

    newDialog.setSize(Math.max(newDialog.getWidth(), 450), newDialog.getHeight());

    newDialog.setResizable(false);
    newDialog.setModal(true);
    newDialog.getRootPane().setDefaultButton(cancelButton);
    newDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

    newDialog.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        performCancel();
      }

      @Override
      public void windowClosed(WindowEvent e) {
        synchronized (InternalProgressDialog.this) {
          dialogClosed = true;
        }
      }
    });
    return newDialog;
  }

  @Override
  public void dispose() {
    synchronized (this) {
      disposed = true;
      if (showing) {
        while ((dialog == null || !dialog.isVisible()) && !dialogClosed) {
          try {
            wait(50); // give up monitor for WindowListener
          }
          catch (InterruptedException e) {
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
    monitorComponent.beginTaskWithUnknownTotalWork(name);
    yield();
  }

  @Override
  public void beginTask(String name, int totalWork) {
    monitorComponent.beginTask(name, totalWork);
    yield();
  }

  @Override
  public void done() {
    monitorComponent.done();
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
    for (Iterator<ICanceledListener> iter = clonedListeners.iterator(); iter.hasNext();) {
      ICanceledListener listener = iter.next();
      listener.canceled();
    }
  }

  @Override
  public void subTask(String name) {
    monitorComponent.subTask(name);
    yield();
  }

  @Override
  public void worked(int work) {
    monitorComponent.worked(work);
    yield();
  }

  @Override
  public boolean isCanceled() {
    yield();
    return model.isCanceled();
  }

  private void yield() {
    if (dialog != null) {
      /* (gebhard) 07.03.2006: Bugfix: Only request focus if we are not already an 'active' window.
       * Otherwise focus from the 'Cancel' Button might be stolen while user clicks this button
       * (happened with frequent subsequent calls to subTask()). */
      if (!dialog.isActive()) {
        /* Bugfix/Workaround (gebhard) 15.12.2005: Bug 2802: 
         Modal progress dialog can be hidden behind other frame in same JVM.
         The frame is blocked, but no progress dialog is visible.
         So we just request focus as often as possible in order to stay on top of hiding window. */

        /*
         * hier stand urspruenglich
         *  dialog.requestFocus()
         * das sorgt unter linux dazu das sich die sich der focus von anderen Programmen
         * geholt wird wenn diese aktive sind.
         * dieses verhalten tritt mit requestFocusInWindow() nicht mehr auf
         * da die verhalten von requestFocus und requestFocusInWindow Betriebssystem bzw.
         * Xserver spezifisch sind muss hier wenn weiterhin probleme auftreten 
         * muessen hier system spezifische implelementierungen erstellt werden. 
        */
        dialog.requestFocusInWindow();
      }
    }
    Thread.yield();
  }
}
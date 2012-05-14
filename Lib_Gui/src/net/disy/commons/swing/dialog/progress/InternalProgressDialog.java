/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.progress;

import net.disy.commons.core.progress.ICanceledListener;
import net.disy.commons.core.progress.IObservableCancelable;
import net.disy.commons.core.progress.IProgressMonitor;
import net.disy.commons.core.util.Ensure;
import net.disy.commons.swing.dialog.DisyCommonsSwingDialogMessages;
import net.disy.commons.swing.layout.util.ButtonPanelBuilder;
import net.disy.commons.swing.util.GuiUtilities;
import net.sf.anathema.lib.gui.action.SmartAction;

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
      final Component parentComponent,
      final String title,
      final InternalProgressDialogModel model) {
    Ensure.ensureArgumentNotNull(model);
    this.parentComponent = parentComponent;
    this.title = title;
    this.model = model;

    cancelAction = new SmartAction(DisyCommonsSwingDialogMessages.CANCEL) {
      @Override
      protected void execute(final Component parent) {
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

  public void setCancelable(final boolean cancelable) {
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
    final JButton cancelButton = new JButton(cancelAction);
    final ButtonPanelBuilder builder = new ButtonPanelBuilder();
    builder.add(cancelButton);

    final JDialog newDialog = GuiUtilities.createDialog(parentComponent, title);
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
      public void windowClosing(final WindowEvent e) {
        performCancel();
      }

      @Override
      public void windowClosed(final WindowEvent e) {
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
          catch (final InterruptedException e) {
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
  public final void beginTaskWithUnknownTotalWork(final String name) {
    monitorComponent.beginTaskWithUnknownTotalWork(name);
    yield();
  }

  @Override
  public void beginTask(final String name, final int totalWork) {
    monitorComponent.beginTask(name, totalWork);
    yield();
  }

  @Override
  public void done() {
    monitorComponent.done();
    yield();
  }

  @Override
  public void setCanceled(final boolean canceled) {
    model.setCanceled(canceled);
    if (canceled) {
      fireCanceled();
    }
    yield();
  }

  @Override
  public synchronized void addCanceledListener(final ICanceledListener listener) {
    Ensure.ensureNotNull(listener);
    canceledListeners.add(listener);
  }

  @Override
  public synchronized void removeCanceledListener(final ICanceledListener listener) {
    canceledListeners.remove(listener);
  }

  private void fireCanceled() {
    List<ICanceledListener> clonedListeners;
    synchronized (this) {
      clonedListeners = new ArrayList<ICanceledListener>(canceledListeners);
    }
    for (final Iterator<ICanceledListener> iter = clonedListeners.iterator(); iter.hasNext();) {
      final ICanceledListener listener = iter.next();
      listener.canceled();
    }
  }

  @Override
  public void subTask(final String name) {
    monitorComponent.subTask(name);
    yield();
  }

  @Override
  public void worked(final int work) {
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
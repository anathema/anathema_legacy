package net.sf.anathema.lib.gui.dialog.progress;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.progress.ICanceledListener;
import net.sf.anathema.lib.progress.IProgressMonitor;

import javax.swing.JProgressBar;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ProgressMonitorBar extends JProgressBar implements IProgressMonitor {
  private static final int EXPIRATION_MILLIS = 5000;

  private final Timer expirationTimer = new Timer(EXPIRATION_MILLIS, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      setIndeterminate(active);
    }
  });

  private boolean unknownTotalWork;
  private final Collection<ICanceledListener> canceledListeners = new ArrayList<ICanceledListener>();
  private boolean canceled = false;
  private boolean active = false;

  /**
   * Creates a TimeSlider and register ist as a GisTermService
   */
  public ProgressMonitorBar() {
    setOpaque(true);
    setDoubleBuffered(false);
    setBorderPainted(false);
    setStringPainted(false);
  }

  @Override
  public final void beginTaskWithUnknownTotalWork(String name) {
    beginTask(name, UNKNOWN);
  }

  @Override
  public void beginTask(String name, int totalWork) {
    canceled = false;
    setMinimum(0);

    unknownTotalWork = (totalWork == UNKNOWN);
    setIndeterminate(unknownTotalWork);
    setStringPainted(!unknownTotalWork);
    if (!unknownTotalWork) {
      super.setMaximum(totalWork);
    }
    actuallySetValue(0, true);
  }

  @Override
  public void done() {
    setStringPainted(false);
    actuallySetValue(0, false);
    setIndeterminate(false);
  }

  public boolean isCanceled() {
    return canceled;
  }

  @Override
  public void setCanceled(boolean canceled) {
    this.canceled = canceled;
    if (canceled) {
      fireCanceled();
    }
  }

  @Override
  public void subTask(String name) {
    //nothing to do
  }

  @Override
  public void worked(int work) {
    if (!unknownTotalWork) {
      actuallySetValue(getValue() + work, true);
    }
  }

  private void actuallySetValue(int newValue, boolean newActive) {
    this.active = newActive;
    setValue(newValue);
    expirationTimer.restart();
    setIndeterminate(unknownTotalWork);
  }

  public void finish() {
    setIndeterminate(false);
    actuallySetValue(getMaximum(), false);
  }

  public synchronized void addCanceledListener(ICanceledListener listener) {
    Preconditions.checkNotNull(listener);
    canceledListeners.add(listener);
  }

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
}
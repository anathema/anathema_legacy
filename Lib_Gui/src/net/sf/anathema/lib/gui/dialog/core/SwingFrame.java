package net.sf.anathema.lib.gui.dialog.core;

import com.google.common.base.Preconditions;

import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import java.awt.AWTEvent;
import java.awt.ActiveEvent;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.MenuComponent;
import java.awt.Window;
import java.awt.event.WindowListener;

public class SwingFrame implements ISwingFrameOrDialog {

  private final JFrame frame;
  private boolean modal = false;

  public SwingFrame(JFrame frame) {
    Preconditions.checkNotNull(frame);
    this.frame = frame;
  }

  @Override
  public void setTitle(String title) {
    frame.setTitle(title);
  }

  @Override
  public JRootPane getRootPane() {
    return frame.getRootPane();
  }

  @Override
  public void pack() {
    frame.pack();
  }

  @Override
  public void setModal(boolean modal) {
    this.modal = modal;
  }

  @Override
  public Container getContentPane() {
    return frame.getContentPane();
  }

  @Override
  public void setDefaultCloseOperation(int closeOperation) {
    frame.setDefaultCloseOperation(closeOperation);
  }

  @Override
  public void addWindowListener(WindowListener windowListener) {
    frame.addWindowListener(windowListener);
  }

  @Override
  public void removeWindowListener(WindowListener windowListener) {
    frame.removeWindowListener(windowListener);
  }

  @Override
  public void dispose() {
    setVisible(false);
    frame.dispose();
  }

  @Override
  public Window getWindow() {
    return frame;
  }

  @Override
  public void setResizable(boolean resizable) {
    frame.setResizable(resizable);
  }

  @Override
  public void show() {
    setVisible(true);
  }

  @Override
  public void setVisible(boolean visible) {
    frame.setVisible(visible);
    if (!modal) {
      return;
    }
    if (visible) {
      startModal();
    }
    else {
      stopModal();
    }
  }

  private synchronized void startModal() {
    try {
      if (SwingUtilities.isEventDispatchThread()) {
        EventQueue theQueue = frame.getToolkit().getSystemEventQueue();
        while (isVisible()) {
          AWTEvent event = theQueue.getNextEvent();
          Object source = event.getSource();
          if (event instanceof ActiveEvent) {
            ((ActiveEvent) event).dispatch();
          }
          else if (source instanceof Component) {
            ((Component) source).dispatchEvent(event);
          }
          else if (source instanceof MenuComponent) {
            ((MenuComponent) source).dispatchEvent(event);
          }
          else {
            System.err.println("Unable to dispatch: " + event); //$NON-NLS-1$
          }
        }
      }
      else {
        while (isVisible()) {
          wait();
        }
      }
    }
    catch (InterruptedException ignored) {
      //nothing to do
    }
  }

  private synchronized void stopModal() {
    notifyAll();
  }

  @Override
  public boolean isVisible() {
    return frame.isVisible();
  }
}
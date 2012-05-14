/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.core;

import net.disy.commons.core.util.Ensure;

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

  public SwingFrame(final JFrame frame) {
    Ensure.ensureArgumentNotNull(frame);
    this.frame = frame;
  }

  @Override
  public void setTitle(final String title) {
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
  public void setModal(final boolean modal) {
    this.modal = modal;
  }

  @Override
  public Container getContentPane() {
    return frame.getContentPane();
  }

  @Override
  public void setDefaultCloseOperation(final int closeOperation) {
    frame.setDefaultCloseOperation(closeOperation);
  }

  @Override
  public void addWindowListener(final WindowListener windowListener) {
    frame.addWindowListener(windowListener);
  }

  @Override
  public void removeWindowListener(final WindowListener windowListener) {
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
  public void setResizable(final boolean resizable) {
    frame.setResizable(resizable);
  }

  @Override
  public void show() {
    setVisible(true);
  }

  @Override
  public void setVisible(final boolean visible) {
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
        final EventQueue theQueue = frame.getToolkit().getSystemEventQueue();
        while (isVisible()) {
          final AWTEvent event = theQueue.getNextEvent();
          final Object source = event.getSource();
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
    catch (final InterruptedException ignored) {
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
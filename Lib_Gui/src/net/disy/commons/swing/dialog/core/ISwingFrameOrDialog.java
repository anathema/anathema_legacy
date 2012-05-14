/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.core;

import javax.swing.JRootPane;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.WindowListener;

public interface ISwingFrameOrDialog {

  public void setTitle(String title);

  public JRootPane getRootPane();

  public void pack();

  public void setModal(boolean modal);

  public Container getContentPane();

  public void setDefaultCloseOperation(int closeOperation);

  public void addWindowListener(WindowListener windowListener);

  public void removeWindowListener(WindowListener windowListener);

  public void dispose();

  public Window getWindow();

  public void setResizable(boolean resizable);

  public void show();

  public void setVisible(boolean visible);

  public boolean isVisible();
}
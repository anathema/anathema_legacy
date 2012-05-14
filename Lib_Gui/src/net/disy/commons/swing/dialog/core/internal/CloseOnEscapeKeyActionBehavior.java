/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.core.internal;

import net.disy.commons.core.util.Ensure;
import net.disy.commons.swing.dialog.core.AbstractDialog;
import net.disy.commons.swing.util.GuiUtilities;

import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public final class CloseOnEscapeKeyActionBehavior {

  public static void attachTo(final AbstractDialog dialog) {
    Ensure.ensureArgumentNotNull(dialog);
    final KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
    JRootPane rootPane = dialog.getDialog().getRootPane();
    ActionListener listener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        final Window parentComponent = GuiUtilities.getWindowFor(e);
        dialog.performCancel(parentComponent);
      }
    };
    rootPane.registerKeyboardAction(listener, keyStroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
  }
}
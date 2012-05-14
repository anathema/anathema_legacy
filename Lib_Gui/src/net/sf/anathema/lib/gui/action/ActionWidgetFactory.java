/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.action;

import net.disy.commons.core.model.listener.IChangeListener;

import javax.swing.JCheckBox;
import javax.swing.JToggleButton;

public class ActionWidgetFactory {

  public static JToggleButton createToggleButton(final SmartToggleAction action) {
    final JToggleButton button = new JToggleButton(action);
    button.setSelected(action.getSelectionModel().getValue());
    action.getSelectionModel().addChangeListener(new IChangeListener() {
      @Override
      public void stateChanged() {
        button.setSelected(action.getSelectionModel().getValue());
      }
    });
    return button;
  }

  public static JCheckBox createCheckBox(final SmartToggleAction action) {
    final JCheckBox checkBox = new JCheckBox(action);
    checkBox.setSelected(action.getSelectionModel().getValue());
    action.getSelectionModel().addChangeListener(new IChangeListener() {
      @Override
      public void stateChanged() {
        checkBox.setSelected(action.getSelectionModel().getValue());
      }
    });
    return checkBox;
  }
}
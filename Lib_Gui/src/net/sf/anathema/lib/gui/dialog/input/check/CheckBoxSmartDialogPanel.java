/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.input.check;

import net.disy.commons.core.model.BooleanModel;
import net.disy.commons.core.util.Ensure;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.action.ActionWidgetFactory;
import net.sf.anathema.lib.gui.action.SmartToggleAction;
import net.sf.anathema.lib.gui.dialog.input.AbstractSmartDialogPanel;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class CheckBoxSmartDialogPanel extends AbstractSmartDialogPanel {

  private final JCheckBox checkBox;
  private final BooleanModel model;

  public CheckBoxSmartDialogPanel(final BooleanModel model, final String label) {
    this.model = model;
    Ensure.ensureArgumentNotNull(model);
    checkBox = ActionWidgetFactory.createCheckBox(new SmartToggleAction(model, label));
  }

  @Override
  public void addChangeListener(final IChangeListener listener) {
    model.addChangeListener(listener);
  }

  @Override
  public void requestFocus() {
    checkBox.requestFocus();
  }

  @Override
  public void fillInto(final JPanel panel, final int columnCount) {
    final GridDialogLayoutData layoutData = GridDialogLayoutDataFactory
        .createHorizontalSpanData(columnCount);
    panel.add(checkBox, layoutData);
  }

  @Override
  public int getColumnCount() {
    return 1;
  }
}
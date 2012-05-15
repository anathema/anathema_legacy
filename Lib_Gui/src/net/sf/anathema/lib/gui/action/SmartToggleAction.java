/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.action;

import net.sf.anathema.lib.model.IModifiableBooleanModel;

import java.awt.Component;

public class SmartToggleAction extends SmartAction {

  private final IModifiableBooleanModel model;

  public SmartToggleAction(final IModifiableBooleanModel model, final String name) {
    super(name);
    this.model = model;
  }

  public IModifiableBooleanModel getSelectionModel() {
    return model;
  }

  @Override
  protected void execute(final Component parentComponent) {
    model.setValue(!model.getValue());
  }
}
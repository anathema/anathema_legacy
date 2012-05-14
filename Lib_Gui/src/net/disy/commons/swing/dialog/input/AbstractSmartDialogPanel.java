/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.input;

import net.disy.commons.core.model.listener.ListenerList;
import net.disy.commons.core.util.IClosure;

public abstract class AbstractSmartDialogPanel implements ISmartDialogPanel {
  private final transient ListenerList<IRequestFinishListener> listeners = new ListenerList<IRequestFinishListener>();

  protected final void fireRequestFinish() {
    listeners.forAllDo(new IClosure<IRequestFinishListener>() {
      @Override
      public void execute(final IRequestFinishListener listener) {
        listener.requestFinish();
      }
    });
  }

}
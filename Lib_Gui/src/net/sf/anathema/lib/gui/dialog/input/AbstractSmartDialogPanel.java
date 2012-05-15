/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.input;

import org.jmock.example.announcer.Announcer;

public abstract class AbstractSmartDialogPanel implements ISmartDialogPanel {

  private transient Announcer<IRequestFinishListener> listeners = Announcer.to(IRequestFinishListener.class);

  protected final void fireRequestFinish() {
    listeners.announce().requestFinish();
  }

}
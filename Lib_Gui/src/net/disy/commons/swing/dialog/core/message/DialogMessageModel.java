/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.core.message;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.core.message.MessageType;
import net.disy.commons.core.model.AbstractChangeableModel;
import net.disy.commons.core.util.Ensure;

public class DialogMessageModel extends AbstractChangeableModel {

  private IBasicMessage baseMessage;
  private IBasicMessage overlaidMessage;
  private boolean overlaidMessageActive = false;

  public void setMessage(final IBasicMessage message) {
    Ensure.ensureArgumentNotNull(message);
    synchronized (getMutex()) {
      if (message.equals(getMessage())) {
        return;
      }
      if (message.getType() == MessageType.NORMAL) {
        this.baseMessage = message;
        this.overlaidMessageActive = false;
      }
      else {
        this.overlaidMessage = message;
        this.overlaidMessageActive = true;
      }
      fireChangeEvent();
    }
  }

  public boolean isOverlaidMessageActive() {
    synchronized (getMutex()) {
      return overlaidMessageActive;
    }
  }

  public IBasicMessage getBaseMessage() {
    synchronized (getMutex()) {
      return baseMessage;
    }
  }

  public IBasicMessage getOverlaidMessage() {
    synchronized (getMutex()) {
      return overlaidMessage;
    }
  }

  public IBasicMessage getMessage() {
    synchronized (getMutex()) {
      return overlaidMessageActive ? overlaidMessage : baseMessage;
    }
  }
}
/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.message;

/**
 * Constants for specifying the type of a message object.
 * 
 * @author gebhard
 */
public enum MessageType {

  /**
   * @published
   */
  ERROR("Fehler") { //$NON-NLS-1$
    @Override
    public void accept(final IMessageTypeVisitor visitor) {
      visitor.visitError(this);
    }
  },
  /**
   * @published
   */
  WARNING("Warnung") { //$NON-NLS-1$
    @Override
    public void accept(final IMessageTypeVisitor visitor) {
      visitor.visitWarning(this);
    }
  },
  /**
   * @published
   */
  INFORMATION("Information") { //$NON-NLS-1$
    @Override
    public void accept(final IMessageTypeVisitor visitor) {
      visitor.visitInformation(this);
    }
  },
  /**
   * @published
   */
  NORMAL("Normal") { //$NON-NLS-1$
    @Override
    public void accept(final IMessageTypeVisitor visitor) {
      visitor.visitNormal(this);
    }
  },
  /**
   * @published
   */
  QUESTION("Question") { //$NON-NLS-1$
    @Override
    public void accept(final IMessageTypeVisitor visitor) {
      visitor.visitQuestion(this);
    }
  };

  private String name;

  private MessageType(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }

  public abstract void accept(IMessageTypeVisitor visitor);
}
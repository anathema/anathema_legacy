/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.message;

import com.google.common.base.Preconditions;

/** A basic message only contains the text and type of a message. */
public class BasicMessage implements IBasicMessage {
  private final MessageType type;
  private final String text;

  public BasicMessage(final String text) {
    this(text, MessageType.NORMAL);
  }

  public BasicMessage(final String text, final MessageType type) {
    Preconditions.checkNotNull(text, "Text for message may not be null.");
    Preconditions.checkNotNull(type, "Type for message may not be null.");
    this.text = text;
    this.type = type;
  }

  @Override
  public String getText() {
    return text;
  }

  @Override
  public MessageType getType() {
    return type;
  }

  @Override
  public boolean isErrorMessage() {
    return MessageType.ERROR.equals(type);
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof BasicMessage)) {
      return false;
    }
    final BasicMessage other = (BasicMessage) obj;
    return other.type == type && text.equals(other.text);
  }

  @Override
  public int hashCode() {
    return text.hashCode() + 5 * type.hashCode();
  }

  @Override
  public String toString() {
    return getType() + ": " + getText(); //$NON-NLS-1$
  }
}
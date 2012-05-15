/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.message;

import java.io.Serializable;

/**
 * Interface for basic message object only contains the text and type of a message.
 * @published
 * @author gebhard
 */
public interface IBasicMessage extends Serializable {

  /** Returns the text of this message, must not return <code>null</code>.
   * @published */
  public String getText();

  /** Returns the type of this message, must not return <code>null</code>.
   * @published */
  public MessageType getType();

  /**
   * @published
   */
  public boolean isErrorMessage();

}
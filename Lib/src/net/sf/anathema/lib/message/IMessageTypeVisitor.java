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
 * @author gebhard
 */
public interface IMessageTypeVisitor {

  public void visitError(MessageType visitedType);

  public void visitNormal(MessageType visitedType);

  public void visitWarning(MessageType visitedType);

  public void visitInformation(MessageType visitedType);

  public void visitQuestion(MessageType visitedType);

}

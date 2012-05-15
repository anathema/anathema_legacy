/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.progress;

public interface IObservableCancelable extends ICancelable {

  public void addCanceledListener(ICanceledListener listener);

  public void removeCanceledListener(ICanceledListener listener);

}
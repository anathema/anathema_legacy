/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.events;

import net.sf.anathema.lib.control.IChangeListener;

import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.event.TreeSelectionListener;
import java.awt.datatransfer.FlavorListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;

public interface ICheckInputValidListener
    extends
    ActionListener,
    ItemListener,
    ListSelectionListener,
    DocumentListener,
    IChangeListener,
    ChangeListener,
    TableModelListener,
    TreeSelectionListener,
    PropertyChangeListener,
    FlavorListener,
    ListDataListener {

  public void checkInputValid();
}

//Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.lib.gui.table.actions;

import javax.swing.Action;

import net.sf.anathema.lib.gui.table.SmartTable;

// NOT_PUBLISHED
public interface ITableActionFactory {

  public Action createAction(SmartTable table);
}